package edu.vadym.vorobel.tools;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 10.01.2016.
 */
public class ImageOperations {
  public Mat readImage(String imagePath) {
    return Imgcodecs.imread(imagePath);
  }

  public Mat convertToBinary(Mat img) {
    Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2GRAY);
    Mat imgBinary = new Mat(img.size(), CvType.CV_8UC1);
    Imgproc.threshold(img, imgBinary, 65, 255, Imgproc.THRESH_BINARY_INV);
    return imgBinary;
  }

  public List<MatOfPoint> findContours(Mat img) {
    // масив контурів
    List<MatOfPoint> contours = new ArrayList<>();
    // знаходження контурів
    Imgproc.findContours(img, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
    return contours;
  }

  public List<Point> reduceContour(MatOfPoint contour, int pointsToLeave) {
    // спрощення контурів
    List<Point> inputPointList = contour.toList();
    int numberToRemove = inputPointList.size() - pointsToLeave;
    ArrayList<Point> outputPointList = new ArrayList<>(inputPointList);

    // якщо кількість точок для видалення <= 0, то повертаємо вхідну ламану
    if(numberToRemove <= 0){
      return outputPointList;
    }

    // якщо кількість точок у ламній менше 3, повертаємо вхідну ламану
    if(outputPointList.size() <= 3){
      return outputPointList;
    }

    // видаляэмо вказану кількість точок
    for(int i=0; i < numberToRemove; i++){

      int minIndex = 0;           // ідекс точки для якої площо трикутника найменша
      double minArea = GeometryUtils.getTriangleArea(outputPointList.get(0),
          outputPointList.get(1), outputPointList.get(2));

      // шукаємо точку з найменшою площою
      // після видалення точки, потрібно знову перераховувати площі для кожної точки ламаної
      for(int j=2; j < outputPointList.size() - 1; j++){
        //обчилюємо площі для всі наступних точок в ламаній
        double area = GeometryUtils.getTriangleArea(outputPointList.get(j-1),
            outputPointList.get(j),outputPointList.get(j+1));

        if(area < minArea){
          minIndex = j;
          minArea = area;
        }
      }
      // видаляємо точку для якої площа найменша
      outputPointList.remove(minIndex);
    }
    return outputPointList;
  }

  public List<MatOfPoint> getReducedContours(Mat image, int pointsToLeave) {
    Mat bwImage = this.convertToBinary(image);

    List<MatOfPoint> contours = this.findContours(bwImage);

    List<MatOfPoint> reducedContours = new ArrayList<MatOfPoint>();
    int i=0;
    for(MatOfPoint contour : contours) {
      if(contour.size().height > 2) {
        List<Point> tempReducedContour = this.reduceContour(contour, pointsToLeave);
        MatOfPoint mop = new MatOfPoint();
        mop.fromList(tempReducedContour);
        reducedContours.add(i, mop);
        i++;
      }
    }

    return reducedContours;
  }
}
