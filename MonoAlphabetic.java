//Names: Will Maberry, Anthony (Tony) Timberman, Fernando Cardenas, AnMinh Vuong
//Date: 9/19/2023

import java.util.*;

/*
Sources:
   looked up documentation of HashMap class
   looked up how to use a HashMap in for each loop (decoding and printing)
*/

public class MonoAlphabetic
{
   private final String alphaString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()-=[];',./<>?:{}|+_`~ ";
   
   //convert alphaString to alphaArray
   private final char alphaArray[] = alphaString.toCharArray();
   
   //declare encodedHashMap
   private HashMap<Integer, Character> encodedHashMap = new HashMap<Integer, Character>(alphaString.length());
   
   private int randomInt, location, count = 0;
   
   private char character;
   
   //constructor
   MonoAlphabetic()
   {
      //populate encodedArrayMap
      while(count < alphaString.length())
      {
         //grab random integer from 0 to alphaString's length
         randomInt = (int)(Math.random() * alphaString.length());
         
         //skip iteration and find new number if value already used
         if(encodedHashMap.containsValue(alphaArray[randomInt]))
         {
            continue;
         }
         
         //grab a new character if character equals encoded one
         if(alphaArray[randomInt] == alphaString.charAt(count))
         {
            //reset encoding if ' ' will equal ' '
            if(alphaString.charAt(count) == ' ')
            {
               count = 0;
            }
            
            continue;
         }
         
         //add character to encodedHashMap
         encodedHashMap.put(count, alphaArray[randomInt]);
         
         //increase count for next character
         count++;
      }
   }
   
   //encoding function
   String encode(String input)
   {
      String result = "";
      
      for(int count = 0; count < input.length(); count++)
      {
         character = input.charAt(count);
         location = alphaString.indexOf(character);
         
         //convert given character to encoded one with encodedHashMap and location
         result += encodedHashMap.get(location);
      }
      
      return result;
   }
   
   //decoding function
   String decode(String input)
   {
      String result = "";
      
      for(int count = 0; count < input.length(); count++)
      {
         character = input.charAt(count);
         
         //loop through encodedHashMap to find location of character
         for (Map.Entry<Integer, Character> mapElement : encodedHashMap.entrySet())
         {
            //see if character is the same as the current element in encodedHashMap
            if(character == mapElement.getValue())
            {
               //set location to the element's key
               location = mapElement.getKey();
               
               //convert given character to decoded one with alphaString and location
               result += alphaString.charAt(location);
            }
         }
      }
      
      return result;
   }
   
   //printing original and encoded alphaString
   String printing()
   {
      String result = "Original: " + alphaString + "\nEncoded:  ";
      
      for(Integer value : encodedHashMap.keySet())
      {
         result += encodedHashMap.get(value);
      }
      
      return result;
   }
}