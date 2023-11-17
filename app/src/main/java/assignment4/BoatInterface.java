package assignment4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * This is the class that gets input information.
 * It also stores some menus in here and some logic
 * to print stuff correctly. The reason im printing here
 * is because to distribute the code evenly, if i do this
 * in main, main would be very large, there is still logic to do.
 */
public class BoatInterface {
  Scanner scan = new Scanner(System.in, "UTF-8");

  public BoatInterface() {

  }

  /**
   * Shows menu and returns an int.
   */
  public int showMenu() {
    System.out.println("Hi! What would you like to do?"
                     + "\n 1. Add Member"
                     + "\n 2. Edit/Show Members"
                     + "\n 3. Save & Quit");
    return validateInput();
  }

  /**
   * Returns input string.
   */
  public String validateString() {
    String sinput = scan.nextLine();
    return sinput;
  }

  /**
   * Returns input int if its valid, if not
   * it will retry.
   */
  public int validateInput() {
    try  {
      int input = scan.nextInt();
      return input;
    } catch (InputMismatchException e) {
      System.out.println("Input numbers only!");
      scan.nextLine();
      return validateInput();
    }
  }

  /**
   * Shows all members in existance and returns int.
   * The int returned corresponds to members arraylist
   * position.
   */
  public int showMembers(ArrayList<Member> members) {
    StringBuilder result = new StringBuilder();
    System.out.println("Choose a Member for options");
    System.out.println(" 1. Go Back ");

    for (int i = 0; i < members.size(); i++) {
      Member member = members.get(i);
      result.append(" " + (i + 2) + ". " + member.getName() + "\n");
    }
    System.out.println(result);
    return validateInput();
  }

  /**
   * Shows all boats on screen for a member. Returns
   * an int which corresponds to boats position in arraylist.
   */
  public int showBoats(Member member) {
    StringBuilder result = new StringBuilder();
    System.out.println(" 1. Go Back");
    for (int i = 0; i < member.getNoBoats(); i++) {
      Boat boat = member.getBoats()[i];
      result.append(" " + (i + 2) + ". " + boat.getName() + "\n");
    }
    System.out.println(result);
    return validateInput();
  }
  
  /**
   * Lets you choose a type of boat to create
   * also prints it in console.
   */
  public int addBoatmenu() { // Could not return multiple classes, so i have to do this in main.
    System.out.print("What type of boat?\n"
                   + " 1. Canoe\n"
                   + " 2. Sailboat\n"
                   + " 3. Motorboat\n"
                   + " 4. Motorsailer\n");
    int input = validateInput();
    return input;
  }

  /**
   * Returns a new member created from variables inputted.
   */
  public Member addMember() {
    scan.nextLine();
    System.out.print("Enter name for member: ");
    String name = scan.nextLine();
    System.out.print("Enter email (optional): ");
    String email = scan.nextLine();
    Member member = new Member(name, email);
    return member;
  }

  /**
   * Shows a detailed view of a boat.
   * It varies in what should be printed, so a check for 
   * specific boat type has to be made.
   * This function returns an int for what you want to do.
   */
  public int detailedBoat(Boat boat) {
    System.out.print("Boat info: Name: " + boat.getName() + ",Type: " + boat.getType());
    if (boat.getType().equals("canoe")) {
      Canoe canoe = (Canoe) boat;
      System.out.print(",Lenght: " + canoe.getLength());
    }
    if (boat.getType().equals("motorboat")) {
      Motorboat motorboat = (Motorboat) boat;
      System.out.print(",Lenght: " + motorboat.getLength() + ",HP: " + motorboat.getHp());
    }
    if (boat.getType().equals("sailboat")) {
      Sailboat sailboat = (Sailboat) boat;
      System.out.print(",Lenght: " + sailboat.getLength() + ",Depth: " + sailboat.getDepth());
    }
    if (boat.getType().equals("motorsailer")) {
      Motorsailer motorsailer = (Motorsailer) boat;
      System.out.print(",Lenght: " + motorsailer.getLength() + ",Depth: "
                     + motorsailer.getDepth() + ",HP: " + motorsailer.getHp());
    }
    System.out.println("\n 1. Go back\n 2. Delete boat");
    return validateInput();
  }

  /**
   * Shows a detailed list of a member. Also prints out choices.
   * it returns an int based on what you choose on screen
   */
  public int detailedMember(Member member) {
    System.out.println("Member info: Name: " + member.getName()
                     + ",Email: " + member.getEmail() 
                     + ",ID: " + member.getId());
    System.out.println(" 1. Go back\n 2. Delete Member\n 3. Add Boat\n 4. Show Boats");
    return validateInput();
  }
}
