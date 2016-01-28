import edu.image.comparator.Comparator;
import edu.image.comparator.distances.frechet.Frechet;
import edu.image.comparator.distances.gromovFrechet.GromovFrechet;
import org.opencv.core.Core;

/**
 * Created by Vadym on 10.01.2016.
 */
public class ComparatorTest {
  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  public static void main(String[] args) {
    Comparator comparator = new Comparator();

    comparator.add(new Frechet(), 0.5);
    comparator.add(new GromovFrechet(), 0.5);
    double result = comparator.compare("src\\images\\image1.png", "src\\images\\image2.png");

    System.out.println("RESULT = " + result);
  }
}
