//*************************************************
//  Leslie Gates
//  CS230 Final Project
//  Pawn.java
//
//  Class that creates the pawn object with its
//  color and position. The color is limited to the
//  four colors.
//  TESTED
//*************************************************

import java.util.*;
import javafoundations.*;

public class Pawn {
  
  //instance variables
  String color; //color of pawn
  int position; //current position on board
  int startPosition; //start position
  int finishPosition; //finish position
  boolean canFinish; //checks to see if the pawn can go into the finish zone once it completes a circuit
  String[] colors = {"blue", "red", "yellow", "green"}; //valid colors
  
  /**
   * Constructor that takes in a string color and sets positions based upon color
   *
   * @param color (a string taken from player)
   */
  public Pawn(String color) {
    
    //Only set the color if the color is valid, otherwise print error message
    if (isColor(color))  
      this.color = color;
    
    else {
      System.out.println("NOT A VALID COLOR " +color+ ". PLEASE ASSIGN A COLOR within the group: RED, GREEN, BLUE or YELLOW.");
    }
    canFinish = false; //when first created, pawn cannot be in finish zone so set false
    position = -1; //position starts off as -1 (represents home zone)
    startPosition = sPosition(color); //find start position (helper method) of pawns of that color
    if (startPosition != 0)
      finishPosition = startPosition-1; //finish position is one before start position (goes around board-1)
    else //for the blue color case where the finish position is 39 (not -1)
      finishPosition = 39;
  }
  
  
  //****************************************************************************
  //  HELPER FUNCTIONS:
  //   isColor() - Checks to see if the color is valid. 
  //   sPosition() - Calculates the starting position of the pawn based off color
  //****************************************************************************
  
  /**
   * helper method isColor checks if the color input is one of the four valid colors
   * 
   * @param pcolor (string that takes in constructor input color
   * @return boolean
   */
  private boolean isColor(String pcolor) {
    
    //for int i equals 0, less than 4, increment by 1
    //if any color at index i of valid colors array equals the input color
    //return true
    for (int i =0; i<4; i++) {
      if (colors[i]==pcolor)
        return true;
    }
    //otherwise, return false
    return false; //if not a valid color
  }
  
  /**
   * helper method sPosition finds the starting position of the pawn
   * on the board based off of its color
   * 
   * @param color (string input)
   * @return int position of pawn on board array
   */
  private int sPosition(String color) {
    //No need to check if color is valid because it is checked first in the contructor
    int index = -1;
    
    //for int i equals 0, i is less than 4, increment by 1,
    //if the color at index i of the valid colors array equals the input color,
    //set variable index to i (colors array is in correct order of board)
    for (int i =0; i<4; i++) {
      if (colors[i]==color)
        index = i;
    }
    //return the set index multiplied by 10, (this will calculate the
    //correct start position for the color relative to the board, which has 10
    //spaces per color
    return index*10;
  }
  
  //****************************************************************************
  //  Method to deal with color, getter 
  //  No way to set a new color.
  //****************************************************************************
  public String getColor() {
    return color;
  }


  
  //****************************************************************************
  //  Methods to deal with position, getter and setter
  //  May need to check to see if the new position is valid.
  //****************************************************************************
  
  /*
   * getter method that gets current position of pawn
   * 
   * @return int position
   */
  public int getPosition() {
    return position;
  }
  
  /*
   * setter method that sets new position of pawn
   * 
   * @param newPos (int position pawn is moved to)
   */
  public void setPosition(int newPos) {
    
    //if the new position is valid and
    //doesn't go completely around the board, set position to new position
    if (newPos >= -2 && newPos<40)
      position = newPos; 
    
    //otherwise, if the position goes around/past the board,
    //set position as remainder 42 so it is still valid, and set
    //canFinish to true
    else {
      position = newPos%40;
      canFinish = true;
    }
  }
  
  //****************************************************************************
  //  Methods to deal with the start and finish position.
  //  Only includes getters, you are not allowed to change them once the pawn
  //  is created.
  //****************************************************************************
  
  /*
   * getter method for start position
   * 
   * @return int startPosition
   */
  public int getStartPosition() {
    return startPosition;
  }
  
  public int getFinishPosition() {
    return finishPosition;
  }
  
  /*
   * getter method; Checks to see if the pawn can finish
   * 
   * @return boolean
   */
  public boolean getCanFinish() {
    return canFinish;
  }
  
  /*
   * setter method for canFinish
   * Only for use when the pawn gets landed on and sent home (Trouble)
   * 
   * @return void, set canFinish to false
   */
  public void setCanFinish() {
    canFinish=false;
  }
  
  /**
   * toString method for printing nicely
   * 
   * @return String with color and position of pawn
   */
  public String toString() {
    return "The " + color + " pawn is at position " + position;
  }
  
  /**
   * main method for testing
   */
  public static void main(String[] args) { 
    Pawn p1 = new Pawn("red");
    Pawn p2 = new Pawn("blue");
    Pawn p3 = new Pawn("green");
    Pawn p4 = new Pawn("purple"); //Should print a line saying it is not a valid color
    
    System.out.println("For the red player: ");
    System.out.println("The initial position of a red pawn is: (-1) " + p1.getPosition());
    p1.setPosition(p1.getStartPosition());
    System.out.println("The position of a red pawn after it is moved to its start position: (10) " + p1.getPosition());
    System.out.println("The start position of a red pawn is: (10) " + p1.getStartPosition());
    System.out.println("The finish position of a red pawn is: (9) " + p1.getFinishPosition());
    System.out.println("Can the pawn go into the finish zone? (FALSE) " + p1.getCanFinish());
    p1.setPosition(p1.getPosition()+35);
    System.out.println("The red pawn is moved 35 spaces forwards.");
    System.out.println("The position of a red pawn after it is moved: (5) " + p1.getPosition());
    System.out.println("Can the pawn go into the finish zone? (TRUE) " + p1.getCanFinish());
    
    //Testing for the blue color's case where the finish position is calculated differently
    System.out.println("For the blue player: ");
    System.out.println("The initial position of a blue pawn is: (-1) " + p2.getPosition());
    p2.setPosition(p2.getStartPosition());
    System.out.println("The position of a red pawn after it is moved to its start position: (0) " + p2.getPosition());
    System.out.println("The start position of a red pawn is: (0) " + p2.getStartPosition());
    System.out.println("The finish position of a red pawn is: (39) " + p2.getFinishPosition());
    
  }
  
}
