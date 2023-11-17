package assignment4;

/**
 * Class for canoe.
 */
public class Canoe extends Boat {
  private float length;

  /**
   * Makes new canoe, uses super for common variables.
   */
  public Canoe(String name, String type, float length) {
    super(name, type);
    this.length = length;
  }

  public float getLength() {
    return length;
  }
}
