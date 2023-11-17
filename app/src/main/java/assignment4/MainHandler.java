package assignment4;

import java.util.ArrayList;

/**
 * This class is the main handler which interpets all the
 * integers that BoatInterface returns. It also keeps track on where
 * the user currently is in the program.
 */
public class MainHandler {

  /**
   * Init mainhandler.
   */
  public MainHandler() {

  }

  /**
   * This always runs Filemanager to load from file when starting.
   */
  private int input;
  private ArrayList<Member> members = new ArrayList<Member>();
  private BoatInterface ui = new BoatInterface();
  private Filemanager filesystem = new Filemanager();
  
  /**
   * Loads file and prints status.
   */
  public void load() {
    System.out.println("Loading file...");
    members = filesystem.load();
    System.out.println("Loaded sucessfully!\n\n\n");
    mainMenu();
  }

  /**
   * Saves and quits program.
   */
  public void saveandquit() {
    filesystem.save(members);
    System.out.println("Save sucessful! Quitting...");
  }

  /**
   * Goes back to main menu, and checks what the user inputs
   * to make a new choice.
   */
  public void mainMenu() {
    input = ui.showMenu();
    if (input == 1) {
      addMember();
    } else if (input == 2) {
      listMembers();
    } else if (input == 3) {
      saveandquit();
    } else {
      System.out.println("Error! Number chosen was out of range!");
      mainMenu();
    }
  }

  /**
   * This belongs in BoatInterface, but i can't return multiple subclasses
   * so i need to do it here. It adds a boat to a member, and it works
   * by running the scanner already in use by the BoatInterface
   * by using validatestring() and validateint().
   */
  public void newBoat(Member member) {
    input = ui.addBoatmenu();
    ui.validateString();
    System.out.print("Name for boat: ");
    String name = ui.validateString();
    System.out.print("Length for boat: ");
    int length = ui.validateInput();
    if (input == 1) {
      Canoe boat = new Canoe(name, "canoe", length);
      member.addBoat(boat);
    } else if (input == 2) {
      System.out.print("Enter depth: ");
      int depth = ui.validateInput();
      Sailboat boat = new Sailboat(name, "sailboat", length, depth);
      member.addBoat(boat);
    } else if (input == 3) {
      System.out.print("Enter enter hp: ");
      int hp = ui.validateInput();
      Motorboat boat = new Motorboat(name, "motorboat", length, hp);
      member.addBoat(boat);
    } else if (input == 4) {
      System.out.print("Enter hp: ");
      int hp = ui.validateInput();
      System.out.print("Enter depth: ");
      int depth = ui.validateInput();
      Motorsailer boat = new Motorsailer(name, "motorsailer", length, hp, depth);
      member.addBoat(boat);
    } else {
      System.out.println("Error. Number out of range!");
      newBoat(member); // Retries if you input something wrong!
    }
    System.out.println("Boat added sucessfully to " + member.getName() + "!");
    mainMenu();
  }

  /**
   * Prints menu from Boatinterface and waits for a response.
   * it lets you delete boat, or go back to main menu.
   */
  public void detailedBoat(Boat boat, Member member) {
    input = ui.detailedBoat(boat);
    if (input == 1) {
      mainMenu();
    } else if (input == 2) {
      member.deleteBoat(boat);
      System.out.println("Deleted Boat!");
      mainMenu();
    }
  }

  /**
   * lists all boats, then waits for a choice.
   * you can either go back, or choose a boat from the list to
   * get even more info and the choice to delete the boat.
   */
  public void listBoats(Member member) {
    input = ui.showBoats(member);
    if (input == 1) {
      mainMenu();
    } else {
      detailedBoat(member.getBoats()[(input - 2)], member);
    }
  }

  /**
   * Adds a member and also checks for dublicate emails or id:s.
   * also checks name length (minimum of 3).
   */
  public void addMember() {
    Member member = ui.addMember();
    if (member.getName().length() < 3) {
      System.out.println("Too short name! Try again!");
      addMember();
    }
    if (member.getEmail() != "") { // Okay to have blank email (no email)
      if (members.size() != 0) {
        for (int i = 0; i < members.size(); i++) {
          if (members.get(i).getId().equals(member.getId()) // Checks for same ID:s
              ||  members.get(i).getEmail().equals(member.getEmail())) { // Checks if the emails are same.
            System.out.println("An Error occured. Try again!\n");
            addMember();
          }
        }
      }
    }
    members.add(member);
    System.out.println("Member added sucessfully!");
    mainMenu();
  }

  /**
   * This lists all members in the array, and lets you choose
   * a member or go back to main menu.
   */
  public void listMembers() {
    input = ui.showMembers(members);
    if (input == 1) {
      mainMenu();
    } else if (input <= (members.size() + 1) && input > 0) {
      detailedMember(members.get((input - 2)));
    } else {
      System.out.println("Error! Number was out of range!");
      mainMenu();
    }
  }

  /**
   * This shows detailed info on member, and also
   * lets you delete the member (along with their boats)
   * It also lets you add a boat, and also list all of their boats.
   */
  public void detailedMember(Member member) {
    input = ui.detailedMember(member);
    if (input == 1) {
      mainMenu();
    } else if (input == 2) {
      members.remove(member);
      System.out.println("Deleted Member!");
      mainMenu();
    } else if (input == 3) {
      newBoat(member);
    } else if (input == 4) {
      listBoats(member);
    } else {
      System.out.println("Error! Invalid number..\n\n");
      mainMenu();
    }
  }

}
