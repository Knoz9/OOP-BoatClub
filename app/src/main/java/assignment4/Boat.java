package assignment4;

import java.io.Serializable;

/**
 * The main class for boats.
 * All other classes extend from this class.
 */
public class Boat implements Serializable {
  private String name;
  private String boatType;

  /**
   * Adds a new default boat if type is correct.
   */
  public Boat(String name, String type) {
    if (type.equalsIgnoreCase("canoe") 
        || type.equalsIgnoreCase("motorboat")
        || type.equalsIgnoreCase("motorsailer") 
        || type.equalsIgnoreCase("sailboat")) {
      this.name = name;
      boatType = type.toLowerCase();
    }
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return boatType;
  }
}
