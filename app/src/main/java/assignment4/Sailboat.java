package assignment4;

/**
 * This extends from canoe since it only has one more
 * unique variable.
 */
public class Sailboat extends Canoe {
  private float depth;

  public Sailboat(String name, String type, float length, float depth) {
    super(name, type, length);
    this.depth = depth;
  }

  public float getDepth() {
    return depth;
  }
}
