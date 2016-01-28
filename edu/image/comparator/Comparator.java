package edu.image.comparator;

import edu.image.comparator.distances.DistanceInterface;
import edu.image.comparator.tools.Contour;
import edu.image.comparator.tools.ImageOperations;
import org.opencv.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 10.01.2016.
 */
public class Comparator {

  protected List<DistanceInterface> distancesToFind = new ArrayList<>();
  protected List<Double> distancesRanks = new ArrayList<>();

  public void add(DistanceInterface distanceInterface, double rank) {
    this.distancesToFind.add(distanceInterface);
    this.distancesRanks.add(rank);
  }

  public double compare(String image1, String image2) {
    ImageOperations imageOperations = new ImageOperations();

    Mat imgA = imageOperations.readImage(image1);
    Mat imgB = imageOperations.readImage(image2);

    List<MatOfPoint> contoursA = imageOperations.getReducedContours(imgA, 30);
    List<MatOfPoint> contoursB = imageOperations.getReducedContours(imgB, 30);

    return execute(contoursA, contoursB);
  }

  protected double execute(List<MatOfPoint> contoursA, List<MatOfPoint> contoursB) {

    Mat testImage = new Mat(1000, 1000, CvType.CV_8UC3);

    boolean firstIteration = true;
    int distancesCount = this.distancesToFind.size();
    double[] results = new double[distancesCount];

    for (MatOfPoint contourAMop : contoursA) {
      double[] iterationResults = new double[distancesCount];

      Contour contourA = new Contour();
      contourA.set(contourAMop.toList());

      Point center = contourA.getContourCenter();
      contourA.rotate(contourA.getAngle(), center);

      for (MatOfPoint contourBMop : contoursB) {
        Contour contourB = new Contour();
        contourB.set(contourBMop.toList());

        contourB.move(center);
        contourB.rotate(contourB.getAngle(), center);

        for (int i=0; i<distancesCount; i++) {
          double distanceResult;

          if (this.distancesToFind.get(i).isGromovMode()) {
            distanceResult = this.distancesToFind.get(i).getDistance(contourA.get(), contourB.get());
          } else {
            distanceResult = this.distancesToFind.get(i).getDistance(contourAMop.toList(), contourBMop.toList());
          }

          iterationResults[i] = firstIteration ? distanceResult : Math.min(iterationResults[i], distanceResult);
        }
        firstIteration = false;
      }

      for (int i=0; i<distancesCount; i++) {
        results[i] = Math.max(results[i], iterationResults[i]);
      }
    }

    double result = 0;
    for (int i=0; i<distancesCount; i++) {
      result += this.distancesRanks.get(i) * results[i];
    }

    return result;
  }
}
