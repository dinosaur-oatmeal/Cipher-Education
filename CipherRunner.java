//Names: Will Maberry, Anthony (Tony) Timberman, Fernando Cardenas, AnMinh Vuong
//Date: 10/28/2023

//that's a lot of imports...
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
Sources:
   looked up documentation over Swing and AWT
   online code has source comment above it
   primarily referred to my previous programs
*/

public class CipherRunner extends JFrame
{
   //variables used throughout program
   private static String input;
   private String result;
   private String transpositionalBaseEncode;
   private String transpositionalBaseDecode;
   private String transpositionalFinalEncode;
   private String transpositionalFinalDecode;
   
   //button
   static JButton jbtNext = new JButton("Next");
   
   //paintPanel
   ButtonPaint paintPanel = new ButtonPaint();
   
   //nextPanel
   InstructionPanel nextPanel = new InstructionPanel();
   
   //connect to monoAlphabet class
   MonoAlphabetic monoAlphabet = new MonoAlphabetic();
            
   //call MonoAlphabetic functions
   String monoAlphabetEncode = monoAlphabet.encode(input);
   String monoAlphabetDecode = monoAlphabet.decode(monoAlphabetEncode);
   
   //connect to TranspositionalBase class
   TranspositionalBase transBase = new TranspositionalBase();
   
   //connect to TranspositionalFinal class
   TranspositionalFinal transFinal = new TranspositionalFinal();
   
   //constructor
   public CipherRunner()
   {
      //panel for instructions and button
      JPanel bottomPanel = new JPanel();
      bottomPanel.setLayout(new GridLayout(2, 1));
      
      //panel for button to help with formatting
      JPanel jbtNextPanel = new JPanel();
      jbtNextPanel.add(jbtNext);
      
      //add instructions and button panel to bottomPanel
      bottomPanel.add(nextPanel);
      bottomPanel.add(jbtNextPanel);
      
      //Frame Layout
      setLayout(new BorderLayout());
      
      //Panel Layout In Frame
      add(paintPanel, BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
      
      //paint
      repaint();
   }
   
   //Main
   public static void main(String args[])
   {
      //gets input from the user in a panel
      input = JOptionPane.showInputDialog("Enter a short phrase");
      
      //create frame and set title
      JFrame frame = new CipherRunner();
      frame.setTitle("CipherRunner");
      
      //lets the user close the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //sets frame size and doesn't allow frame to be resized
      frame.setSize(1600, 800);
      frame.setResizable(false);
      
      //makes the frame visible and center it (super important)
      frame.setVisible(true);
      frame.setLocationRelativeTo(null);
   }
   
   //code for function obtained from online source: https://programming.guide/java/drawing-multiline-strings-with-graphics.html
   //lets me draw strings on a JPanel with multiple lines (g.drawstring doesn't support '\n')
   private void drawString(Graphics g, String text, int x, int y)
   {
      FontMetrics fm = g.getFontMetrics();
      
      int lineHeight = fm.getHeight();
      
      for(String line : text.split("\n"))
      {
         g.drawString(line, x, y += lineHeight);
      }
   }
   
   //**ButtonPaint Inner Class**
   private class ButtonPaint extends JPanel
   {
      //runs program to repaint
      int programCounter = 0;
      
      //titlePanel
      TitlePaint titlePanel = new TitlePaint();
   
      //leftPanel and rightPanel
      JPanel middle = new JPanel();
      LeftPaint leftPanel = new LeftPaint();
      RightPaint rightPanel = new RightPaint();
      
      private ButtonPaint()
      {
         //add leftPanel and rightPanel to middle
         middle.setLayout(new GridLayout(1, 2));
         middle.add(leftPanel);
         middle.add(rightPanel);
         
         //add titlePanel and middle to ButtonPaint
         setLayout(new GridLayout(2, 1));
         add(titlePanel);
         add(middle);
         
         //jbtNext ActionListener
         jbtNext.addActionListener(new ActionListener()
         {
            //do this when button is clicked
            public void actionPerformed(ActionEvent e)
            {
               //increase programCounter by 1
               programCounter++;
               
               //runs transBase
               if(programCounter == 3)
               {
                  //call functions
                  transpositionalBaseEncode = transBase.encode(input);
                  transpositionalBaseDecode = transBase.decode(transpositionalBaseEncode);
                  result = transBase.printing();
               }
               
               //runs first use of transFinal
               if(programCounter == 4)
               {
                  transpositionalFinalEncode = transFinal.encode(input);
                  transpositionalFinalDecode = transFinal.decode(transpositionalFinalEncode);
                  result = transFinal.printing();
               }
               
               //runs second use of transFinal
               if(programCounter == 5)
               {
                  transpositionalFinalEncode = transFinal.encode(monoAlphabetEncode);
                  transpositionalFinalDecode = monoAlphabet.decode(transFinal.decode(transpositionalFinalEncode));
                  result = transFinal.printing();
               }
               
               //repaint panels
               rightPanel.repaint();
               leftPanel.repaint();
               
               //end program
               if(programCounter == 6)
               {
                  System.exit(0);
               }
            }
         });
      }
      
      //**LeftPaint** (inner-inner class)
      private class LeftPaint extends JPanel
      {
         //graphics
         public void paintComponent(Graphics g)
         {
            super.paintComponent(g);
            
            //Initial Prompt
            if(programCounter == 0)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "The purpose of this program is to show you how various ciphers work."
               + "\nWe're going to start simple and get increasingly complex."
               + "\nThe hope is that you can't decode the string by the end of this demonstration."
               + "\nHowever, we'll have the ability to decode it with a bit of"
               + "\n\u2728magic\u2728 from the computer.", 20, 20);
            }
            
            //Caesar Prompt
            if(programCounter == 1)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "Let's start really simple with a Caesar cipher."
               + "\nThis cipher is designed to shift the alphabet"
               + "\nover a certain number of positions."
               + "\n\nThe entire alphabetical string is shifted over from its original position."
               + "\n\nWhile there are only 92 different seeds to encode with this method,"
               + "\nthat doesn't mean it's super easy to break."
               + "\nWithout the original string, this message gets fairly difficult to decode"
               + "\nbecause we don't know the order of the characters.", 20, 20);
            }
            
            //MonoAlphabetic Prompt
            if(programCounter == 2)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "Now, let's add to the complexity of our cipher!"
               + "\nThis is called a Monoalphabetic cipher,"
               + "\nand it randomizes the way our string is encoded."
               + "\n\nSince we have a 92 character string, there are 91!"
               + "\ndifferent seeds to encode this with."
               + "\nThis is equivalent to 1.35 * 10\u00B9\u2074\u2070"
               + "\n\n(For perspective, there are only 1 * 10\u2078\u2070 atoms in the universe)"
               + "\n\nIt's important to note that not every different seed"
               + "\nwill result in a different output."
               + "\nFor example, the word \"it\" will only have 91 * 90"
               + "\ndifferent ways to be encoded (8190).", 20, 20);
            }
            
            //transBase Table
            if(programCounter == 3)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "Now, we'll look at a completely new type of cipher."
               + "\nThis cipher puts all of our values into a matrix."
               + "\nWe'll put our values in by row and then column."
               + "\nWhen we encode our string, we'll encode it out by"
               + "\ncolumn and then row.", 20, 20);
               
               //prints table
               g.setFont(new Font("Monospaced", Font.BOLD, 14));
               drawString(g, "\n\n\n\n\n\n" + result, 20, 20);
            }
            
            //transFinal Table
            if(programCounter == 4)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "Woah, Where did this table come from!?"
               + "\nNot only did we add extra rows and colums, but we're also injecting"
               + "\nrandom data in random locations throughout the table."
               + "\n\nYou can see your real data somewhere jumbled up in the table.", 20, 20);
               
               //prints table
               g.setFont(new Font("Monospaced", Font.BOLD, 14));
               drawString(g, "\n\n\n\n" + result, 20, 20);
            }
            
            //finalEncode Table
            if(programCounter == 5)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "Now, let's take this to the next level and combine our ciphers."
               + "\nWe'll use our monoalphabetic output as the input for our final cipher."
               + "\n\nHere's the same output from earlier we'll utilize."
               + "\nMonoalphabetic Output: " + monoAlphabetEncode, 20, 20);
               
               //prints table
               g.setFont(new Font("Monospaced", Font.BOLD, 14));
               drawString(g, "\n\n\n\n" + result, 20, 20);
            }
         }
      }
      
      //**RightPaint** (inner-inner class)
      private class RightPaint extends JPanel
      {
         protected void paintComponent(Graphics g)
         {
            super.paintComponent(g);
            
            //set font
            g.setFont(new Font("Monospaced", Font.PLAIN, 15));
            
            if(programCounter == 0)
            {
               //code obtained through StackOverflow
               //questions/67413434/graphics-2d-not-drawing-images-in-java
               try
               {
                  //load image into buffImage
                  BufferedImage buffImage = ImageIO.read(getClass().getResource("CipherLogo.png"));
                  
                  //create 2D graphic object
                  Graphics2D graphics = (Graphics2D)g.create();
                  
                  //draw image with graphics
                  graphics.drawImage(buffImage, 0, 0, this);
                  
                  //delete graphics (not needed anymore)
                  graphics.dispose();
               }
               
               //print issue if something fails
               catch(IOException e)
               {
                  e.printStackTrace();
               }
            }
            
            //Caesar Cipher
            if(programCounter == 1)
            {
               //connect to CaesarBase class
               CaesarBase Caesar = new CaesarBase();
               
               //grab random number
               int shift = (int)(Math.random() * 92);
               
               //call functions
               String caesarEncode = Caesar.encode(input, shift);
               String caesarDecode = Caesar.decode(caesarEncode);
               
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "We'll randomize how far the alphabet shifts over", 0, 20);
               
               g.setFont(new Font("Monospaced", Font.BOLD, 12));
               drawString(g, "\n" + Caesar.printing(), 0, 20);
               
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "\n\n\n\nCaesar Cipher Encoded: " + caesarEncode
               + "\n\nCaesar Cipher Decoded: " + caesarDecode, 0, 20);
            }
            
            //MonoAlphabetic Cipher (functions called earlier in program)
            if(programCounter == 2)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "We'll completely randomize the alphabet's order", 0, 20);
               
               g.setFont(new Font("Monospaced", Font.BOLD, 12));
               drawString(g, "\n\n" + monoAlphabet.printing(), 0, 20);
               
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "\n\n\n\nMonoalphabetic Cipher Encoded: " + monoAlphabetEncode
               + "\n\nMonoalphabetic Cipher Decoded: " + monoAlphabetDecode, 0, 20);
            }
            
            //TranspositionalBase Cipher (functions called earlier in program)
            if(programCounter == 3)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "\nThis type of cipher is fairly easy to break because"
               + "\nwe know where the extra data is added and what it looks like."
               + "\nWhat if we could change that...", 0, 0);
               
               drawString(g, "\n\n\n\nTranspositional Cipher Encoded: " + transpositionalBaseEncode
               + "\n\nTranspositional Cipher Decoded: " + transpositionalBaseDecode, 0, 20);
            }
            
            //TranspositionalFinal Cipher (functions called earlier in program)
            if(programCounter == 4)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "\nNow, our string is much more difficult to decode"
               + "\nbecause some of our data is fake. The injected data's locations"
               + "\nare completely random everytime the program is run.", 0, 0);
               
               //variables used in printing outputs
               char[] array = transpositionalFinalEncode.toCharArray();
               String printing = "";
               int countFifty = 1, count = 0;
               
               //formats transpositionalFinalEncode
               for(int i = 0; i < array.length; i++)
               {
                  if(countFifty == 50)
                  {
                     printing += "\n                      ";
                     countFifty = 1;
                     count++;
                     printing += array[i];
                     continue;
                  }
                  
                  printing += array[i];
                  countFifty++;
               }
               
               drawString(g, "\n\n\n\nFinal Cipher Encoded: " + printing, 0, 20);
               
               //formats transpositionalFinalDecode
               printing = "";
               
               for(int j = 0; j < count; j++)
               {
                  printing += "\n";
               }
               
               drawString(g, printing + "\n\n\n\n\n\nFinal Cipher Decoded: " + transpositionalFinalDecode, 0, 20);
            }
            
            //Monoalphabetic + transpositionalFinal
            if(programCounter == 5)
            {
               //printing
               g.setFont(new Font("Monospaced", Font.PLAIN, 15));
               drawString(g, "\nGood luck trying to decode this string."
               + "\nI don't even want to think about how long that would take...", 0, 0);
               
               //variables used in printing output
               char[] array = transpositionalFinalEncode.toCharArray();
               String printing = "";
               int countFifty = 1, count = 0;
               
               //formats transpositionalFinalEncode
               for(int i = 0; i < array.length; i++)
               {
                  if(countFifty == 50)
                  {
                     printing += "\n                      ";
                     countFifty = 1;
                     count++;
                     printing += array[i];
                     continue;
                  }
                  
                  printing += array[i];
                  countFifty++;
               }
               
               drawString(g, "\n\n\n\nFinal Cipher Encoded: " + printing, 0, 20);
               
               //formats transpositionalFinalDecode
               printing = "";
               
               for(int j = 0; j < count; j++)
               {
                  printing += "\n";
               }
               
               drawString(g, printing + "\n\n\n\n\n\nFinal Cipher Decoded: " + transpositionalFinalDecode, 0, 20);
            }
         }
      }
      
      //**TitlePaint** (inner-inner class)
      private class TitlePaint extends JPanel
      {
         //graphics
         protected void paintComponent(Graphics g)
         {
            super.paintComponent(g);
         
            //set title font
            g.setFont(new Font("Monospaced", Font.BOLD, 16));
         
            //draw title
            //used online source to make the block text for me
            drawString(g,"            |---------------------------------------------------------------------------------------------------------------------------------------|"
            + "\n            |   ______   __            __                                  _______                                                                  |"
            + "\n            |  /      \\ /  |          /  |                                /       \\                                                                 |"
            + "\n            | /||||||  |||/   ______  || |____    ______    ______        |||||||  |  ______    ______    ______    ______   ______   _____  ____   |"
            + "\n            | || |  ||/ /  | /      \\ ||      \\  /      \\  /      \\       || |__|| | /      \\  /      \\  /      \\  /      \\ /      \\ /     \\/    \\  |"
            + "\n            | || |      || |/||||||  ||||||||  |/||||||  |/||||||  |      ||    ||/ /||||||  |/||||||  |/||||||  |/||||||  |||||||  ||||||| ||||  | |"
            + "\n            | || |   __ || ||| |  || ||| |  || |||    || ||| |  ||/       |||||||/  || |  ||/ || |  || ||| |  || ||| |  ||/ /    || ||| | || | || | |"
            + "\n            | || \\__/  ||| ||| |__|| ||| |  || |||||||||/ || |            || |      || |      || \\__|| ||| \\__|| ||| |     /||||||| ||| | || | || | |"
            + "\n            | ||    ||/ || |||    ||/ || |  || |||       ||| |            || |      || |      ||    ||/ ||    || ||| |     ||    || ||| | || | || | |"
            + "\n            |  ||||||/  ||/ |||||||/  ||/   ||/  |||||||/ ||/             ||/       ||/        ||||||/   ||||||| |||/       |||||||/ ||/  ||/  ||/  |"
            + "\n            |               || |                                                                        /  \\__|| |                                  |"
            + "\n            |               || |                                                                        ||    ||/                                   |"
            + "\n            |               ||/                                                                          ||||||/                                    |"
            + "\n            |---------------------------------------------------------------------------------------------------------------------------------------|", 0, 20);
         }
      }
   }
   
   //**InstructionPanel**
   private class InstructionPanel extends JPanel
   {
      //graphics
      public void paintComponent(Graphics g)
      {
         super.paintComponent(g);
         
         //printing(functions called earlier in program)(functions called earlier in program)
         g.setFont(new Font("Monospaced", Font.BOLD, 17));
         drawString(g, "                                           "
         + "                    Click Next To Continue", 0, 0);
      }
   }
} 