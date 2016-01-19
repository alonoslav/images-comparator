package edu.vadym.vorobel.distances;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

import java.util.List;

/**
 * Created by Vadym on 10.01.2016.
 */
public interface ComparatorInterface {
  double getDistance(List<MatOfPoint> contour1, List<MatOfPoint> contour2);
}
