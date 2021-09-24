import java.io.*; 

public class Main
{
    //main method
    public static void main (String [] args) throws IOException
    {   // start the main method
        
        BufferedReader myInput2 = new BufferedReader (new InputStreamReader (System.in));
       
        int count= 100,checker=-1; //set variables which will be used for making game board
        System.out.println ("Game Board");
        
       // this while loop makes the board for the player to visualise what the game looks like. It uses a count to increment or decrement by 1. It will also 9 or 10 to create a board.
        while (count > 0){
            if (count%10 == 0 && count != 100){// checks if count is at 10..20.. and so on
                if(checker==-1)
                {
                    count-=9;
                    checker=1;
                }
                else
                {
                    System.out.print(count+"\t");
                    count-=10;
                    checker=-1; 
                }
                if(count!=0)
                System.out.print("\n" + count + "\t"); 
            }
            else
            System.out.print(count + "\t"); //prints out the count
            count+=checker; //sets the count to add by checker
        }
        System.out.println();
        
        String startGame = "y"; //variable used to ask if the player wants to play
        System.out.print ("Do you want to start the game? Y or N     >  "); //asks user if they want to play
        startGame = myInput2.readLine (); // reads input
       
        while (startGame.equals ("y") || startGame.equals ("Y"))// if input equals y or Y, it calls the playGame method and starts the game
        {
            startGame = playGame(startGame); 
        }
        System.out.println ("\n\n\n\n\t\t\t\t\t\tHave Fun!");
    } // end of main method
    
    //playGame method
    public static String playGame (String play) throws IOException 

    {
        BufferedReader myInput = new BufferedReader (new InputStreamReader (System.in));
        int playerPosition = 1; //sets players position to 1
        int computerPosition = 1; // sets computers position to 1
        int diceRoll = 0; //creates first dice
        int diceRoll1 = 0;// creates second dice
        int playerRoll = 1; // declares what player rolled
        int computerRoll = 1; // declares what computer rolled
        String playAgain = "y"; 
       
        int snakes_LaddersArray [] = new int [6]; //creates 6 element array to store the snakes and ladders
        snakes_LaddersArray [0] = 50;
        snakes_LaddersArray [1] = 92;
        snakes_LaddersArray [2] = 97;
        snakes_LaddersArray [3] = 10;
        snakes_LaddersArray [4] = 37;
        snakes_LaddersArray [5] = 70;
        
        
        while (playAgain.equals ("Y") || playAgain.equals ("y")) 
        {
            playerRoll =  playDice(diceRoll, diceRoll1);// sends data to method
            computerRoll =  playDice(diceRoll, diceRoll1); // sends date to method
            System.out.println ("\t\t\t\t\t|\tYou rolled a " + playerRoll + "\t\t|"); 
            System.out.println ("\t\t\t\t\t|\tThe Computer rolled a " + computerRoll + "\t|"); 
            playerPosition = playerPosition + playerRoll;// holds player position if position is higher than 100
            computerPosition = computerPosition + computerRoll;// does the same but for computer
            
            // checks to see if player or computer lands on a snake or ladder
            playerPosition = getP(playerPosition, playerRoll, snakes_LaddersArray);
            computerPosition = computergetP(computerPosition, computerRoll, snakes_LaddersArray, playerPosition);
            
            System.out.println ("\t\t\t*\t\tYou are on square " + playerPosition + "\t\t\t*"); 
            System.out.println ("\t\t\t*\t\tThe Computer is on square " + computerPosition + "\t\t*"); 
            
           //resets positions if the player or computer has won
            if (playerPosition == 100 || computerPosition == 100)
            {
                playerPosition = 1;
                computerPosition = 1;
                System.out.print ("Do you want to start the game? Y or N     >  ");
                playAgain = myInput.readLine ();
                System.out.print ("\n\n\n\n\n\n\n\n\n\n\n\n");
            }
            else
            {
        //asks player if they want to keep playing
                System.out.print ("Do you want to play? Y or N     >  ");
                playAgain = myInput.readLine ();
                
            }
        }
        
        return playAgain; 
    }
    
   //playDice method
    public static int playDice (int diceRoll, int diceRoll1)
    {
        diceRoll = (int)(Math.random()*6 )+1 ; //creates dice roll 
        diceRoll1 = (int)(Math.random()*6 )+1 ; creates dice roll 1
        int move = diceRoll + diceRoll1; adds both rolls to get total spaces moves
        return move; 
    }
    
    //getP method
//used to check if player is on a snake or ladder and then changes their position and lets the player know what their position after the change is 
    public static int getP (int playerPosition, int playerRoll, int snakes_LaddersArray []) throws IOException 
    {
        if(playerPosition == snakes_LaddersArray[0])//if position equals snake 1
        {
            playerPosition = 17; //new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got Bit By A Snake,YOU GO DOWN!~~~~~~~~~~~~~");
        }
        else if (playerPosition == snakes_LaddersArray[1]) //if position equals snake 2
        {
            playerPosition = 46; // new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got Bit By A Snake,YOU GO DOWN!~~~~~~~~~~~~~");
            
        }
        else if (playerPosition == snakes_LaddersArray[2]) //if position equals snake 3
        {
            playerPosition = 74; // new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got Bit By A Snake,YOU GO DOWN!~~~~~~~~~~~~~");
        }
        else if (playerPosition == snakes_LaddersArray[3]) //if position equals ladder 1
        {
            playerPosition = 30; // new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Ladder!! YOU GO UP!~~~~~~~~~~~~~");
            
        }
        else if (playerPosition == snakes_LaddersArray[4]) //if position equals ladder 2
        {
            playerPosition = 66; // new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Ladder!!YOU GO UP!~~~~~~~~~~~~~");
            
        }
        else if (playerPosition == snakes_LaddersArray[5]) //if position equals ladder 3
        {
            
            
            playerPosition = 88; // new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Ladder!!YOU GO UP!~~~~~~~~~~~~~");
        }
        
        
         if (playerPosition > 100) checks is players position is greater than 100
        {
            playerPosition = playerPosition - playerRoll;// subtracts players position from their roll
            System.out.println ("You cant jump, you must land on a 100"); 
            
        }
        else if (playerPosition == 100) //checks if players position equals 100
        {
            System.out.println ("YOU WON,WELL DONE!"); 
            
        }
        return playerPosition; 
    }
    
//computergetP method
// exact same as getP but for the computer
    public static int computergetP (int computerPosition, int computerRoll, int snakes_LaddersArray [], int playerPosition) throws IOException
    {
        
        if(computerPosition == snakes_LaddersArray[0])
        {
            computerPosition = 17;
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got Bit By A Snake, THEY GO DOWN!~~~~~~~~~~~~~");
            
            
        }
        else if (computerPosition == snakes_LaddersArray[1])
        {
            computerPosition = 46;
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got Bit By A Snake, THEY GO DOWN!~~~~~~~~~~~~~");
            
        }
        else if (computerPosition == snakes_LaddersArray[2])
        {
            computerPosition = 74;
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got Bit By A Snake, THEY GO DOWN!~~~~~~~~~~~~~");
        }
        else if (computerPosition == snakes_LaddersArray[3])
        {
            computerPosition = 30;
            System.out.println ("Computer Got TO A Ladder, THEY WENT UP!");
        }
        else if (computerPosition == snakes_LaddersArray[4])
        {
            computerPosition = 66;
            System.out.println ("Computer Got TO A Ladder, THEY WENT UP!");
            
        }
        else if (computerPosition == snakes_LaddersArray[5])
        {
            computerPosition = 88;
            System.out.println ("Computer Got TO A Ladder, THEY WENT UP!");
        }
        
         if (computerPosition > 100) 
        {
            computerPosition = computerPosition - computerRoll; 
            System.out.println ("THE COMPUTER CAN'T JUMP! They must land on a 100"); 
        }
        else if (computerPosition == 100 && playerPosition != 100)
        {
            System.out.println ("THE COMPUTER WON, YOU LOST!"); 
        }
        return computerPosition; 
    } 
}
