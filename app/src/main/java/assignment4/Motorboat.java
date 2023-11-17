package assignment4;

/**
 * This class extends from canoe since it has length too.
 */
public class Motorboat extends Canoe {
  private float hp;

  public Motorboat(String name, String type, float length, float hp) {
    super(name, type, length);
    this.hp = hp;
  }

  public float getHp() {
    return hp;
  }
}
