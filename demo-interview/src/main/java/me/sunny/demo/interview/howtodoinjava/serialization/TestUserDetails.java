package me.sunny.demo.interview.howtodoinjava.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * Serialization and deserialization example
 * @author sunnnychan@gmail.com
 */
public class TestUserDetails {

  public static void main(String[] args) {
    // Create new UserDetails object
    UserDetails myDetails = new UserDetails("Lokesh", "Gupta", 102825,
        new Date(Calendar.getInstance().getTimeInMillis()));

    // Serialization code
    try {
      FileOutputStream fileOut = new FileOutputStream("userDetails.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(myDetails);
      out.close();
      fileOut.close();
    } catch (IOException i) {
      i.printStackTrace();
    }

    // deserialization code
    @SuppressWarnings("unused")
    UserDetails deserializedUserDetails = null;
    try {
      FileInputStream fileIn = new FileInputStream("userDetails.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      deserializedUserDetails = (UserDetails) in.readObject();
      in.close();
      fileIn.close();

      // verify the object state
      System.out.println(deserializedUserDetails.getFirstName());
      System.out.println(deserializedUserDetails.getLastName());
      System.out.println(deserializedUserDetails.getAccountNumber());
      System.out.println(deserializedUserDetails.getDateOpened());
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
}
