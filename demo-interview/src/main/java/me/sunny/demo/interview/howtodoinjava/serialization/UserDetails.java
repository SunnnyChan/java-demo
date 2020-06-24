package me.sunny.demo.interview.howtodoinjava.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.util.Date;

/**
 * Sample class following serialization best practices
 *
 * @author sunnnychan@gmail.com
 */

public final class UserDetails implements Serializable {

  /**
   * This constructor requires all fields
   *
   * @param aFirstName
   * contains only letters, spaces, and apostrophes.
   * @param aLastName
   * contains only letters, spaces, and apostrophes.
   * @param aAccountNumber
   * is non-negative.
   * @param aDateOpened
   * has a non-negative number of milliseconds.
   */
  public UserDetails(String aFirstName, String aLastName, int aAccountNumber, Date aDateOpened)
  {
    super();
    setFirstName(aFirstName);
    setLastName(aLastName);
    setAccountNumber(aAccountNumber);
    setDateOpened(aDateOpened);
    // there is no need here to call verifyUserDetails.
  }

  // The default constructor
  public UserDetails() {
    this("FirstName", "LastName", 0, new Date(System.currentTimeMillis()));
  }

  public final String getFirstName() {
    return fFirstName;
  }

  public final String getLastName() {
    return fLastName;
  }

  public final int getAccountNumber() {
    return fAccountNumber;
  }

  /**
   * Returns a defensive copy of the field so that no one can change this
   * field.
   */
  public final Date getDateOpened() {
    return new Date(fDateOpened.getTime());
  }

  /**
   * Names must contain only letters, spaces, and apostrophes. Validate before
   * setting field to new value.
   *
   * @throws IllegalArgumentException
   * if the new value is not acceptable.
   */
  public final void setFirstName(String aNewFirstName) {
    verifyNameProperty(aNewFirstName);
    fFirstName = aNewFirstName;
  }

  /**
   * Names must contain only letters, spaces, and apostrophes. Validate before
   * setting field to new value.
   *
   * @throws IllegalArgumentException
   * if the new value is not acceptable.
   */
  public final void setLastName(String aNewLastName) {
    verifyNameProperty(aNewLastName);
    fLastName = aNewLastName;
  }

  /**
   * Validate before setting field to new value.
   *
   * @throws IllegalArgumentException
   * if the new value is not acceptable.
   */
  public final void setAccountNumber(int aNewAccountNumber) {
    validateAccountNumber(aNewAccountNumber);
    fAccountNumber = aNewAccountNumber;
  }

  public final void setDateOpened(Date aNewDate) {
    // make a defensive copy of the mutable date object
    Date newDate = new Date(aNewDate.getTime());
    validateAccountOpenDate(newDate);
    fDateOpened = newDate;
  }

  /**
   * The client's first name.
   *
   * @serial
   */
  private String fFirstName;

  /**
   * The client's last name.
   *
   * @serial
   */
  private String fLastName;

  /**
   * The client's account number.
   *
   * @serial
   */
  private int fAccountNumber;

  /**
   * The date the account was opened.
   *
   * @serial
   */
  private Date fDateOpened;

  /**
   * Determines if a de-serialized file is compatible with this class.
   * Included here as a reminder of its importance.
   */
  private static final long serialVersionUID = 7526471155622776147L;

  /**
   * Verify that all fields of this object take permissible values
   *
   * @throws IllegalArgumentException
   * if any field takes an unpermitted value.
   */
  private void verifyUserDetails() {
    validateAccountNumber(fAccountNumber);
    verifyNameProperty(fFirstName);
    verifyNameProperty(fLastName);
    validateAccountOpenDate(fDateOpened);
  }

  /**
   * Ensure names contain only letters, spaces, and apostrophes.
   *
   * @throws IllegalArgumentException
   * if field takes an unpermitted value.
   */
  private void verifyNameProperty(String aName) {
    boolean nameHasContent = (aName != null) && (!aName.equals(""));
    if (!nameHasContent) {
      throw new IllegalArgumentException(
          "Names must be non-null and non-empty.");
    }

    StringCharacterIterator iterator = new StringCharacterIterator(aName);
    char character = iterator.current();
    while (character != StringCharacterIterator.DONE) {
      boolean isValidChar = (Character.isLetter(character)
          || Character.isSpaceChar(character) || character == '\'');
      if (isValidChar) {
        // do nothing
      } else {
        String message = "Names can contain only letters, spaces, and apostrophes.";
        throw new IllegalArgumentException(message);
      }
      character = iterator.next();
    }
  }

  /**
   * AccountNumber must be non-negative.
   *
   * @throws IllegalArgumentException
   * if field takes an unpermitted value.
   */
  private void validateAccountNumber(int aAccountNumber) {
    if (aAccountNumber < 0) {
      String message = "Account Number must be greater than or equal to 0.";
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * DateOpened must be after 1970.
   *
   * @throws IllegalArgumentException
   * if field takes an unpermitted value.
   */
  private void validateAccountOpenDate(Date aDateOpened) {
    if (aDateOpened.getTime() < 0) {
      throw new IllegalArgumentException(
          "Date Opened must be after 1970.");
    }
  }

  /**
   * Always treat deserialization as a full-blown constructor, by validating
   * the final state of the de-serialized object.
   */
  private void readObject(ObjectInputStream aInputStream)
      throws ClassNotFoundException, IOException {
    // always perform the default deserialization first
    aInputStream.defaultReadObject();

    // make defensive copy of the mutable Date field
    fDateOpened = new Date(fDateOpened.getTime());

    // ensure that object state has not been corrupted or tampered with
    // malicious code
    verifyUserDetails();
  }

  /**
   * This is the default implementation of writeObject. Customise if
   * necessary.
   */
  private void writeObject(ObjectOutputStream aOutputStream)
      throws IOException {
    // perform the default serialization for all non-transient, non-static
    // fields
    aOutputStream.defaultWriteObject();
  }
}