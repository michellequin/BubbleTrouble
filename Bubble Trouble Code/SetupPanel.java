//*************************************************
//  Isabel D'Alessandro
//  CS230 Final Project
//  PlayPanel.java
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

public class SetupPanel extends JPanel{
  public PlayGame game;
  private JTextField redname, bluename, greenname, yellowname;
  private JButton addButton;
  
  public SetupPanel(PlayGame g){
    this.game=g;
    //Sets up the background and puts in the instructions on how to add players
    setBackground(new Color(100,149,237));
    JPanel instructions= new JPanel();
    JLabel infolabel = new JLabel("<html><CENTER><font color=#FFFFFF size=5>Have each player type their name in the box next to the fish they want to play as. Then click <b>'Add Players'</b>!</font><br><font color=#FFB90F size=5> Click the <b>'Play'</b> tab to start the game!</font></CENTER></html>");
    add(instructions);
    instructions.setBackground(new Color(100,149,237));
    instructions.add(infolabel);
    
    //Creates the panel that holds all the players information (to be added) and has the fish icon
    //that goes with each player
    JPanel players = new JPanel();
    add(players);
    players.setBackground(new Color(100,149,237));
    players.setLayout (new GridLayout (0, 3));

    //for the red player setup
    ImageIcon redfishicon = createImageIcon("redfish.png","");
    JLabel redfish = new JLabel(redfishicon);
    players.add(redfish);
    //JPanel redbox = new JPanel();
    //players.add(redbox);
    //redbox.setPreferredSize(new Dimension(100,100));
    redname= new JTextField(10);
    redname.setPreferredSize(new Dimension(50,50)); 
    players.add(redname);
    Font bigFont = redname.getFont().deriveFont(Font.PLAIN, 20f);
    redname.setFont(bigFont);
    ImageIcon bluespacericon = createImageIcon("bluespacer.png","");
    JLabel bluespacer = new JLabel(bluespacericon);
    players.add(bluespacer);
    
    //for the blue player setup
    ImageIcon bluefishicon = createImageIcon("bluefish.png","");
    JLabel bluefish = new JLabel(bluefishicon);
    players.add(bluefish);
    bluename= new JTextField(10);
    bluename.setPreferredSize(new Dimension(50,50)); 
    players.add(bluename);
    Font bigFont2 = bluename.getFont().deriveFont(Font.PLAIN, 20f);
    bluename.setFont(bigFont2);
    JLabel bluespacer2 = new JLabel(bluespacericon);
    players.add(bluespacer2);
    
    //for the green player setup
    ImageIcon greenfishicon = createImageIcon("greenfish.png","");
    JLabel greenfish = new JLabel(greenfishicon);
    players.add(greenfish);
    greenname= new JTextField(20);
    greenname.setPreferredSize(new Dimension(50,50)); 
    players.add(greenname);
    Font bigFont3 = greenname.getFont().deriveFont(Font.PLAIN, 20f);
    greenname.setFont(bigFont3);
    JLabel bluespacer3 = new JLabel(bluespacericon);
    bluespacer3.setPreferredSize(new Dimension(50,50));
    players.add(bluespacer3);
    
    //for the yellow player setup
    ImageIcon yellowfishicon = createImageIcon("yellowfish.png","");
    JLabel yellowfish = new JLabel(yellowfishicon);
    players.add(yellowfish);
    yellowname= new JTextField(20);
    yellowname.setPreferredSize(new Dimension(50,50)); 
    players.add(yellowname);
    Font bigFont4 = yellowname.getFont().deriveFont(Font.PLAIN, 20f);
    yellowname.setFont(bigFont4);
    
    //Creates the button that allows the game to be started once all the players names have been added
    addButton = new JButton (new ImageIcon ("addbutton.png"));
    players.add(addButton);
    addButton.setPreferredSize(new Dimension(100, 10));
    ButtonListener listener= new ButtonListener();
    addButton.addActionListener(listener);
  }
  
  
  //****************************************************************************
  //  HELPER FUNCTION:
  //  createImageIcon allows for an icon to be created and added to the panel
  //  also allows for a description in addition to the picture
  //****************************************************************************
  protected ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
  //****************************************************************************
  // When add Players button is pressed, creates the PlayGame class with all 
  // of the players and pawns.
  //****************************************************************************
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      // if the add button is pressed, create a PlayGame class with all the players and pawns 
      if(event.getSource()==addButton){
        System.out.println(redname.getText());
        game.orderPlay(bluename.getText(),redname.getText(), yellowname.getText(), greenname.getText());
      }
    }
  }
}