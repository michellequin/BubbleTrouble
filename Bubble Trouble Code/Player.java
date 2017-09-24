/*
 * Player.java
 * Written by Michelle Quin
 * 
 * CS 230 Final Project
 * Bubble Trouble by Isabel, Leslie, and Michelle
 * 
 * This class creates a Player object which is associated
 * with a single player of the game (referred to by color).
 * Player has the ability to roll Die and move Pawn (each
 * Player has 4 Pawns). It also keeps track of how many Pawns
 * are currently in play, in home, or in the finish zone.
 *
 */

public class Player implements Comparable<Player>{
  
  //instance variables (protected for access from other classes)
  protected String color;
  protected String name;
  protected Die die = new Die(); //create new die object
  protected Pawn[] pawnArray = new Pawn[4]; //array of 4 pawns
  protected int dieNumber;
  protected boolean hasMoved;
  protected boolean rolled=false;
  
  /**
   * constructor (takes in String color)
   * 
   * @param color
   */
  public Player(String color) {
    
    //set input color to instance variable color
    this.color = color;
    name = color; //set name to color if no name provided
    hasMoved = false;
    
    //for int i equals 0, i is less than length of pawnArray, increment by 1
    for (int i = 0; i < pawnArray.length; i++) {
      
      //create a new pawn of input color and set to pawnArray index i
      pawnArray[i] = new Pawn(color); //start Pawn position at home
      //(not on board/at home zone = position of -1)
    } 
  }
  
  /**
   * constructor (takes in String color and String name)
   * 
   * @param color and name
   */
  public Player(String color, String name) {
    
    //set input color to instance variable color
    this.color = color;
    this.name = name; //set name to input name
    
    //for int i equals 0, i is less than length of pawnArray, increment by 1
    for (int i = 0; i < pawnArray.length; i++) {
      
      //create a new pawn of input color and set to pawnArray index i
      pawnArray[i] = new Pawn(color); //start Pawn position at home
      //(not on board/at home zone = position of -1)
    }
  }
  
  /**
   * getter method for color
   *
   * @return string color
   */
  public String getColor(){
    return color;
  }
  
  /**
   * getter method for PawnArray
   * 
   * @return Pawn[] pawnArray
   */
  public Pawn[] getPawnArray(){
    return pawnArray;
  }
  
  
   /**
   * getter method for rolled
   * 
   * @return boolean rolled
   */
  public boolean getRolled(){
    return rolled;
  }
  
  /**
   * getter method for name
   * 
   * @return String name
   */
  public String getName(){
    return name;
  }
  
  /**
   * roll Die and set dieNumber to rolled number
   * 
   */
  public void rollDie() {
   
    dieNumber = die.roll();
    rolled=true;
  }
  
  /**
   * getter method for dieNumber
   * 
   * @return int dieNumber
   */
  public int getDieNumber() {
    
    return dieNumber;
  }
  
  /**
   * number of pawns in play
   * 
   * @return int of number of pawns on board
   */
  public int inPlay() {
    
    //initialize int play count at 0
    int play = 0;
    
    //for int i starting at 0, less than length of pawnArray,
    //incrementing by 1
    for (int i = 0; i < pawnArray.length; i++) {
      
      //if the pawn is on the board/not in home zone (-1) or finish zone (-2)
      if (pawnArray[i].getPosition() > -1) {
        //increment number of pawns in play
        play += 1;
      }
    }
    //return updated play count
    return play;
  }
  
  /*
   * number of pawns in finish zone (-2)
   * 
   * @return int of pawns that are in finish zone
   */
  public int inFinish() {
    
    //initialize int finish count at 0
    int finish = 0;
    
    //for int i starting at 0, i is less than length of pawnArray,
    //incrementing by 1
    for (int i = 0; i < pawnArray.length; i++) {
      
      //if it is in the finish zone it's position is -2
      if (pawnArray[i].getPosition() == -2) {
        //increment finish by 1
        finish += 1;
      }
    }
    //return updated finish count
    return finish;
  }  
  
   /*
   * number of pawns in home zone (position -1)
   * 
   * @return int of pawns that are in home zone
   */
  public int inHome() {
    
    //initialize int home count at 0
    int home = 0;
    
    //for int i starting at 0, i is less than length of pawnArray,
    //incrementing by 1
    for (int i = 0; i < pawnArray.length; i++) {
      
      //if it is in the home zone (-1)
      if (pawnArray[i].getPosition() == -1) {
        //increment home by 1
        home += 1;
      }
    }
    //return updated home count
    return home;
  }
  
  //-----------------------------------------------------------------------------------------------------------------------
  // Returns one pawn from the homezone
  //-----------------------------------------------------------------------------------------------------------------------
  public Pawn oneHome() {
    for (int i = 0; i < pawnArray.length; i++) {
      if (pawnArray[i].getPosition() == -1)
        return pawnArray[i];
    }
    return null;
  }
  //-----------------------------------------------------------
  // Getter and setter for hasMoved
  //--------------------------------------------------------------
  public boolean hasMoved() {
    return hasMoved;
  }
  
  public void setHasMoved(boolean b) {
    hasMoved = b;
  }
  
  
  /**
   * if there are no moves possible this turn, return true
   * otherwise, return false
   * 
   * @return boolean
   */
  public boolean noMoves() {
    
    //if there are 0 pawns in play
    if (inPlay() == 0) {
      
      //and if the die number does not equal 6 or 1, meaning none can move out of home, return true
      if (getDieNumber() != 6 && getDieNumber() != 1) {
        return true;
      }
    } //otherwise, return false
    return false;
  }
  
  /**
   * override compareTo from Comparable, comparing the
   * number in finish zone between two players
   *
   * @param p2 (other Player)
   * @return int (negative, 0, positive)
   */
  public int compareTo(Player p2) {
    return (this.inFinish()- p2.inFinish());
  }
  
  /**
   * toString method for printing purposes
   * 
   * @return string
   */
  public String toString() {
   return  name + " has " + inFinish() + " pawns in finish.\n";
  }
    
  }


  
    