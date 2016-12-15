package graphCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 
 * A class to read strings and numbers from the keyboard.
 * The following examples illustrate how to use this class to
 * read from the keyboard:
 *
 * int x = KeyboardReader.readInt();          // read an int
 * double y = KeyboardReader.readDouble();    // read a double
 * String s = KeyboardReader.readString();    // read a String
 *
 * This class also defines return values to indicate end-of-information
 * (EOI) and data conversion errors.
 *
 */

public class KeyboardReader {

   /**
    * Dummy constructor to inhibit documentation.
    */
   private KeyboardReader() {}

   /**
    * Returned by the readInt() method to indicate EOI.
    */
   public static final int EOI_INT = Integer.MAX_VALUE;

   /**
    * Returned by the readDouble() method to indicate EOI.
    */
   public static final double EOI_DOUBLE = Double.MAX_VALUE;

   /**
    * Returned by the readString() method to indicate EOI.
    */
   public static final String EOI_STRING = "END_OF_INFO_1234";

   /**
    * Returned by the readInt() method to indicate an error.
    */
   public static final int ERROR_INT = Integer.MIN_VALUE;

   /**
    * Returned by the readDouble() method to indicate an error.
    */
   public static final double ERROR_DOUBLE = Double.MIN_VALUE;

   /**
    * Returned by the readString() method to indicate an error.
    */
   public static final String ERROR_STRING = "I/O_ERROR_1234";

   /**
    * Controls the output of error messages to the console in
    * response to inappropriate input.
    */
   public static boolean ERROR_MESSAGES = true;

   private static BufferedReader reader = new BufferedReader
                                        (new InputStreamReader(System.in)); 
   
   /**
    * Reads a line of input and converts it into an int.
    * @return the integer that the user typed, or 
    *         EOI_INT to indicate end-of-information, or 
    *         ERROR_INT to indicate a conversion or I/O error.
    */
   public static int readInt() {
      String s = "";
      try {
         s = reader.readLine();
      }catch(IOException e) {
         if (ERROR_MESSAGES)
            System.out.println("IO error: " + e);
         return ERROR_INT;
      }
      if (s == null)
         return EOI_INT;
      int n;
      try {
         n = Integer.parseInt(s.trim());
      } catch (Exception e) {
         if (ERROR_MESSAGES)
            System.out.println ("Bad input: enter digits without decimal point");
         return ERROR_INT;
      }
      return n;
   }
   
   /**
    * Reads a line of input and converts it into a double.
    * @return the number that the user typed, or
    *         EOI_DOUBLE to indicate end-of-information, or 
    *         ERROR_DOUBLE to indicate a conversion or I/O error.
    */
   public static double readDouble() {
      String s = "";
      try {
         s = reader.readLine();
      } catch(IOException e) {
         if (ERROR_MESSAGES)
            System.out.println("IO error: " + e);
         return ERROR_DOUBLE;
      }
      if (s == null) {
         return EOI_DOUBLE;
      }
      double x;
      try {
         x = Double.parseDouble(s.trim());
      } catch (Exception e) {
         if (ERROR_MESSAGES)
            System.out.println ("Bad input: enter digits only");
         return ERROR_DOUBLE;
      }
      return x;
   }
   
   /**
    * Reads a line of character input.
    * @return the line of input that the user typed, or
    *         EOI_STRING to indicate end-of-information, or 
    *         ERROR_STRING to indicate a conversion or I/O error.
    */
   public static String readString() {
      String s = "";
      try {
         s = reader.readLine();
      } catch(IOException e) {
         if (ERROR_MESSAGES)
            System.out.println("IO error: " + e);
         return ERROR_STRING;
      }
      if (s == null)
         return EOI_STRING;
      return s;
   }

   /**
    * Tests the KeyboardReader methods.
    * This method would not normally be invoked by users of the 
    * KeyboardReader class.
    */
   public static void main (String[] args) {

      ERROR_MESSAGES = false;
      // Integer input
      while (true) {
         System.out.println ("Enter an int");
         int i = KeyboardReader.readInt();
         if (i == KeyboardReader.EOI_INT) {
            //System.out.println ("EOI");
            System.out.println ("EOI");
            break;
         }
         else if (i == KeyboardReader.ERROR_INT) {
            System.out.println ("ERROR");
            continue;
         }
         else 
            System.out.println (i + " entered");
      }

      // double input
      while (true) {
         System.out.println ("Enter a double");
         double d = KeyboardReader.readDouble();
         if (d == KeyboardReader.EOI_DOUBLE) {
            //System.out.println ("EOI");
            System.out.println ("EOI");
            break;
         }
         else if (d == KeyboardReader.ERROR_DOUBLE) {
            System.out.println ("ERROR");
            continue;
         }
         else 
            System.out.println (d + " entered");
      }

      // String input
      while (true) {
         System.out.println ("Enter a String");
         String s = KeyboardReader.readString();
         if (s == KeyboardReader.EOI_STRING) {
            System.out.println ("EOI");
           // System.out.println ("EOI");
            break;
         }
         else if (s == KeyboardReader.ERROR_STRING) {
            System.out.println ("ERROR");
            continue;
         }
         else 
            System.out.println (s + " entered");
      }
   }
}
