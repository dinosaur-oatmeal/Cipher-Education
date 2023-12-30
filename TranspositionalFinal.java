//Names: Will Maberry, Anthony (Tony) Timberman, Fernando Cardenas, AnMinh Vuong
//Date: 9/20/2023

import java.util.*;

/*
Sources:
   looked up documentation of ArrayList class
*/

public class TranspositionalFinal
{
   private char matrix[][];
   
   private int stringLength, arraySize, randomInt, count, randomCount, rows, columns;
   
   private final String alphaString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()-=[];',./<>?:{}|+_`~ ";
   
   //declare ArrayList list
   private ArrayList<Integer> list = new ArrayList<Integer>(alphaString.length());
   
   //encode
   String encode(String input)
   {
      String result = "";
      
      //clear list for multiple runs
      list.clear();
      
      stringLength = input.length();
      
      //find size of matrix based off of input's length
      for(int findSize = 1; findSize <= 100; findSize++)
      {
         //test to see if findSize is large enough
         if(Math.pow(findSize, 2) >= stringLength)
         {
            arraySize = findSize + 2;
            
            matrix = new char[arraySize][arraySize];
            
            break;
         }
      }
      
      count = (int)Math.pow(arraySize, 2);
      
      //grab random locations in the array and add to list (used later)
      do
      {
         randomInt = (int)(Math.random() * (int)Math.pow(arraySize, 2));
         
         //skip doubles
         if(list.contains(randomInt))
         {
            continue;
         }
         
         list.add(randomInt);
         
         count--;
      }
      while(count > stringLength);
      
      //reset values for matrix encoding
      count = 0;
      randomCount = 0;
      
      //add data to matrixEncode in rows then columns
      for(rows = 0; rows < arraySize; rows++)
      {
         for(columns = 0; columns < arraySize; columns++)
         {
            //add random data at list indices
            if(list.contains(randomCount))
            {
               //grab random integer from 0 to alphaString's length and add it to the encode (junk data)
               randomInt = (int)(Math.random() * alphaString.length());
               matrix[rows][columns] = alphaString.charAt(randomInt);
            }
            
            //add input data (real data)
            else
            {
               matrix[rows][columns] = input.charAt(count);
               count++;
            }
            
            //always increase randomCount
            randomCount++;
         }
      }
      
      //add data to result from matrixEncode in columns then rows
      for(rows = 0; rows < arraySize; rows++)
      {
         for(columns = 0; columns < arraySize; columns++)
         {
            result += matrix[columns][rows];
         }
      }
      
      return result;
   }
   
   //decode
   String decode(String input)
   {
      String result = "";
      
      //reset count
      count = 0;
      
      //decoding message
      for(rows = 0; rows < arraySize; rows++)
      {
         for(columns = 0; columns < arraySize; columns++)
         {
            //skip random injected data
            if(list.contains(count))
            {
               count++;
               continue;
            }
            
            //add real data to result
            result += matrix[rows][columns];
            
            count++;
         }
      }
      
      return result;
   }
   
   //printing
   String printing()
   {
      String result = "";
      
      result += "\n\nEncoded Table\n";
      
      for(int i = 0; i < arraySize; i++)
      {
         for(int j = 0; j < arraySize; j++)
         {
            result += matrix[i][j] + " ";
         }
         
         result += "\n";
      }
      
      return result;
   }
}