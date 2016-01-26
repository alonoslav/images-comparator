import edu.image.comparator.Comparator;
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
    double result = comparator.compare("src\\images\\image1.png", "src\\images\\image1.png");
    System.out.println("RESULT = " + result);
  }
}
