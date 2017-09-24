//*************************************************
//  Leslie Gates
//  CS230 Final Project
//  Board.java
//
//  Creates the board of the game. The board of the
//  game includes only the position in the circle.
//  The homezone and finishzones are separate static
//  pictures that are not related.
//
//  Needs testing?
//*************************************************

import java.util.*;
import javafoundations.*;

public class Board {
  private Pawn[] board;
  
  public Board() {
    board = new Pawn[40]; // does not includes finish zones or home zones
  }
  
  
  /**
   * Returns the Pawn [], which is needed to update where the pawns are
   * @return Pawn[]
   */
  public Pawn[] getBoard()  {
    return board;
  }
 
  /**
   * Helper function to determine if there is a pawn in that location
   * @param int position
   * @return boolean
   */
  private boolean checkPosition(int position) {
    return board[position]!=null;
  }
  
  /**
   * Returns the pawn in that position, useful for the trouble method
   * @param int position
   * @return Pawn
   */
  public Pawn getPawn(int position) {
    if (checkPosition(position)) {
      return board[position];
    }
    else
      return null;
  }
  
  
  /**
   * Allows you to set a position to have a pawn or be null
   * @param int position
   * @param Pawn
   */
  public void setPosition(int position, Pawn p) {
    board[position] = p;
  }
  
}
