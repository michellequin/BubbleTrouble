/**
 * FILE NAME: AboutPanel.java
 * WHO: Isabel D'Alessandro 
 * WHAT: Defines the About panel, one of the tabs on the tabbed 
 * pane of the GradSchoolsGUI
 * It communicates with the GradSchools.java class where 
 * the functionality resides.
 * CS230 Assignment #8
 * WHEN: 19 April, 2016
 * */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFrame.*;
import java.lang.*;

public class InfoPanel extends JPanel{
  
  public InfoPanel(){
    
    //setLayout (new GridLayout (2, 1));
    setBackground(new Color(100,149,237));
    
    
    //JLabel l1= new JLabel("<html>  <CENTER>  <font color=#191970, size=7><b> GradSchools Picker  </b></font>  </CENTER>  <br>  <CENTER> <font color=#FCFCFC size=5> Choose the Grad School that fits you best!</font></CENTER> <br><font size=5>Select the <b>'Add Schools'</b> tab to add Schools. Then select the <b>'Evaluate'</b> tab to evaluate them </font></a>  </html>");
    JPanel images = new JPanel();
    JPanel text = new JPanel();
    ImageIcon logoicon = createImageIcon("logo4.png","");
    JLabel logo = new JLabel(logoicon);
    JLabel l1= new JLabel("<html><CENTER><font color=#FFB90F size=7><b><u>Instructions</u></b><br></font><font size=4 color=#FFFFFF>The goal of the game is to be the first player to move all four of their fish"
                          
    
                          +"from <b>’Home’</b> to <b>‘Finish’</b><br>A label at the top of the"+
                          "game board will indicate which player’s turn it is, and what action"+
                          "they need to take.<br> To roll, click the die at the center of the game"+
                          "board. You are only allowed to move your fish out of ‘Home’<br> if you "+
                          "roll a 6 or a 1. Once your fish are on the board, roll the die and "+
                          "click the fish that you want to move.<br> You will be moved the"+
                          "corresponding number of spots. If you land on a space that has your"+
                          "opponent’s fish<br>, your opponent’s fish is sent back to their ‘Home’."+
                          "You may not land on your own fish. After swimming<br> around the board "+
                          "once, your fish can make their way into their finish zone. The first"+
                          "player to have all 4 of<br> their fish in the finish zone is the winner!"+
                          "</font></CENTER></html>" );
                          
    //add(text);//text Panel
    //text.add(l1);//adds description label to text Panel
    text.setBackground(new Color(100,149,237));
    add(images);
    images.add(logo);
    add(text);
    text.add(l1);
    
    //JScrollPane scrollPane = new JScrollPane();
    //add(scrollPane, BorderLayout.CENTER);
    
    
    
    

        //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setBounds(0, 0, 900, 500);
        //scrollPane.setBounds(0,0,900,200);
        //JPanel contentPane = new JPanel(null);
        //contentPane.setPreferredSize(new Dimension(900, 200));
        //contentPane.add(scrollPane);
        //add(contentPane);
        //frame.setContentPane(contentPane);
    
    JPanel border= new JPanel();
    border.setBounds(0,100,900,700);
    //border.setPreferredSize(new Dimension(900, 700));
    ImageIcon waveicon = createImageIcon("waveborder.png","");
    JLabel wave = new JLabel(waveicon);
    add(border);
    border.add(wave);
    border.setBackground(new Color(100,149,237));
    images.setBackground(new Color(100,149,237));
  }
  
  
   /** 
   * Creates and returns an ImageIcon out of an image file.
   * @param path The path to the imagefile relevant to the current file.
   * @param description A short description to the image.
   * @return ImageIcon An ImageIcon, or null if the path was invalid. 
   */
  protected ImageIcon createImageIcon(String path,
                                           String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}
                            
}