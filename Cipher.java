//Names: Will Maberry, Anthony (Tony) Timberman, Fernando Cardenas, AnMinh Vuong
//Date: 9/19/2023

import java.util.*;

/*
Sources:
   No Sources Needed
*/

public class Cipher
{
   public static void main(String args[])
   {
      //Prompt
      System.out.println("The purpose of this program is to show you how various ciphers work.");
      System.out.println("We're going to start simple and get increasingly complex.");
      System.out.println("The hope is that you can't decode the string by the end of this demonstration.");
      System.out.println("However, we'll have the ability to decode it with a bit of *magic* from the computer.");
      
      //input string
      Scanner inputScan = new Scanner(System.in);
      String inputString = "";
      System.out.print("\n\nPlease enter your string to be encoded: ");
      inputString = inputScan.nextLine();
      
      //**CaesarBase**        
      System.out.print("\n\nLet's start really simple with a Caesar cipher.");
      System.out.print("\nThis cipher is designed to shift the alphabet over a certain number of positions.");
      
      //input number
      System.out.print("\n\nPlease Enter how far you'd like to shift (1 - 92): ");
      int shift = inputScan.nextInt();
      
      //ensure input is valid
      while(shift <= 0 || shift >= 93)
      {
         System.out.print("\nOut of bounds. Please enter another number (max is 92 and min is 1): ");
         shift = inputScan.nextInt();
      }
      
      CaesarBase Caesar = new CaesarBase();
      
      //Caesar (user shift)
      String caesarEncode = Caesar.encode(inputString, shift);
      String caesarDecode = Caesar.decode(caesarEncode);
     
      System.out.print("\n\nAs we can see, the entire list of possible inputs is the Original string.");
      System.out.print("\nThe Encoded string is our shifted version of the Original one.");
      System.out.print("\n" + Caesar.printing());
      
      System.out.print("\n\nChosen Caesar Cipher Output: " + caesarEncode);
      System.out.print("\n\nCaesar Cipher Decoded: " + caesarDecode + "\n");
      
      step();
      
      //Caesar (random shift)
      shift = (int)(Math.random() * 92);
      caesarEncode = Caesar.encode(inputString, shift);
      caesarDecode = Caesar.decode(caesarEncode);
      
      System.out.print("\n\nThat was pretty neat. Now let's make the shift be completely random.\n");
      System.out.print("\n" + Caesar.printing());
      
      System.out.print("\n\nRandom Caesar Cipher Output: " + caesarEncode);
      System.out.print("\n\nCaesar Cipher Decoded: " + caesarDecode + "\n");
      
      step();
      
      //**MonoAlphabetic**
      System.out.print("\n\nThat isn't super impressive as it's easy to break. Let's dramatically increase the difficulty.");
      System.out.print("\nNow, we're going to make the encoded string completely random in order.");
      System.out.print("\nThis type of cipher is known as a Monoalphabetic Cipher.\n\n");
      
      MonoAlphabetic monoAlphabet = new MonoAlphabetic();
      
      String monoAlphabetEncode = monoAlphabet.encode(inputString);
      String monoAlphabetDecode = monoAlphabet.decode(monoAlphabetEncode);
      
      System.out.println(monoAlphabet.printing());
      System.out.print("\nMonoalphabetic Cipher Output: " + monoAlphabetEncode);
      System.out.print("\n\nMonoalphabetic Cipher Decoded: " + monoAlphabetDecode + "\n");
      
      step();
      
      //**TranspositionalBase**
      System.out.print("\n\nNow, let's transition to a transpositional cipher.");
      System.out.print("\nThis type of cipher uses a 2D array to encode a message.\n");
      
      TranspositionalBase transBase = new TranspositionalBase();
      
      String transpositionalBaseEncode = transBase.encode(inputString);
      String transpositionalBaseDecode = transBase.decode(transpositionalBaseEncode);
      
      System.out.print("\n" + transBase.printing());
      System.out.print("\nNormal Transpositional Cipher Output: " + transpositionalBaseEncode);
      System.out.print("\n\nTranspositional Cipher Decoded: " + transpositionalBaseDecode + "\n");
      
      step();
      
      //**TranspositionalFinal**
      System.out.print("\n\nTo combat the issues of a regular transpositional cipher, let's add quite a bit of complexity.\n");

      TranspositionalFinal transFinal = new TranspositionalFinal();
      String transpositionalFinalEncode = transFinal.encode(inputString);
      String transpositionalFinalDecode = transFinal.decode(transpositionalFinalEncode);
      
      System.out.print("\nFor starters, we'll always add at least one extra row and column to the matrix to ensure random data is added.");
      System.out.print("\nAdditionally, we're going to inject random data in random locations throughout the real data we're encoding.");
      System.out.print("\nWe'll read the array the same way (columns then rows), but now our data is all jumbled to begin with.");
      System.out.print(transFinal.printing());
      
      System.out.print("\nTranspositional Final Cipher Encoded: " + transpositionalFinalEncode);
      System.out.print("\n\nTranspositional Final Cipher Decoded: " + transpositionalFinalDecode + "\n");
      
      step();
      
      //**TranspositionalFinal + MonoAlphabetic**
      System.out.print("\n\nAlas, we're now ready to use the full capabilities of our ciphers!\n");
      System.out.print("We'll use our Monoalphabetic cipher from earlier, and we'll input that output into our modified transpositional cipher.\n");
      
      step();
      
      String finalEncode = transFinal.encode(monoAlphabetEncode);
      String finalDecode = monoAlphabet.decode(transFinal.decode(finalEncode));
      
      System.out.print("\n\nHere's the Monoalphabetic Input (same as before): " + monoAlphabetEncode + "\n");
      System.out.print(transFinal.printing());
      
      System.out.print("\nFinal Cipher Encoded: " + finalEncode);
      System.out.print("\n\nNow, this message is near impossible for humans to decode...\n");
      
      step();
      
      System.out.print("\n\nTo decode this encoded mess, let's just call a few functions!");
      System.out.print("\n\nFinal Cipher Decoded: " + finalDecode);
   }
   
   //function to break up printing output until ready
   private static void step()
	{
      Scanner inputScan = new Scanner(System.in);
      char zero = '0';
      
      System.out.print("\n\n0 to continue: ");
      while(true)
      {
         zero = inputScan.next().charAt(0);
         
         if(zero == '0')
         {
            break;
         }
      }
	}
}