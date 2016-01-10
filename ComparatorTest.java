import edu.vadym.vorobel.Comparator;
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
    comparator.compare("image1.jpg", "image2.jpg");
  }
}
