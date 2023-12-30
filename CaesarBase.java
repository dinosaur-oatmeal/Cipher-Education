//Names: Will Maberry, Anthony (Tony) Timberman, Fernando Cardenas, AnMinh Vuong
//Date: 9/23/2023

import java.util.*;

/*
Sources:
   MonoAlphabetic.java as reference
*/

public class CaesarBase
{
   private final String alphaString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()-=[];',./<>?:{}|+_`~ ";
   
   private String shiftedAlpha;
   
   private int count, location;
   
   private char character;
   
   //encode
   String encode(String input, int shift)
   {
      String result = "";
      
      //shifted string
      shiftedAlpha = alphaString.substring(shift) + alphaString.substring(0, shift);
      
      //add encoded characters to result
      for(count = 0; count < input.length(); count++)
      {
         character = input.charAt(count);
         location = alphaString.indexOf(character);
         
         result += shiftedAlpha.charAt(location);
      }
      
      return result;
   }
   
   //decode
   String decode(String input)
   {
      String result = "";
      
      //add decoded characters to result
      for(count = 0; count < input.length(); count++)
      {
         character = input.charAt(count);
         location = shiftedAlpha.indexOf(character);
         
         result += alphaString.charAt(location);
      }
      
      return result;
   }
   
   //printing original and encoded alphaString
   String printing()
   {
      return "\nOriginal: " + alphaString + "\nEncoded:  " + shiftedAlpha;
   }
}