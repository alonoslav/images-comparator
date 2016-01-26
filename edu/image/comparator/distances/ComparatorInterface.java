package edu.image.comparator.distances;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

import java.util.List;

/**
 * Created by Vadym on 10.01.2016.
 */
public interface ComparatorInterface {
  boolean isGromovMode();
  double getDistance(List<Point> contour1, List<Point> contour2);
}
