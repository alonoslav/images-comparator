package edu.image.comparator.distances.gromovFrechet;

import edu.image.comparator.distances.ComparatorInterface;
import edu.image.comparator.distances.frechet.Frechet;
import edu.image.comparator.tools.Contour;
import org.opencv.core.*;

import java.util.List;

/**
 * Created by Vadym on 10.01.2016.
 */
public class GromovFrechet implements ComparatorInterface {
  public boolean isGromovMode() {
    return true;
  }

  public double getDistance(List<Point> contour1, List<Point> contour2) {
    ComparatorInterface frechet = new Frechet();
    return frechet.getDistance(contour1, contour2);
  }
}
