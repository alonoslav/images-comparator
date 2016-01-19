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

    double result = gromovFrechet.getDistance(contoursA, contoursB);

    System.out.println(result);
    return 0;
  }
}
