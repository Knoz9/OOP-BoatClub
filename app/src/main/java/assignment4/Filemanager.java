package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Saves/loads members.
 */
public class Filemanager {

  /**
   * Save the members and boats into a file.
   * Its pretty straight forward except for boats, it needs
   * to know what boat type it is so that it knows if it needs to 
   * store more or less information in a line.
   */
  protected void save(ArrayList<Member> members) {
    try {
      File saveFile = new File("registry.data");
      //Overwrites file
      if (!saveFile.createNewFile()) {
        if (saveFile.delete() == true) {
          if (saveFile.createNewFile() != true) {
            save(members);
          }
        }
      }
      FileOutputStream fileStream = new FileOutputStream("registry.data");
      OutputStreamWriter writer = new OutputStreamWriter(fileStream, "UTF-8");
      // Goes through all members and writes them into a file. Does it with boats too!
      for (int i = 0; i < members.size(); i++) {
        Member member = members.get(i);
        StringBuilder result = new StringBuilder();
        result.append("MEMBER:" + member.getName() + ":" + member.getEmail() + ":" + member.getId() + "\n");
        if (member.getNoBoats() > 0) {
          Boat[] boats = member.getBoats();
          for (int j = 0; j < member.getNoBoats(); j++) {
            Boat boat = boats[j];
            result.append("BOAT:" + boat.getName());
            if (boat.getType().equals("canoe")) { // This part checks what type of boat it is.
              Canoe idboat = (Canoe) boat;
              result.append(":canoe:" + idboat.getLength() + "\n");
            } else if (boat.getType().equals("sailboat")) {
              Sailboat idboat = (Sailboat) boat;
              result.append(":sailboat:" + idboat.getLength() + ":" + idboat.getDepth() + "\n");
            } else if (boat.getType().equals("motorboat")) {
              Motorboat idboat = (Motorboat) boat;
              result.append(":motorboat" + idboat.getLength() + ":" + idboat.getHp() + "\n");
            } else if (boat.getType().equals("motorsailer")) {
              Motorsailer idboat = (Motorsailer) boat;
              result.append(":motorsailer:" + idboat.getLength() + ":" + idboat.getHp() + ":"
                           + idboat.getDepth() + "\n");
            }
          }
        }
        writer.write(result.toString());
      }
      writer.close();
    } catch (IOException e) { // If it for some reason fails, it just returns nothing.
      return;
    }
  }   
  /*
   * Load file from previous session. It works by splitting at ":"
   * and then loading an array with info for a particular line. It checks info[0]
   * to determine what type of class it stored (Member or boat). It also for boats
   * check what type of boat it is, because all boats carry diffrent information.
   */

  protected ArrayList<Member> load() {

    try {
      ArrayList<Member> members = new ArrayList<Member>();
      File loadFile = new File("registry.data");
      if (loadFile.exists()) {
        Scanner reader = new Scanner(loadFile, "UTF-8");
        while (reader.hasNext()) {
          String[] info = reader.nextLine().split(":");
          if (info[0].equals("MEMBER")) {
            Member member = new Member(info[1], info[2]);
            member.setId(info[3]);
            members.add(member);
          } else {
            if (info[2].equals("canoe")) {
              Canoe boat = new Canoe(info[1], info[2], Float.parseFloat(info[3]));
              members.get(members.size() - 1).addBoat(boat);
            } 
            if (info[2].equals("motorboat")) {
              Motorboat boat = new Motorboat(info[1], info[2], Float.parseFloat(info[3]), 
                  Float.parseFloat(info[4]));
              members.get(members.size() - 1).addBoat(boat);
            } 
            if (info[2].equals("motorsailer")) {
              Motorsailer boat = new Motorsailer(info[1], info[2], Float.parseFloat(info[3]), 
                  Float.parseFloat(info[4]), Float.parseFloat(info[5]));
              members.get(members.size() - 1).addBoat(boat);
            } 
            if (info[2].equals("sailboat")) {
              Sailboat boat = new Sailboat(info[1], info[2], Float.parseFloat(info[3]), 
                  Float.parseFloat(info[4]));
              members.get(members.size() - 1).addBoat(boat);
            }
          }
        }
        reader.close();
        ArrayList<Member> result = members;
        return result;
      }
      //If no data is found, return empty list.
      ArrayList<Member> result = new ArrayList<Member>();
      return result;
    } catch (FileNotFoundException e) { // In case we bump into no file.
      ArrayList<Member> result = new ArrayList<Member>();
      return result;
    }
  }
}