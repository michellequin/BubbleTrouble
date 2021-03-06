//*************************************************
//  Isabel D'Alessandro
//  CS230 Final Project
//  PlayPanel3.java
//
//  Class that creates play panel for the game. It includes the
//  board as well as all the pawns and players. It invokes a 
//  PlayGame class to create the players and pawns. It also
//  enable/disables buttons based on what action the player
//  is supposed to take.
//
//  UNTESTED, COMPILES
//*************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFrame.*;
import java.lang.*;
import javax.swing.JOptionPane;

public class PlayPanel extends JPanel{
  //instance variables
  public PlayGame game;
  private ImageIcon redhome0,redhome1,redhome2,redhome3,redhome4,greenhome0,greenhome1,greenhome2,greenhome3,greenhome4,
    yellowhome0,yellowhome1,yellowhome2,yellowhome3,yellowhome4,bluehome0,bluehome1,bluehome2,bluehome3,bluehome4,redOnRed,
    redOnYellow,redOnBlue,redOnGreen,redOnWhite,greenOnRed,greenOnGreen,greenOnYellow,greenOnBlue,greenOnWhite,
    blueOnRed,blueOnBlue,blueOnGreen,blueOnYellow,blueOnWhite,yellowOnYellow,yellowOnBlue,yellowOnGreen,yellowOnRed,
    yellowOnWhite,playerturnicon,actionbuttonicon,homeicon,bluesquare,greensquare,redsquare,yellowsquare,diesquare,
    redbordersquare,yellowbordersquare,greenbordersquare,bluebordersquare,yellowS,greenS,blueS,redS,die1icon,die2icon,
    die3icon,die4icon,die5icon,die6icon,diespacericon,playbuttonicon, whitesquare;
  private JLabel playersturn,turn,actionbutton,action,homename,die1,die2,die3,die4,die5,die6,dieroll,dicon,diespacer,rolledA, rank;
  private String redturn,greenturn,blueturn,yellowturn,roll,click,rolledAString;
  private JButton redhome,greenhome,yellowhome,bluehome,playbutton;
  private JButton[][] buttons;
  private ButtonListener listener;
  
  

  //Constructor
  // The PlayPanel takes in a PlayGame so that it can find the names of each player
  // from the setup panel
  public PlayPanel(PlayGame g){
    this.game=g;
    listener=new ButtonListener();
    setBackground(new Color(100,149,237));
    setLayout(new BorderLayout());
    
    //Creates the top panel which tells you whose turn it is and what that player should do (roll die, select pawn)
    JPanel toppanel = new JPanel();
    playbuttonicon=createImageIcon("playbutton.gif","");
    playbutton=new JButton(playbuttonicon);
    add(toppanel,BorderLayout.NORTH);
    playerturnicon = createImageIcon("playersturnbutton.png","");
    playersturn = new JLabel(playerturnicon);
    redturn= new String("<html><font color=#FFD700 size=6>Red </font></html>");
    greenturn= new String("<html><font color=#FFD700 size=6>Green </font></html>");
    blueturn= new String("<html><font color=#FFD700 size=6>Blue </font></html>");
    yellowturn= new String("<html><font color=#FFD700 size=6>Yellow </font></html>");
    toppanel.add(playbutton);
    playbutton.setPreferredSize(new Dimension(100, 100));
    playbutton.addActionListener(listener);
    toppanel.add(playersturn);
    turn = new JLabel("");
    toppanel.add(turn);
    redturn= new String("<html><font color=#FFD700 size=6>Red </font></html>");
    greenturn= new String("<html><font color=#FFD700 size=6>Green </font></html>");
    blueturn= new String("<html><font color=#FFD700 size=6>Blue </font></html>");
    yellowturn= new String("<html><font color=#FFD700 size=6>Yellow </font></html>");
    
    actionbuttonicon = createImageIcon("actionbutton.png","");
    actionbutton = new JLabel(actionbuttonicon);
    roll= new String("<html><font color=#FFD700 size=5>Click on the  <br> die to roll </font></html>");
    click= new String("<html><font color=#FFD700 size=5>Click on one<br> of your pieces<br> to make a move </font></html>");
    toppanel.add(actionbutton);
    action= new JLabel("");
    toppanel.add(action);
    toppanel.setBackground(new Color(100,149,237));
    
    die1icon=createImageIcon("die1.gif","");
    die1=new JLabel(die1icon);

    die2icon=createImageIcon("die2.gif","");
    die2=new JLabel(die2icon);

    die3icon=createImageIcon("die3.gif","");
    die3=new JLabel(die3icon);

    die4icon=createImageIcon("die4.gif","");
    die4=new JLabel(die4icon);

    die5icon=createImageIcon("die5.gif","");
    die5=new JLabel(die5icon);

    die6icon=createImageIcon("die6.gif","");
    die6=new JLabel(die6icon);
    
    diespacericon=createImageIcon("diespacer.gif","");
    diespacer=new JLabel(diespacericon);
    
    
    rolledAString="<html><font  size=5>You rolled a:  </font></html>";
    rolledA=new JLabel(" ");
    toppanel.add(rolledA);
    dicon=new JLabel(diespacericon);
    toppanel.add(dicon);
    dieroll=new JLabel("");
    
    
    //Creates the center panel which is the board and puts it on the left
    add(makeCenterPanel(),BorderLayout.WEST);
    
    //Creates the panel that holds all the pawns in the players' homezones
    JPanel homePanel= new JPanel();
    add(homePanel,BorderLayout.CENTER);
    homePanel.setBackground(new Color(100,149,237));
    homePanel.setBackground(new Color(100,149,237));
    homePanel.setLayout(new GridLayout(7,1,-5,-5));
    homePanel.setPreferredSize(new Dimension(100,100));
    //Makes it so the homezone are clickable 
    redhome= new JButton();
    greenhome=new JButton();
    yellowhome=new JButton();
    bluehome=new JButton();
    homeicon= createImageIcon("homehome.png","");
    homename=new JLabel(homeicon);
    redhome.setPreferredSize(new Dimension(50, 50));
    homePanel.add(homename);
    homePanel.add(redhome);
    homePanel.add(greenhome);
    homePanel.add(yellowhome);
    homePanel.add(bluehome);
    redhome.addActionListener(listener);
    greenhome.addActionListener(listener);
    bluehome.addActionListener(listener);
    yellowhome.addActionListener(listener);
    //Creates all the icons that show how many pawns are in each home. Since it is static
    // we need a new pic for each time a pawn leaves the homezone
    redhome0=createImageIcon("redhome.png","");
    bluehome0=createImageIcon("bluehome.png","");
    greenhome0=createImageIcon("greenhome.png","");
    yellowhome0=createImageIcon("yellowhome.png","");
    redhome1=createImageIcon("redhome1.png","");
    redhome2=createImageIcon("redhome2.png","");
    redhome3=createImageIcon("redhome3.png","");
    redhome4=createImageIcon("redhome4.png","");
    bluehome1=createImageIcon("bluehome1.png","");
    bluehome2=createImageIcon("bluehome2.png","");
    bluehome3=createImageIcon("bluehome3.png","");
    bluehome4=createImageIcon("bluehome4.png","");
    greenhome1=createImageIcon("greenhome1.png","");
    greenhome2=createImageIcon("greenhome2.png","");
    greenhome3=createImageIcon("greenhome3.png","");
    greenhome4=createImageIcon("greenhome4.png","");
    yellowhome1=createImageIcon("yellowhome1.png","");
    yellowhome2=createImageIcon("yellowhome2.png","");
    yellowhome3=createImageIcon("yellowhome3.png","");
    yellowhome4=createImageIcon("yellowhome4.png","");
    //Sets up the initial homezones with 4 pawns in each players home.
    redhome.setIcon(redhome4);
    greenhome.setIcon(greenhome4);
    yellowhome.setIcon(yellowhome4);
    bluehome.setIcon(bluehome4);
    
    //Creates the leaderboard of who is winning in the game based on who
    // has the most pawns in the finishzone. Located on the right side
    // of the tab.
    JPanel leaderboard= new JPanel();
    add(leaderboard,BorderLayout.EAST);
    ImageIcon bluespacericon=createImageIcon("bluespacer.png","");
    JLabel bluespacer2=new JLabel(bluespacericon);
    rank = new JLabel();
    leaderboard.add(rank);
    leaderboard.add(bluespacer2);
    leaderboard.setBackground(new Color(100,149,237));
    
    
    //Make fish icons which serve as pawns, requires that each pawn have different backgrounds so they
    // match the boards
    yellowOnYellow=createImageIcon("yellowOnYellow.png","");
    yellowOnRed=createImageIcon("yellowOnRed.png","");
    yellowOnBlue=createImageIcon("yellowOnBlue.png","");
    yellowOnGreen=createImageIcon("yellowOnGreen.png","");
    yellowOnWhite=createImageIcon("yellowOnWhite.png","");
    
    blueOnYellow=createImageIcon("blueOnYellow.png","");
    blueOnRed=createImageIcon("blueOnRed.png","");
    blueOnBlue=createImageIcon("blueOnBlue.png","");
    blueOnGreen=createImageIcon("blueOnGreen.png","");
    blueOnWhite=createImageIcon("blueOnWhite.png","");
    
    redOnYellow=createImageIcon("redOnYellow.png","");
    redOnRed=createImageIcon("redOnRed.png","");
    redOnBlue=createImageIcon("redOnBlue.png","");
    redOnGreen=createImageIcon("redOnGreen.png","");
    redOnWhite=createImageIcon("redOnWhite.png","");
    
    greenOnYellow=createImageIcon("greenOnYellow.png","");
    greenOnRed=createImageIcon("greenOnRed.png","");
    greenOnBlue=createImageIcon("greenOnBlue.png","");
    greenOnGreen=createImageIcon("greenOnGreen.png","");
    greenOnWhite=createImageIcon("greenOnWhite.png","");
  }
  
  //****************************************************************************
  //  HELPER FUNCTION:
  //   Creates the center panel for the PlayPanel.This includes the board that 
  //   is clickable and is relatable to the board's Pawn[]
  //****************************************************************************
  private JPanel makeCenterPanel () {
    // create centerPanel using GridLayout manager
    JPanel centerPanel = new JPanel();
    //centerPanel.setBackground(Color.blue);
    centerPanel.setLayout(new GridLayout(11, 11, -5, -5)); // rows, cols, hgap, vgap
    
    //Each part of the board is a button, however we've made it so that each quadrent has its own
    // color which is why there are so many icon. Additionally, the start position for each
    // player is indicated by an S. There is also an icon to indicate which buttons are the
    // finish zone area.
    buttons = new JButton[11][11];
    greensquare=createImageIcon("greensquare.png","");
    bluesquare=createImageIcon("bluesquare.png","");
    yellowsquare=createImageIcon("yellowsquare.png","");
    redsquare=createImageIcon("redsquare.png","");
    diesquare=createImageIcon("diesquare.png","");
    whitesquare=createImageIcon("whitesquare.png","");
    greenbordersquare=createImageIcon("greenbordersquare.png","");
    bluebordersquare=createImageIcon("bluebordersquare.png","");
    yellowbordersquare=createImageIcon("yellowbordersquare.png","");
    redbordersquare=createImageIcon("redbordersquare.png","");
    yellowS=createImageIcon("yellowS.png","");
    blueS=createImageIcon("blueS.png","");
    redS=createImageIcon("redS.png","");
    greenS=createImageIcon("greenS.png","");
    
    //Creates all the buttons and initially starts them all off as disabled
    for (int i = 0; i<11; i++) {
      for (int j=0; j<11; j++) {
        buttons[i][j] = new JButton(" ");
        buttons[i][j].setEnabled(false);
        buttons[i][j].setPreferredSize(new Dimension(50, 50));     
        buttons[i][j].setFont(new Font("Serif", Font.ITALIC, 36));
      }
    }
    
   //Sets the upper left quadrent to blue
   for(int i=0;i<5;i++){
      for(int j=0;j<5;j++){
        buttons[i][j].setIcon(bluesquare);
    }
   }
   
   //Sets the lower left quadrant to green
   for(int i=6;i<11;i++){
     for(int j=0;j<5;j++){
       buttons[i][j].setIcon(greensquare);
     }
   }
   
   //Sets the upper right quadrent to red
   for(int i=0;i<5;i++){
     for(int j=6;j<11;j++){
       buttons[i][j].setIcon(redsquare);
     }
   } 
   
   //Sets the lower right quadrent to yellow
   for(int i=6;i<11;i++){
     for(int j=6;j<11;j++){
       buttons[i][j].setIcon(yellowsquare);
     }
   }
  
   boardEnabled(true); //enables the squares that serve are the path the pawns take, center buttons remain disabled
   //sets special button locates to their unique icons (die icon or finishzone icons)
   buttons[5][5].setIcon(diesquare);
   buttons[5][5].addActionListener(listener);
   buttons[5][5].setEnabled(true); //set die button to true so player can roll
   buttons[6][6].setIcon(yellowbordersquare);
   buttons[6][6].setEnabled(true);
   buttons[7][7].setIcon(yellowbordersquare);
   buttons[7][7].setEnabled(true);
   buttons[8][8].setIcon(yellowbordersquare);
   buttons[8][8].setEnabled(true);
   buttons[9][9].setIcon(yellowbordersquare);
   buttons[9][9].setEnabled(true);
   buttons[4][4].setIcon(bluebordersquare);
   buttons[4][4].setEnabled(true);
   buttons[3][3].setIcon(bluebordersquare);
   buttons[3][3].setEnabled(true);
   buttons[2][2].setIcon(bluebordersquare);
   buttons[2][2].setEnabled(true);
   buttons[1][1].setIcon(bluebordersquare);
   buttons[1][1].setEnabled(true);
   buttons[4][6].setIcon(redbordersquare);
   buttons[4][6].setEnabled(true);
   buttons[3][7].setIcon(redbordersquare);
   buttons[3][7].setEnabled(true);
   buttons[2][8].setIcon(redbordersquare);
   buttons[2][8].setEnabled(true);
   buttons[1][9].setIcon(redbordersquare);
   buttons[1][9].setEnabled(true);
   buttons[6][4].setIcon(greenbordersquare);
   buttons[6][4].setEnabled(true);
   buttons[7][3].setIcon(greenbordersquare);
   buttons[7][3].setEnabled(true);
   buttons[8][2].setIcon(greenbordersquare);
   buttons[8][2].setEnabled(true);
   buttons[9][1].setIcon(greenbordersquare);
   buttons[9][1].setEnabled(true);
   buttons[0][0].setIcon(blueS);
   buttons[0][0].addActionListener(listener);
   buttons[10][10].setIcon(yellowS);
   buttons[10][10].addActionListener(listener);
   buttons[10][0].setIcon(greenS);
   buttons[10][0].addActionListener(listener);
   buttons[0][10].setIcon(redS);
   buttons[0][10].addActionListener(listener);
   
    
   //Adds all the buttons to the centerPanel so that it can be added to the main panel
   for(int i=0;i<11;i++){
      for(int j=0;j<11;j++){
        centerPanel.add(buttons[i][j]);
      }
    }
   return centerPanel;
  }
   
  
  //****************************************************************************
  //  HELPER FUNCTION:
  //  createImageIcon allows for an icon to be created and added to the panel
  //  also allows for a description in addition to the picture
  //****************************************************************************
  protected ImageIcon createImageIcon(String path,String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
  }
  
  //****************************************************************************
  //  getBoardPosition takes in a button and returns what position it is
  //  in the board Pawn array from the PlayGame class.
  //  Useful for finding out where a pawn in on the board if you only have
  //  the button.
  //****************************************************************************
  public int getBoardPosition(JButton button){
    int buttoncounter=0;
    for(int i=0;i<11;i++){
      if(button==buttons[0][i]){
        return i;
      }
    }
    for(int i=1;i<11;i++){
      if(button==buttons[i][10]){
        return 10+i;
      }
    }
    for(int i=1;i<11;i++){
      if(button==buttons[10][i]){
        return 20+i;
      }
    }

    for(int i=1;i<10;i++){
      if(button==buttons[i][0]){
        return 30+i;
      }
    }
    return -2;
  }
  
    public void updateBoard(){
    for(int i=0;i<game.getBoard().getBoard().length;i++){
      if(game.getBoard().getBoard()[i]!=null){
        getGUIPosition(i).setIcon(chooseBoardImage((game.getBoard().getBoard()[i].getColor()),i));
        
      }
      else{
        if(getColorZone(i).equals("red")){
          if (i==10)
            getGUIPosition(i).setIcon(redS);
          else
            getGUIPosition(i).setIcon(redsquare);
        }
        else if(getColorZone(i).equals("green")){
          if (i==30)
            getGUIPosition(i).setIcon(greenS);
          else
            getGUIPosition(i).setIcon(greensquare);
        }
        else if(getColorZone(i).equals("blue")){
          if (i==0)
            getGUIPosition(i).setIcon(blueS);
          else
            getGUIPosition(i).setIcon(bluesquare);
        }
        else if(getColorZone(i).equals("yellow")){
          if (i==20)
            getGUIPosition(i).setIcon(yellowS);
          else
            getGUIPosition(i).setIcon(yellowsquare);
        }
        else{
          getGUIPosition(i).setIcon(whitesquare);
        }
     }
  }
    /*
    getGUIPosition(6).setIcon(redsquare);
    getGUIPosition(7).setIcon(redsquare);
    getGUIPosition(8).setIcon(redsquare);
    getGUIPosition(9).setIcon(redsquare);
    */
}

/*
 * helper method that sets occupied pawn positions
 * on the board to enabled so player can click on them
 * after rolling
 * 
 * @param Player 
 */
  private void pawnEnabled(Player p) {
  
    Pawn[] temp = p.getPawnArray();
    for (int i = 0; i < 4; i++) { 
      if (temp[i].getPosition() >= 0)
        getGUIPosition(temp[i].getPosition()).setEnabled(true);   
    }
  }

  /**
   * helper method boardEnabled enables or disables the buttons that serve as the path that the pawns travel on
   * it is used to help indicate to the player what they should be clicking on
   * 
   * @param boolean (either true/false depending on whether you want to enable to disable)
   * @return none
   */
  private void boardEnabled(boolean s) {
  
    for (int i = 0; i<11; i++) {  
      buttons[0][i].setEnabled(s);
      buttons[i][10].setEnabled(s);
      buttons[10][i].setEnabled(s);
      buttons[i][0].setEnabled(s);
    }
  }

  //****************************************************************************
  //  getGUIPosition takes in a integer that is the board index from PlayGame
  //  and returns where on the grid of buttons that corresponds to.
  //  Useful for finding out where a pawn should go on the grid when
  //  moving the pawn and you know where it is supposed to go in the Pawn array
  //  board.
  //****************************************************************************
  public JButton getGUIPosition(int boardindex){
    if(boardindex>=0 && boardindex<=10){
      return buttons[0][boardindex];
    }
    else if(boardindex>=11 && boardindex<=20){
      return buttons[boardindex-10][10];
    }
    else if(boardindex>=21 && boardindex<=30){
      return buttons[10][10-(boardindex-20)];
    }
    else if(boardindex>=31 && boardindex<=39){
      return buttons[0][10-(boardindex-30)];
    }
    System.out.println("why is this returning null for GUI position line 475");
    return null;
  }
  //****************************************************************************
  //  getColorZone takes in an integer that is the boardindex of a pawn,
  //  found from the PlayGame class and figures out what quadrent it is in or if
  //  it is between quadrants and returns the color as a string.
  //****************************************************************************
  public String getColorZone(int boardindex){
    if(boardindex>=0 && boardindex<=4){
      return "blue";
    }
    else if(boardindex==5 || boardindex==15 || boardindex==25 || boardindex==35){
      return "white";
    }
    else if(boardindex>=6 && boardindex<=14){
      return "red";
    }
    else if(boardindex>=16 && boardindex<=24){
      return "yellow";
    }
    else if(boardindex>=26 && boardindex<=34){
      return "green";
    }
    else if(boardindex>=36 && boardindex<=39){
      return "blue";
    }
    return "";
  }
  
  //****************************************************************************
  //  isValidSelection takes in a player and a button that the player clicked on
  //  and checks to see that it is a valid selection.
  //  If the button is considered a finishzone, then it is not a valid selection. 
  //  If the player clicks on another players pawn then it is not a valid selection
  //  If the player selects their own homezone, returns true, otherwise returns false.
  //****************************************************************************
  public boolean isValidSelection(Player p,JButton button){
    if(getBoardPosition(button)!=-2){
      if (game.getBoard().getBoard()[getBoardPosition(button)]==null){
        return false;
      }
      else{
        if(((game.getBoard().getBoard()[getBoardPosition(button)].getColor()).equals(p.getColor()))==false){
          return false;
        }
        return true;
      }
    }
    if(button==redhome && p.getColor().equals("red") && p.inHome()!=0){
      return true;
    }
    if(button==greenhome && p.getColor().equals("green") && p.inHome()!=0){
      return true;
    }
    if(button==bluehome && p.getColor().equals("blue") && p.inHome()!=0){
      return true;
    }
    if(button==yellowhome && p.getColor().equals("yellow") && p.inHome()!=0){
      return true;
    }
    if(button==playbutton){
      return true;
    }
    return false;
  }
  //****************************************************************************
  //  chooseHomeImage takes in a player and updates the player's homezone.
  //  For each player, there are 5 images: 4 pawns, 3 pawns, 2 pawns, 1 pawn and 
  //  no pawns in the homezone. The method checks to see which player it has and
  //  then updates the appropriate homezone.
  //****************************************************************************
  public ImageIcon chooseHomeImage(Player p){
    //for the red player
    if(p.getColor().equals("red")){
      if(p.inHome()==4){
        return redhome4;
      }
      else if(p.inHome()==3){
        return redhome3;
      }
      else if(p.inHome()==2){
        return redhome2;
      }
      else if(p.inHome()==1){
        return redhome1;
      }
      else if(p.inHome()==0){
        return redhome0;
      }
    }
    //for the green player
    else if(p.getColor().equals("green")){
      if(p.inHome()==4){
        return greenhome4;
      }
      else if(p.inHome()==3){
        return greenhome3;
      }
      else if(p.inHome()==2){
        return greenhome2;
      }
      else if(p.inHome()==1){
        return greenhome1;
      }
      else if(p.inHome()==0){
        return greenhome0;
      }
    }
    //for the blue player
    else if(p.getColor().equals("blue")){
      if(p.inHome()==4){
        return bluehome4;
      }
      else if(p.inHome()==3){
        return bluehome3;
      }
      else if(p.inHome()==2){
        return bluehome2;
      }
      else if(p.inHome()==1){
        return bluehome1;
      }
      else if(p.inHome()==0){
        return bluehome0;
      }
    }
    //for the yellow player
    else if(p.getColor().equals("yellow")){
      if(p.inHome()==4){
        return yellowhome4;
      }
      else if(p.inHome()==3){
        return yellowhome3;
      }
      else if(p.inHome()==2){
        return yellowhome2;
      }
      else if(p.inHome()==1){
        return yellowhome1;
      }
      else if(p.inHome()==0){
        return yellowhome0;
      }
    }
    //if it is not any of the player (shouldn't ever happen) returns a null
    return null;
  }
  
  
  //****************************************************************************
  //  chooseBoardImage takes in a player and a integer that is the boardindex
  //  and returns the appropriate image for the pawn.
  //
  //  It first checks what color the player is and then finds out which zone the
  //  pawns will be going to. Based off what color zone the pawn will be in, it
  //  has a unique icon that will be placed at that index in another method. 
  //****************************************************************************
 public ImageIcon chooseBoardImage(String color,int boardindex){
    //for the red player in any color zone
    if(color.equals("red")){
      if(getColorZone(boardindex).equals("red")){
        return redOnRed;
      }
      else if(getColorZone(boardindex).equals("green")){
        return redOnGreen;
      }
      else if(getColorZone(boardindex).equals("blue")){
        return redOnBlue;
      }
      else if(getColorZone(boardindex).equals("yellow")){
        return redOnYellow;
      }
      else if(getColorZone(boardindex).equals("white")){
        return redOnWhite;
      }
    }
    //for the green player in any color zone
    else if(color.equals("green")){
      if(getColorZone(boardindex).equals("red")){
        return greenOnRed;
      }
      else if(getColorZone(boardindex).equals("green")){
        return greenOnGreen;
      }
      else if(getColorZone(boardindex).equals("blue")){
        return greenOnBlue;
      }
      else if(getColorZone(boardindex).equals("yellow")){
        return greenOnYellow;
      }
      else if(getColorZone(boardindex).equals("white")){
        return greenOnWhite;
      }
    }
    //for the blue player in any color zone
    else if(color.equals("blue")){
      if(getColorZone(boardindex).equals("red")){
        return blueOnRed;
      }
      else if(getColorZone(boardindex).equals("green")){
        return blueOnGreen;
      }
      else if(getColorZone(boardindex).equals("blue")){
        return blueOnBlue;
      }
      else if(getColorZone(boardindex).equals("yellow")){
        return blueOnYellow;
      }
      else if(getColorZone(boardindex).equals("white")){
        return blueOnWhite;
      }
    }
    //for the yellow player in any color zone
    else if(color.equals("yellow")){
      if(getColorZone(boardindex).equals("red")){
        return yellowOnRed;
      }
      else if(getColorZone(boardindex).equals("green")){
        return yellowOnGreen;
      }
      else if(getColorZone(boardindex).equals("blue")){
        return yellowOnBlue;
      }
      else if(getColorZone(boardindex).equals("yellow")){
        return yellowOnYellow;
      }
      else if(getColorZone(boardindex).equals("white")){
        return yellowOnWhite;
      }
    }
    //otherwise if the player does not have a valid color (should never happen), returns null
    return null;
  }

  //****************************************************************************
  // Finds out what button was clicked and takes an appropriate action based on
  // which button was clicked and if it matches the player.
  //
  // First checks to see if anyone has won yet, if not continues the game.
  // Updates whose turn it is on the top of the tab and prompts the player to 
  // roll the die. If the player does have a valid move, prompts them to move 
  // a pawn. Checks to see if the clicked button is a valid move, if it is,
  // it makes the move, otherwise tells the player that the move attempted is
  // not a valid move. After a move has been made, updates the homezone and
  // goes on to the next player's turn
  //****************************************************************************
  
  /*
  private class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      if(event.getSource()==redhome){
        //System.out.println("order size: "+game.getOrder().size());
        System.out.println("you clicked on a button");
        System.out.println(game.getOrder().isEmpty());
        //turn.setText("<html><font color=#FFD700 size=6>"+current.getColor()+ "</font></html>");
        //action.setText(roll);
      }
    }
  }
  */
  
  
  private class ButtonListener implements ActionListener{
    
    boolean started=false;
    
    public void actionPerformed(ActionEvent event){
      
      updateBoard();
      
      Player current=game.order.first();
      if(event.getSource()==playbutton){
        started=true;
      }
      
      //There are no players initialized
      if(game.order.size()==0){
        if (event.getSource()==redhome || event.getSource()==yellowhome || event.getSource()==bluehome || event.getSource()==greenhome || event.getSource()==playbutton){
        JOptionPane.showMessageDialog(null,"Please add players using the 'Setup' tab first");
        }
        else{
          for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
              if(event.getSource()==buttons[i][j]){
                JOptionPane.showMessageDialog(null,"Please add players using the 'Setup' tab first");
              }
            }
          }
      }
      }
      
      
      
      else{ //players have been added to the queue
        
        rank.setText("<html><div style='text-align: center;'><font color=#FFD700 size=6>LEADERBOARD:<br>"
                       + game.winning() + "</font></html>");
        
      //while (!game.isWinner()) {
          
        if(started){
            current=game.order.first();
            turn.setText("<html><font color=#FFD700 size=6>"+current.getName()+ "</font></html>");
            action.setText(roll);
          } 
          //if they hit the die
        if(event.getSource()==buttons[5][5]){ 
            
          System.out.println("rolled");
          //roll the die and display the result at the top of the screen
          current.rollDie();
          int faceValue = current.getDieNumber();
          System.out.println("after rolling: " + faceValue);
          rolledA.setText(rolledAString);
          
          if(faceValue==1){
            dicon=die1;
          }
          else if(faceValue==2){
            dicon.setIcon(die2icon);
          }
          else if(faceValue==3){
            dicon.setIcon(die3icon);
          }
          else if(faceValue==4){
            dicon.setIcon(die4icon);
          }
          else if(faceValue==5){
            dicon.setIcon(die5icon);
          }
          else if(faceValue==6){
            dicon.setIcon(die6icon);
            }
          revalidate();
          repaint();
          }

          if (current.noMoves()){
              System.out.println("No pawns in play and did not roll a 1: " + (current.inPlay() == 0 && current.getDieNumber() != 1));//TEST
              System.out.println("No pawns in play and did not roll a 6: " + (current.inPlay() == 0 && current.getDieNumber() != 6));//TEST
              System.out.println("The player has a move: " + !current.noMoves());//TEST-----------------------------------------------------
              //System.out.println("queue: " + game.order);---------------------------------------------------------------TEMP OUT OF ORDER
              System.out.println("die number: " + current.getDieNumber());
              System.out.println("continue");
              //removeAll()
              revalidate();
              game.nextPlayer(current);
              //turn.setText("<html><font color=#FFD700 size=6>"+current.getName()+ "</font></html>");
            //  current = game.order.first();
              started = true;
              
             repaint();
               
            }
          

          else { //if the player has a move
            JButton selected = new JButton();
            Object source = event.getSource();
            if (source instanceof JButton) {
              selected = (JButton) source;
            }
              
              System.out.println ("At line 836 - player has a move");//TEST------------------------------------------------------------------------
              buttons[5][5].setEnabled(false);
              
              boardEnabled(true);
              pawnEnabled(current);
              selected.setEnabled(true);
              
              System.out.println("current player: "+current);
              action.setText(click);

              if (selected==redhome || selected==yellowhome || selected==bluehome || selected==greenhome) {
                    //System.out.println("At line 841 - move method");
                if (current.getDieNumber() == 6 || current.getDieNumber() == 1) {
                  System.out.println("here");
                  
                  
                  //game.move(current, game.getBoard().getBoard()[getBoardPosition((JButton) event.getSource())]);//TEST-------------
                  game.move(current, current.oneHome());
                  
                  
                  if(current.getColor().equals("red")){
                    //current.oneHome().setPosition(current.oneHome().getStartPosition());
                    buttons[0][10].setIcon(redOnRed);   
                    System.out.println("number in home: " + current.inHome());
                    redhome.setIcon(chooseHomeImage(current));
                    
                  }
                  else if(current.getColor().equals("green")){
                    //current.oneHome().setPosition(current.oneHome().getStartPosition());
                    buttons[10][0].setIcon(greenOnGreen);   
                    System.out.println("number in home: " + current.inHome());
                    greenhome.setIcon(chooseHomeImage(current));
                  }
                  else if(current.getColor().equals("blue")){
                    //current.oneHome().setPosition(current.oneHome().getStartPosition());
                    buttons[0][0].setIcon(blueOnBlue);   
                    System.out.println("number in home: " + current.inHome());
                    bluehome.setIcon(chooseHomeImage(current));
                  }
                  else if (current.getColor().equals("yellow")){
                    //current.oneHome().setPosition(current.oneHome().getStartPosition());
                    buttons[10][10].setIcon(yellowOnYellow);   
                    System.out.println("number in home: " + current.inHome());
                    yellowhome.setIcon(chooseHomeImage(current));
                    
                  }
                  if(current.getDieNumber()==1){
                    dicon=die1;
                  }
                  else if(current.getDieNumber()==2){
                    dicon.setIcon(die2icon);
                  }
                  else if(current.getDieNumber()==3){
                    dicon.setIcon(die3icon);
                  }
                  else if(current.getDieNumber()==4){
                    dicon.setIcon(die4icon);
                  }
                  else if(current.getDieNumber()==5){
                    dicon.setIcon(die5icon);
                  }
                  else if(current.getDieNumber()==6){
                    dicon.setIcon(die6icon);
                  }
                }
              }
              //TEST-------------
              
              else {
                
                  System.out.println("selected");
                  //System.out.println("pawn array: " + current.getPawnArray());
                  
                  game.move(current, game.getBoard().getBoard()[getBoardPosition(selected)]);
                  
                
                  
                  getGUIPosition(game.getBoard().getBoard()[getBoardPosition(selected)].getPosition());
                  
                  revalidate();
                
                  if(getColorZone(getBoardPosition(selected))=="red"){
                 selected.setIcon(redsquare);
                  }
                  else if(getColorZone(getBoardPosition(selected))=="green"){
                    selected.setIcon(greensquare);
                  }
                  else if(getColorZone(getBoardPosition(selected))=="blue"){
                    selected.setIcon(bluesquare);
                  }
                  else if(getColorZone(getBoardPosition(selected))=="yellow"){
                    selected.setIcon(yellowsquare);
                  }
                  //add icon to updated position
                  System.out.println("finally yes");
                  dicon.setIcon(diespacericon);
                  rolledA.setText("");
                  action.setText(roll);
                  buttons[5][5].setEnabled(true);
                  updateBoard();
                  System.out.println("Moving on to next player since player has moved. Line 917");
                  game.nextPlayer(current);
                  if(current.getDieNumber()==1){
                    dicon=die1;
                  }
                  else if(current.getDieNumber()==2){
                    dicon.setIcon(die2icon);
                  }
                  else if(current.getDieNumber()==3){
                    dicon.setIcon(die3icon);
                  }
                  else if(current.getDieNumber()==4){
                    dicon.setIcon(die4icon);
                  }
                  else if(current.getDieNumber()==5){
                    dicon.setIcon(die5icon);
                  }
                  else if(current.getDieNumber()==6){
                    dicon.setIcon(die6icon);
                  }
                  
                
               
            
           
              }
            }
            //game.nextPlayer(current);
            //turn.setText("<html><font color=#FFD700 size=6>"+current.getName()+ "</font></html>");
            revalidate();
            //repaint();
      }
      
        //game.nextPlayer(current);
          //turn.setText("<html><font color=#FFD700 size=6>"+current.getName()+ "</font></html>");
          started = true;
          buttons[5][5].setEnabled(true);

    }
  }
              //dicon.setIcon(diespacericon);
              //rolledA.setText("");
          

  
}