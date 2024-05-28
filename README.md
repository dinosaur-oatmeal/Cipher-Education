# Cipher Education

Purpose:

I worked with classmates to create a program to educate users on how common ciphers work. I was the programmer in the group and wrote all of the code for the project, while they wrote the documentation. The program is split up into a few different classes. There is a class for each individual cipher, a class for a text-based version of the program, and a class that solely runs the graphical user interface (GUI) of the program.

Procedure:

Our ciphers start simple with a Caesar cipher. This cipher allows the user to send an input string to the program. Then, the program randomly shifts over all of our possible inputs, so our alphanumeric string is no longer aligned. By randomly shifting the string over, we can encode our input string with the newly shifted alphanumeric string. There are a total number of 91 possible ways to encode this string.

The Caesar cipher has a glaring flaw about it. If someone can uncover our initial alphanumeric string, they can decode all inputs with ease. To counteract this deficiency, we developed a monoalphabetic cipher. While the overarching idea is the same between the two ciphers, the monoalphabetic cipher drastically increases the difficulty of decoding a string. The monoalphabetic cipher utilizes a hashmap structure to store randomly selected characters and assigns them a number to correspond to the original alphanumeric string. This cipher is specially built to ensure that every single character in the original string gets encoded to a completely random new one. Rather than being limited to 91 ways to encode the alphanumeric string, there are now 91! Ways to encode it.

Cryptarch didn’t want to rely too heavily on one type of cipher, so we have also developed a different type of cipher. This new cipher utilizes a 2D array (matrix) to encode the string. We call this the transposition cipher. First, we find the necessary size of the matrix needed to fit the user’s input. Then, we fill up the rest of the matrix locations with the character ‘X’. Rather than return the string in row -> column order, we transpose the string into column -> row order. This cipher is not meant to be used at scale as it is easy to crack. However, it is the building block for the next cipher meticulously developed.

Going off of the transposition cipher, Cryptarch developed an altered version of it. This cipher functions the same as the previous one. However, this cipher is significantly more complex when compared to the initial transposition cipher. This altered cipher increases the number of rows and columns used in the matrix. Additionally, this cipher uses an ArrayList to grab random locations in the matrix. When we input the user’s string into the matrix, we inject random characters at the locations stored in the ArrayList. This means that the output string has the real data significantly more obscured than the previous cipher.

We’re not finished yet, though! Cryptarch wanted to ensure that no stone was left unturned. As a result, Cryptarch decided to fuse our ciphers. We used the monoalphabetic cipher to obscure the user’s initial data and our modified transposition cipher to obscure the locations of the real data in the output string. When combined, the output string is almost impossible for humans to decode on their own.

To ensure security across our project, all of the variables used in our various ciphers are private, and all inner classes in the Graphical User Interface (GUI) are private. By keeping variables private in the various cipher files, we can ensure that users can’t access or change the necessary data to encode and/or decode the input string.

Our program always stores the necessary keys to decode our ciphers as we call them. As a result, we can decode all of our ciphers by calling a few functions. It’s important to note that the decoded string output is not a reprint of the input. Instead, it is the output of a decoding method we created. By decoding the ciphers in real-time, we can verify that our ciphers are working as intended. This also allows us to increase the scale of our project as it can correctly decode our data.


Graphical User Interface:

For accessibility, we implemented a graphical user interface (GUI) for the user to easily see how our program operates. The GUI is built off of the AWT and Swing libraries in Java. First, the GUI will take an input from the user in a pop-up panel. Once a string is inputted, the program will close the pop-up and launch a splash screen that presents our project. This new frame walks the user through how our ciphers function by running them in real-time. While we explain the logic behind our ciphers, we don’t share exactly how they were coded. We also don’t share any details on how the decoding works to keep our algorithms proprietary.

Each pair of ‘panels’ in our program’s frame covers a quick explanation of how the cipher operates, what its strengths are, and how difficult it would be to decrypt by a malicious user. Our panels are for a Caesar cipher, monoalphabetic cipher, transposition-based cipher, and transposition final cipher. After walking the user through those four ciphers, we combine our two strongest, our monoalphabetic and final transposition ciphers. For security and ease, the GUI will forcibly exit for the user after the GUI displays the final cipher.

Fundamentally our GUI keeps an internal count based on the actions of the user, in this case whenever the user hits the ‘next’ button, the program runs the corresponding classes and functions. Once the program has the data it needs, it automatically repaints the corresponding panels based on the count. The left panel consists of printed strings detailing the cipher and procedure. The right panel displays both the original and encoded inputs, as well as the key to that particular cipher. The key shows each character of the keyboard, and what its corresponding character would be under the cipher. The information on the right panel is obtained using a scanner to read the input, and then using our private classes to run that string through our methods. This first input is the only input required, other than the user clicking the ‘next’ button on the interface.

We also implemented a try-catch to make sure our logo properly uploads, as part of our first panel. We also have several class extensions and imports to ensure the GUI panels operate. Almost all of the variables in our project are private to prevent the user or other classes from interfering with our panels and ciphers. Without these precautions, the GUI would cease to function.


Challenges We Faced:

The CaesarBase.java and TranspositionalBase.java ciphers were both extremely easy to implement. These base ciphers were coded after the more complex ciphers were created. Looking back on it, we should have started simple and scaled from there. However, we weren’t sure what we wanted our project to contain quite yet, so we chose to code the project starting with the most complex ideas.

The monoalphabetic cipher was very difficult to wrap our heads around when coding. We knew how the cipher would function, but we weren’t sure how to get the computer to understand our intent. We settled on using a hashmap so we could map a character to a location in our original alphaString and have that data in the same object. This allowed us to easily step through the hashmap and encode the user’s input.

The final transposition cipher was likely the most cumbersome cipher to code. We quickly learned that the idea of injecting data into an input is much easier said than done. After a lot of brainstorming, we settled on utilizing an ArrayList to hold random positions in the matrix. Then, we would inject data at those locations that the program chose. We used an ArrayList over a LinkedList because we’d be accessing the data more frequently than writing new data to the structure.

The GUI proved to be quite a challenge to implement into the larger project. For starters, the framing of the GUI has some limitations to it that we still can’t easily solve. For instance, the frame size of the GUI isn’t uniform across multiple devices. Will went from coding on a 13” laptop to a 32” monitor, and there was a noticeable difference between the frame’s dimensions and formatting. We tried to mitigate the effects the variation of different systems would have, but the GUI is still limited in that regard. Furthermore, the GUI doesn’t function as intended on non-Windows devices like Tony’s Macbook. We assume it’s a limitation with different operating systems handling Java differently, but we can’t be 100% certain.

This was only the start of our issues. Once we had a frame, we found out that the GUI would alter the encoding of our input whenever the frame was resized or moved on the screen. While it looked neat to see the characters shifting around, it was a pain to figure out how to troubleshoot. To solve this issue, we set the frame to never alter its size. The frame can be moved without repainting it, and the size can’t be altered by the user.

By solving one issue, we created another one. Disallowing the user to resize the frame was a great idea until it was implemented. Now, the text in our various JPanels can extend out of the frame, and there’s no way to access that data. We managed to make a function to format the final output for our last cipher to help ensure none of that data is lost. However, the matrices printed on the left side of the frame frequently extend down too far and off of the panel. There wasn’t a clear solution to this issue, so we opted to ask the user to input a small string when the program launches. By asking for something short to be encoded, we can help mitigate the chance of the matrix extending beyond the frame. 

The text in the GUI is truly a labor of love. We weren’t sure if we could get the GUI to work after figuring out that g.drawString() doesn’t support the ‘\n’ character. Without being able to print new lines, we couldn’t make the GUI work as intended. Thankfully, someone else had thought of this limitation and found a solution for it. We found a method online that functioned the same as g.drawString() but takes the font size chosen into account when writing lines. It splits the lines at a ‘\n’ character and adds the height of the font to simulate how a newline character would function. This method allowed us to write out large blocks of text like the title of the program and explanations for the ciphers.

Similar to the last issue, we assumed that the GUI would run a default character font that was monospaced when coding. We assumed this because that’s what JGrasp runs in the command line (the primary IDE where the project was coded). However, the GUI did not natively print in a monospaced font. Unfortunately, we needed the entire GUI to execute with a monospaced font. Without monospace characters, matrices wouldn’t be aligned, encode and decode strings wouldn’t be aligned, text would randomly extend out of the frame, etc. After tinkering with the code and looking through online documentation, we were able to discover that we could change the font printed to the panels. Once we found this, we forcibly printed text on the JPanels in a monospaced font.

One of the most unique issues we faced when creating the GUI was printing different parts of ciphers on different JPanels. For example, our transposition ciphers print the matrix used in their encoding on the leftPanel and encoded output on the rightPanel. These panels are their own classes inside of a larger class for formatting purposes. Creating an object for a cipher class in a rightPanel/leftPanel disallowed us to access the methods found in the cipher’s class across multiple panels because everything is kept private for security purposes. As a result, we declared some global variables at the top of the program to ensure that all of our ciphers would work in tandem between the JPanels we used for the ciphers that required it.

In the case the GUI doesn’t function as expected, we have another program that runs the project into the command box like any other program we’ve made in this class. While it’s not as fun to use as the GUI, there are no technical limitations the GUI faces when the program is run in the textbox. The program can encode a string exponentially larger than the GUI allows if it’s run on the command line.
