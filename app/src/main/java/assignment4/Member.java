package assignment4;

import java.util.ArrayList;
import java.util.Random;

/**
 * Main class for members.
 */
public class Member {

  private String name;
  private String id;
  private static final Random random = new Random();
  private String email;
  private int noOfBoats = 0;
  private ArrayList<Boat> boats = new ArrayList<Boat>();

  /**
   * Creates new member (with random id).
   */
  public Member(String name, String email) {
    this.name = name;
    this.email = email;
    id = generateId();
  }

  protected String generateId() {
    StringBuilder id = new StringBuilder();
    String acceptedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    while (id.length() < 6) { // length of the random string.
      int alph = (int) (random.nextFloat() * acceptedChars.length());
      id.append(acceptedChars.charAt(alph));
    }

    String newId = id.toString();
    return newId;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getId() {
    return id;
  }

  public int getNoBoats() {
    return noOfBoats;
  }

  /**
   * Adds a boat to arraylist of boats.
   */
  public void addBoat(Boat newBoat) {
    boats.add(newBoat);
    noOfBoats += 1;
  }

  /**
   * Deletes a specific boat from arraylist.
   */
  public void deleteBoat(Boat boat) {
    boats.remove(boat);
    noOfBoats -= 1;
  }

  /**
   * Returns an array deepcopy of internal ArrayList
   * so that its properly encapsulated.
   */
  public Boat[] getBoats() {
    Boat[] copyboats = new Boat[boats.size()];
    for (int i = 0; i < boats.size(); i++) {
      copyboats[i] = boats.get(i);
    }
    return copyboats;
  }

  public void setId(String oldid) {      
    id = oldid;
  }
}
