/*
 * ComputerPlayer.java
 * Written by Michelle Quin
 * 
 * CS 230 Final Project
 * Bubble Trouble by Isabel, Leslie, and Michelle
 * 
 * This class creates a ComputerPlayer object which inherits
 * from the Player class. It automatically rolls when it is 
 * its turn and will move a pawn out of home into start
 * if possible. Otherwise, it makes a random possible move (this
 * automatic determination will occur in PlayGame class).
 * This is for computer players only.
 *
 */

/*
 * class extends Player but makes its own decisions as to which
 * pawn to move during a turn (no user input)
 */
public class ComputerPlayer extends Player {
  
  /**
   * constructor that takes in a color
   * 
   * @param color
   */
  public ComputerPlayer(String color) {
    
    //call constructor from player using super
    super(color);
    
  }
  
  /**
   * HELPER METHOD: find indices of pawns in play
   * and returns an array of the indices
   * 
   * @return int[]
   */
  private int[] pawnIndex() {
    
    //initialize count at 0
    int count = 0;
    //initialize int array of indices of size of pawns in play
    int[] indices = new int[inPlay()];
    
    //for int i equals 0 and i is less than the length of pawnArray,
    //incrementing by 1
    for (int i = 0; i < pawnArray.length; i++) {
      
      //if pawnArray at index i's position is in play
      if (pawnArray[i].getPosition() > -1) {
        
        //set indices at count to i, increment count
        indices[count] = i;
        count++;
      }
    }
    //return indices
    return indices;
  }
  
  /**
   * HELPER METHOD: find if there is another pawn in the
   * start space -- return true if there is, false if not
   * 
   * @return boolean
   */
  private boolean startOccupied() {
    
    //for int i equals 0, i is less than length of pawnArray,
    //incrementing by 1
    for (int i = 0; i < pawnArray.length; i++) {
      
      //if pawnArray at index i's position equals its start position,
      //return true because start is occupied by that pawn
      if (pawnArray[i].getPosition() == pawnArray[i].getStartPosition())
        return true;
    }
    //otherwise, return false
    return false;
  }
            
  
  /**
   * pick the pawn to move by priority
   * 
   * @return Pawn
   */
  public Pawn pickPawn() {
    
    //start pick off at index 0
    int pick = 0;
    
    //Priority 1:
    //if there is a pawn at the start position, select that pawn
    //and get its index, then set the index to pick variable
    if (startOccupied()) {
      for (int i = 0; i < pawnArray.length; i++) {
        if (pawnArray[i].getPosition() == pawnArray[i].getStartPosition())
          pick = i;
      }
    }
    
    //Otherwise, if start is not occupied...
    else {
      
      //Priority 2:
      //if there are pawns in home, and a 6 is rolled,
      //move one pawn at home out to start (get index of
      //one of the pawns and return it immediately
      if (inHome() > 0) {
        
        if (getDieNumber() == 6) {
            for (int i = 0; i < pawnArray.length; i++) {
              if (pawnArray[i].getPosition() == -1)
                return pawnArray[i];
            }
        }
      }
      
      //else if there are no pawns in home
      else {
        
        //Priority 3:
        //if there is only one pawn in play, choose that one
        if (inPlay() == 1) {
          pick = pawnIndex()[0];
        }
        
        //if there is more than one pawn in play
        else {

          for (int i = 0; i < pawnArray.length; i++) {
            
            //Priority 4:
            //if any of the pawns can reach the finish zone in this turn,
            //return that pawn
            if (pawnArray[i].getCanFinish()) {
              return pawnArray[i];
            }
            
            //Priority 5:
            //else if none of the pawns can reach the finish zone
            //return a random playable pawn
            else {
              int random = (int) (Math.random() * pawnIndex().length);  
              pick = pawnIndex()[random-1]; 
            }
          }
        }
      }
    }
    //return pawn at index pick
    return pawnArray[pick];
  }
}
