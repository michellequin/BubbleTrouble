/**
 * PlayGame.java
 * Written by Leslie Gates and Michelle Quin
 * 
 * This class uses the Board, Player and ComputerPlayer classes to create objects
 * that set up a playable game of Trouble
 * 
 * This class utilizes the data structures: Queue and MaxHeap/PriorityQueue
 */

import javafoundations.LinkedQueue;
import javafoundations.PriorityQueue;
import java.util.*;


public class PlayGame {
  
  /*
   * instance variables
   */
  
  //colors
  private Player red;
  private Player blue;
  private Player green;
  private Player yellow;
  
  //board
  private Board board;
  
  //queue of players in order (current player whose turn it is
  //is at front of queue)
  protected LinkedQueue<Player> order;
  
  //priorityqueue/maxheap of players in order of who is winning
  private PriorityQueue<Player> pq;
  
  /**
   * constructor, takes in no parameters
   */
  public PlayGame() {
    
    //create a new board object/instantiate board
    board = new Board();
    //Does not construct the players yet as we don't necessarily have their names
  }
  
  /**
   * HELPER FUNCTION, checks to see if the player is a computer player or not
   * 
   * (human players all have input names that are not empty strings)
   * 
   * @param name (string)
   * @return boolean
   */
  private boolean isComputer(String name) {
    
    //if name is empty string, return true; otherwise, return false
    return name=="";
  }
  
  /**
   * HELPER FUNCTION, retrieves the board
   * 
   * @return board
   */
  public Board getBoard(){
    return board;
  }
  
  /**
   * HELPER METHOD
   * Sets up the players, checking to see if you need to create any computer players
   * Takes in 4 strings, if any are empty, creates a computer player for that color
   * 
   * @param p1, p2, p3, p4 (strings)
   * 
   */
  private void setupPlayers(String p1, String p2, String p3, String p4){
                        //    blue        red      yellow      green 

    //Blue player set up
    if (isComputer(p1))
      blue = new ComputerPlayer("blue");
    else 
      blue = new Player("blue",p1);

    //Green player set up
    if (isComputer(p4))
      green = new ComputerPlayer("green");
    else 
      green = new Player("green",p4);
    
    //Red player set up
    if (isComputer(p2))
      red = new ComputerPlayer("red");
    else 
      red = new Player("red",p2);
    
    //Yellow player set up
    if (isComputer(p3))
      yellow = new ComputerPlayer("yellow");
    else 
      yellow = new Player("yellow",p3);  
  }
  
  /**
   * HELPER METHOD
   * Method that determines the order of the players in the beginning of the game.
   * 
   * @param p1, p2, p3, p4
   * @return linked queue of players
   */
  public LinkedQueue<Player> orderPlay(String p1, String p2, String p3, String p4) {
                                   //    blue        red      yellow      green 
    //call setupPlayers helper method using input strings
    //to create appropriate players/computerplayers
    this.setupPlayers(p1, p2, p3, p4);
    
    //instantiate order LinkedQueue
    order = new LinkedQueue<Player>();
    
    //initialize max index at 0
    int index = 0;
    //initialize max number rolled at 1
    int max = 1;
    
    //initialize first player as blue
    Player first = blue;
    //initialize a Player array in color order of board, clockwise
    Player[] players = {blue,red,yellow,green};
    
    //each color rolls the die
    blue.rollDie();
    red.rollDie();
    yellow.rollDie();
    green.rollDie();
    
    //make temp int array to store the rolled number values
    int[] temp = {blue.getDieNumber(), red.getDieNumber(), yellow.getDieNumber(), green.getDieNumber()};
    
    //for int i is equal to 0 and less than 4, incrementing by 1
    //if any of the values of temp are greater than the current max,
    //set that value to max and the max index to that index
    //
    //this will find the greatest value of all the die rolls
    for (int i =0; i<4; i++) {
      if (temp[i]>max) {
        max = temp[i];
        index = i;
      }
    }
    
    //Starts at the player who rolled the highest number then goes clockwise
    //So starts at index and loops 4 times to get the order of the players
    for (int j = index; j<4+index; j++) {
      order.enqueue(players[j%4]);
    }

    //return the correct order of players
    return order;
  }
  
  
    
  
  /**
   * if noMoves, dequeue player and enqueue to back of queue (next player's turn)
   * 
   * @param p (player)
   * @return boolean
   */
  public void nextPlayer(Player p) {
    //if no moves, enqueue player into the linked queue print out a statement on who is the next player
    // and reset has moved so next time they can move
     order.dequeue();
     p.setHasMoved(false);
     order.enqueue(p);
     System.out.println("Entered the nextPlayer method, the next player is " + order.first()); //TESTING
  }
  
  /**
   * if the player can move after rolling, player selects a pawn
   * which gets input into move
   * 
   * @parameter player, pawn (specifc pawn of that player)
   */
  public void move(Player player, Pawn pawn) {
    System.out.println("The player " + player.getColor() + " has selected a move and rolled a " + player.getDieNumber());//TEST------------------------------------
    //if input player can move
    if (player.hasMoved()==false) {
      System.out.println("The player has a possible move."); //Test---------------------------------------------------
      //get the position of the pawn and set to a temp variable position,
      int position = pawn.getPosition();
      
      //If the pawn is already in play then set that position on board 
      //to null since we will be moving the pawn to another location
      if (position >= 0) {     
        System.out.println("The pawn in on the board, in the if statement"); //TEST------------------------------
        // If the pawn is moving into its finishzone, set the pawn's current position to 0 
        // and put it in the finishzone
        if (pawn.getCanFinish() && (pawn.getPosition() >= pawn.getFinishPosition())) {
          System.out.println("The pawn is going into the finish zone.");//TEST-----------------------------------------
            board.getBoard()[position] = null; //set the current position of the pawn to 0
            pawn.setPosition(-2);
            nextPlayer(player);
        }
        else { //otherwise, it is like any other move, whether the canFinish is true or not
          System.out.println("The pawn is on the board but is not entering the finish zone");//-------------TEST-------------
          // check to see if the location you are moving to is occupied if it is, invoke the trouble method only if
          // the pawn is not yours and set the position of the pawn to that position
          if (board.getPawn(pawn.getPosition() + player.getDieNumber())!=null) {
            System.out.println("The position the pawn is moving into is occupied"); //TEST-----------------------------------
            System.out.println("the color of the pawn already there is the same as the player's: " + board.getPawn(pawn.getPosition() + player.getDieNumber()).getColor() == player.getColor()); //TEST------------
            if (board.getPawn(pawn.getPosition() + player.getDieNumber()).getColor() != player.getColor()){
              System.out.println("The trouble method will be invoked");//TEST-----------------------------------------
              trouble(board.getPawn(pawn.getPosition() + player.getDieNumber()));
              board.setPosition((pawn.getPosition() + player.getDieNumber()), pawn);
              //Then update the pawn's position to where it is nowthe pawn into its new position
              pawn.setPosition(pawn.getPosition() + player.getDieNumber());
              nextPlayer(player);
            }
            else {
              System.out.println("Cannot move pawn as it will land on another of your pawns already in play");
            }
          }
          else { //the position is not occupied
            System.out.println("the position the pawn is moving to is not occupied");//TEST_----------------------------------
            board.getBoard()[position] = null; //set the current position of the pawn to 0
            board.setPosition((pawn.getPosition() + player.getDieNumber()), pawn);
            //Then update the pawn's position to where it is nowthe pawn into its new position
            pawn.setPosition(pawn.getPosition() + player.getDieNumber());
            nextPlayer(player);
          }
        }
      }
        
      
      //if the pawn's position is not on the board and the player rolled a 6 or a 1
      else {
        System.out.println("The pawn is not on the board.");//TEST--------------------------------------------------------
        if (position == -1) {
          System.out.println("The pawn is in the homezone");//TEST-------------------------------------------------------
          System.out.println("The player rolled a 1 or a 6: " + (player.getDieNumber()==6) + (player.getDieNumber()== 1));//TEST----
          if (player.getDieNumber() == 6 || player.getDieNumber() == 1) {
            
            //if the position is not occupied, move the pawn into that position and tell the pawn and the board
            // where the pawn is, also set the hasMoved boolean to true
            if (board.getPawn(pawn.getStartPosition())==null) {
              pawn.setPosition(pawn.getStartPosition());
              board.setPosition(pawn.getStartPosition(), pawn);
              System.out.println(player.inHome());
              nextPlayer(player);
            }
        
            // if the position you are moving to is occupied, if it is then invoke the trouble method 
            // which will make that square in the board null and put that pawn back to its homezone if it is not 
            // your own pawn
            else {
              if (board.getPawn(pawn.getStartPosition()).getColor() != player.getColor()) {
                trouble(board.getPawn(pawn.getStartPosition()));
                board.setPosition(pawn.getStartPosition(), pawn); //set position on board to the new pawn
                pawn.setPosition(pawn.getStartPosition()); //let the pawn know that it is now at the start position
                nextPlayer(player); //the player has moved
                System.out.println(player.inHome());
              }
              else
                System.out.println("Cannot move this pawn as it will land on another of your pawns."); //---------------------------------------------
            }
          }
          else {
            System.out.println("Please choose another pawn to move, pawns cannot move from home onto the board unless "
                               + "you roll a 1 or a 6.");
          }
        }
        else {
        System.out.println("The pawn is in the finish zone and cannot be moved.");
        }
      }
      
    }
    else { //go to next player if you have already moved or you have no moves
      nextPlayer(player);
    }
  }
  
  /**
   * trouble method invokes the trouble function of the game, 
   * in which if a pawn lands on another pawn upon moving (both
   * pawns occupy same position on the board), the pawn that
   * was previously in that position gets sent back to home zone
   * 
   * @param p1 (pawn landed on)
   */
  public void trouble(Pawn p1) {
    
    //set position on board of the pawn that was landed on to null)
    board.setPosition(p1.getPosition(), null);
    
    p1.setPosition(-1); //returns the pawn back to home
    p1.setCanFinish(); //resets the canFinish variable of the pawn to false
    
    //print message explaining what happened to the pawn sent home
    System.out.println("Player " + p1.getColor() + "'s pawn got sent to home");
  }
      
  /**
   * this method uses PriorityQueue/MaxHeap to create a
   * leaderboard of players ranked by how many pawns they have
   * in the finish zone (from leading player to losing player)
   *
   * @return string
   */
  public String winning() {
    
    //initialize new PriorityQueue of Players
    //that decides priority based upon
    //how many pawns are in the finish zone
    //for each player (overrode compareTo in Player
    //to use here)
    pq = new PriorityQueue<Player>();
    
    //enqueue each player
    pq.enqueue(blue);
    pq.enqueue(red);
    pq.enqueue(yellow);
    pq.enqueue(green);
    
    //initialize empty string
    String s = "";
    
    //for each player in the priorityqueue,
    //dequeue (will dequeue them in ranked order)
    //and add to the string
    for (int i =0; i<4; i++) {
      s += ("<html><div style='text-align: left;'><font color=#FFD700 size=4><br>" + pq.dequeue().toString() + "<br>");
    }
    
    //return the ranked string leaderboard
    return s;
  }
      
  /**
   * isWinner method determines if any of the players has won
   * and utilizes the returned boolean to determine whether or not to end game
   * 
   * @return boolean
   */
   public boolean isWinner() {
    
     //if any of the players has 4 pawns in the finish zone, they have won
     //so return true
    if (blue.inFinish() == 4 || red.inFinish() == 4 || green.inFinish() == 4
          || yellow.inFinish() == 4) {
      return true;
    }
    //otherwise, return false
    return false;
   }
  
   //FOR TESTING PURPOSES
   public Player getTurn() {
     return order.first();
   }
   
   public int checkQueue() {
     return order.size();
   }
   
   
  /*
   * main method for testing purposes
   */
  public static void main(String[] args) { 
    PlayGame gm = new PlayGame();
    System.out.println(gm.orderPlay("Nicole", "", "mon", ""));
    System.out.println("The number of names in the queue (4): " + gm.checkQueue()); //4
    
    Player p = gm.getTurn(); //will vary
    System.out.println(p); //will print out how many pawns that player has in the finishzone (0)
    p.rollDie();
    System.out.println("The player rolled a " + p.getDieNumber() + "\n"); // an int between 1-6
    p.getPawnArray()[0].setPosition(-2); //set it so that player has 1 pawn in the finishzone
    System.out.println("The current rankings: (There should be one player with 1 pawn in the finishzone)");
    System.out.println(gm.winning()); //now that player should be winning
    
    gm.nextPlayer(p);
    
    Player q = gm.getTurn();
    System.out.println(q);
    q.rollDie();
    System.out.println("The player rolled a " + q.getDieNumber() + "\n");
    q.getPawnArray()[0].setPosition(-2); //set it so that player has 1 pawn in the finishzone
    System.out.println("The current rankings: (There should be two players with 1 pawn each in the finishzone)");
    System.out.println(gm.winning());
    
    q.getPawnArray()[1].setPosition(-2);
    q.getPawnArray()[2].setPosition(5);
    System.out.println("The current rankings: (There should be one player with 2 pawns in the finish and 1 player with 1)");
    System.out.println(gm.winning());
    System.out.println("Has anyone won the game yet? (FALSE) " + gm.isWinner());
//    q.getPawnArray()[2].setPosition(-2);
//    q.getPawnArray()[3].setPosition(-2);
//    System.out.println(gm.winning());
//    System.out.println("Has anyone won the game yet? (TRUE)" + gm.isWinner());
    gm.move(q,q.getPawnArray()[2]);
    System.out.println("The players pawn has been moved from 5 to " + q.getPawnArray()[2].getPosition() +
                       " (should match 5 + " + q.getDieNumber() + " = current position)");
  }
  
}
