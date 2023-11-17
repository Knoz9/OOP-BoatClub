package assignment4;

/**
 * This class extends from motorboat because it only has one more
 * unique variable.
 */
public class Motorsailer extends Motorboat {
  private float depth;

  public Motorsailer(String name, String type, float length, float hp, float depth) {
    super(name, type, length, hp);
    this.depth = depth;
  }

  public float getDepth() {
    return depth;
  }
}
