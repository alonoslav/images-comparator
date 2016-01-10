package edu.vadym.vorobel;

import edu.vadym.vorobel.distances.GromovFrechet;
import edu.vadym.vorobel.tools.Contour;
import edu.vadym.vorobel.tools.GUI;
import edu.vadym.vorobel.tools.ImageOperations;
import org.opencv.core.*;

import java.util.List;

/**
 * Created by Vadym on 10.01.2016.
 */
public class Comparator {
  public double compare(String image1, String image2) {
    GromovFrechet gromovFrechet = new GromovFrechet();

    ImageOperations imageOperations = new ImageOperations();

    Mat imgA = imageOperations.readImage(image1);
    Mat imgB = imageOperations.readImage(image2);

    List<MatOfPoint> contoursA = imageOperations.getReducedContours(imgA, 30);
    List<MatOfPoint> contoursB = imageOperations.getReducedContours(imgB, 30);

    boolean firstIteration = true;
    double result = 0.0;
//    for (MatOfPoint contourA : contoursA) {
//      double iterationResult = 0.0;
//
//      Contour cA = new Contour();
//      cA.set(contourA.toList());
//      Point center = cA.getContourCenter();
//
//      cA.rotate(cA.getAngle(), center);
//
//      Mat image = new Mat(imgA.size(), CvType.CV_8UC3);
//      cA.drawContour(image, new Scalar(255, 0, 0));
//      double angle = 0;
//
//      for (MatOfPoint contourB : contoursB) {
//        Contour cB = new Contour();
//        cB.set(contourB.toList());
//        cB.move(cA.getContourCenter());
//
//
//        for (int i = 0; i < cB.get().size(); i++) {
//          double localAngle = cB.getAngle(cB.get(i), center);
//          cB.rotate(localAngle, center);
//          double tempResult = distance.frechet(cA.get(), cB.get());
//
//          if (firstIteration || !firstIteration && tempResult < iterationResult) {
//            iterationResult = tempResult;
//            angle = localAngle;
//            firstIteration = false;
//            cB.drawContour(image, new Scalar(0, 0, 255));
//          }
//
//          cB.rotate(-localAngle, center);
//        }
//      }
//      GUI.displayImage(image);
//      result = Math.max(result, iterationResult);
//    }
    return 0;
  }
}
