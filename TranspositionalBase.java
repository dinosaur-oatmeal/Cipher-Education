//Names: Will Maberry, Anthony (Tony) Timberman, Fernando Cardenas, AnMinh Vuong
//Date: 9/23/2023

/*
Sources:
   TranspositionalFinal.java as reference
*/

public class TranspositionalBase
{
   private char matrix[][];
   
   private int stringLength, arraySize, count = 0, rows, columns;
   
   private final String alphaString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()-=[];',./<>?:{}|+_`~ ";
   
   //encode
   String encode(String input)
   {
      String result = "";
      
      stringLength = input.length();
      
      //find size of 2D array to make based off of input's length
      for(int findSize = 1; findSize <= 100; findSize++)
      {
         //test to see if findSize is large enough
         if(Math.pow(findSize, 2) >= stringLength)
         {
            arraySize = findSize;
            
            matrix = new char[arraySize][arraySize];
            
            break;
         }
      }
      
      //populate array by row and column
      for(rows = 0; rows < arraySize; rows++)
      {
         for(columns = 0; columns < arraySize; columns++)
         {
            //add real data
            if(count < stringLength)
            {
               matrix[rows][columns] = input.charAt(count);
            }
            
            //add 'X' at end of string to keep array size consistent
            else
            {
               matrix[rows][columns] = 'X';
            }
            
            count++;
         }
      }
      
      //populate result by column and row
      for(rows = 0; rows < arraySize; rows++)
      {
         for(columns = 0; columns < arraySize; columns++)
         {
            result += matrix[columns][rows];
         }
      }
      
      count = 0;
      
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
            if(count < stringLength)
            {
               //add real data to result
               result += matrix[rows][columns];
            }
            
            count++;
         }
      }
      
      count = 0;
      
      return result;
   }
   
   //printing
   String printing()
   {
      String result = "Encoded Table\n";
      
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