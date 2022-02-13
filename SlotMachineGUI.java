package SlotMachine;
/***************************
 * SlotMachineGUI.java
 * Author: Jacob Golden
 ***************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class SlotMachineGUI extends JFrame implements ActionListener {
	
	////constants ////
	public static final int MAX_BET = 4;
	public static final int MAX_ROLL = 5;
	public static final int ALL_ZERO_MULTIPLIER = 10;
	public static final int TRIPLE_MULTIPLIER = 5;
	public static final int PAIR_MULTIPLIER = 3;
	public static final int INIT1 = 0;
	public static final int INIT2 = 0;
	public static final int INIT3 = 0;
	
	/// other static variables ///
	private static int totalWinnings = 0;
	
	////instance variables ////
	private int bet = 0;
	private int currentBet;
	private int currentWinnings;
	private int r1 = 0;
	private int r2 = 0;
	private int r3 = 0;
	
	//button and label variables
	private JLabel r1Label;
	private JLabel r2Label;
    private JLabel r3Label;
    private JLabel betLabel;
    private JLabel youWonLabel;
    private JLabel totalLabel;
    private JButton betButton;
    private JButton pullHandleButton;
	private JButton resetButton;
	
	////static methods ////
	public static int getTotalWinnings () { return totalWinnings; }
	
	////service methods ////
	
	//constructor
	public SlotMachineGUI () { 
	
	JPanel textPanel = new JPanel();
    textPanel.setBackground(Color.blue);
    
    // //labels for first text panel
    //create label for first reel
    r1Label = new JLabel();
    r1Label.setOpaque(true);
    r1Label.setBackground(Color.yellow);
    textPanel.add(r1Label);

    //create label for second reel
    r2Label = new JLabel();
    r2Label.setOpaque(true);
    r2Label.setBackground(Color.yellow);
    textPanel.add(r2Label);
    
    //create label for third reel
    r3Label = new JLabel();
    r3Label.setOpaque(true);
    r3Label.setBackground(Color.yellow);
    textPanel.add(r3Label);
    
    JPanel textPanel2 = new JPanel();
    textPanel2.setBackground(Color.white);
    
    // //labels for second text panel
    
    betLabel = new JLabel("Bet:");
    betLabel.setOpaque(true);
    betLabel.setBackground(Color.white);
    textPanel2.add(betLabel);
    
    youWonLabel = new JLabel("You Won:");
    youWonLabel.setOpaque(true);
    youWonLabel.setBackground(Color.white);
    textPanel2.add(youWonLabel);
    
    totalLabel = new JLabel("Total Winnings:");
    totalLabel.setOpaque(true);
    totalLabel.setBackground(Color.white);
    textPanel2.add(totalLabel);
    
    
    

    
    // create the buttons
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.red);
    
    // //Bet One Coin Button
    betButton = new JButton("Bet One Coin");
    betButton.addActionListener(this);
    betButton.setBackground(Color.green);
    betButton.setOpaque(true);
    buttonPanel.add(betButton);
    
    
    // //Pull Handle Button
    pullHandleButton = new JButton("Pull Handle");
    pullHandleButton.addActionListener(this);
    pullHandleButton.setBackground(Color.green);
    pullHandleButton.setOpaque(true);
    buttonPanel.add(pullHandleButton);
    
    // //Reset Button
    resetButton = new JButton("RESET");
    resetButton.addActionListener(this);
    resetButton.setBackground(Color.green);
    resetButton.setOpaque(true);
    buttonPanel.add(resetButton);

    resetNums();

    // set-up my container (recall we is-a JFrame) and
    // add the panels
    setBackground(Color.black);
    setLayout(new BorderLayout());
    add(textPanel, BorderLayout.NORTH);
    add(textPanel2, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // set some window behavior
    pack(); // makes the frame fit the contents
    setDefaultCloseOperation(EXIT_ON_CLOSE); // closing window exits
    setVisible(true); // make sure we are displayed
	}
	 
	
	////support methods ////
	
	private void betOneCoin()
	{
	  // if bet is == to max do not increase bet, else increase bet
	if (bet == MAX_BET) {
		betButton.setEnabled(false);
	}
	else {
		bet++;
		currentBet = bet;
		displayNums();
		betButton.setEnabled(true);
		resetButton.setEnabled(true);
		pullHandleButton.setEnabled(true);
	
	}
	} // betOneCoin method
	
	private void pullHandle()
	{
	  // check to make sure there is a bet
	if (bet == 0)
		pullHandleButton.setEnabled(false);
	else
	{
		 // spin the reels and show them
	spinReels();
	System.out.println(toString());
	
	 // calculate winnings
	int won = 0;
	// three of a kind
	if (r1 == r2 && r2 == r3)
	{
	   if (r1 == 0) // all zeros is biggest winner
	      won = ALL_ZERO_MULTIPLIER * bet;
	   else	// all the same but not zero is also a winner
	      won = TRIPLE_MULTIPLIER * bet;
	} // if all same
	else if (r1 == r2 || r1 == r3 || r2 == r3) //two of a kind
	   won = PAIR_MULTIPLIER * bet;
	
	
	//totalWinnings + winnings from current pull
	currentWinnings = won;
	totalWinnings += won;
	// always clear bet after handle is pulled
	bet = 0;
	displayNums();
	//sets pull handle button unusable until betButton has been pressed
	pullHandleButton.setEnabled(false);
	
	} // else pull handle
	} // pullHandle method
	
	//support methods
	
	private void spinReels ()
	{
		  // use one random object three time to generate random number
		  // MAX_ROLL+1 is used because nextInt takes an exclusive upperbound
		Random rnd = new Random();
		r1 = rnd.nextInt(MAX_ROLL+1);
		r2 = rnd.nextInt(MAX_ROLL+1);
		r3 = rnd.nextInt(MAX_ROLL+1);
		displayNums();
	}
	
	/// method displayNums: display the values ///
   private void displayNums() {

	   r1Label.setText("" + r1);
	   r2Label.setText("" + r2);
	   r3Label.setText("" + r3);
	   betLabel.setText("Bet: " + currentBet);
	   youWonLabel.setText("You Won: " + currentWinnings);
	   totalLabel.setText("Total Winnings: " + totalWinnings);
	   
   } // method displayNums
	   
   /// method resetNums: reset the values ///
   private void resetNums() {

	   r1 = INIT1;
	   r2 = INIT2;
	   r3 = INIT3;

	   betButton.setEnabled(true);
	   pullHandleButton.setEnabled(false);
	   resetButton.setEnabled(false);

	   displayNums();

   } // method resetNums
	
	// implement the method defined in the 
	   // ActionListener interface
	   @Override
	   public void actionPerformed(ActionEvent e) {

	      Object src = e.getSource();
	      if (src == resetButton)
	         resetNums();
	      else if (src == betButton)
	         betOneCoin();
	      else if (src == pullHandleButton) {
	    	  pullHandle();
	      }

	   } // method actionPerformed
	}

