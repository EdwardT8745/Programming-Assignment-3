# Programming-Assignment-3

# Problem 1: The Birthday Presents Party
The code for problem 1 is in "birthdayPresents" folder. 

To run the code in the commandline:

1. From birthdayPresents, move to the into the company folder by:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "cd src\com\company"
  
&nbsp;&nbsp;&nbsp; 2. run "javac *.java"

&nbsp;&nbsp;&nbsp; 3. After creating the class files, move back into the src folder 

&nbsp;&nbsp;&nbsp; 4. From the src folder, run "java com.company.Main" 

The code runs with 4 servants/threads. The servants will grab a present first from the bag and put it in the chain. After that, the servant will grab the top "thank you" card from a stavk and check if the corresponding present is in the chain. If it is, the servant will take the present out of the chain and make sure to connect the predecessor’s link with the next gift in the chain. If the gift is not in the chain, the servant puts the tag back in the stack of cards at the bottom.

# Problem 2: Atmospheric Temperature Reading Module

The code for problem 2 is in "Atmospheric_Temperature" folder. 

To run the code in the commandline:

1. move to the src folder

2. from the src folder, run:
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; a."javac *.java"

&nbsp;&nbsp;&nbsp; 3. After creating the class files, run "java Main"

The code, when run, will ask the user to enter a number of guest/threads. The code has 8 sensors/threads. Each sensors will get a random read from -100 to 70. The sensors will put the temperature into a concurrent linked list. For each reading, the sensors will ckeck if their reading is the highest or lowest one. After each reading, the sensors wait 1ms to simulate a minute passing. After 10 "minutes" (10ms), one of the sensor will calculate the differences between the highest and lowest reading and store it for that interval. After an "hour" (60ms), the 10 minute differences will be printed. The 5 highest temperature and the 5 lowest temperature will be printed. 

Resources:

The Art of Multiprocessor Programming, Nir Shavir and Maurice Herlihy, Morgan Kaufmann
