package SlotMachine;
/***************************
 * SlotMachineGUIDriver.java
 * Author: Jacob Golden
 ***************************/

import java.awt.*;
import javax.swing.*;

public class SlotMachineGUIDriver {

	public static void main (String [] args) {
		
		EventQueue.invokeLater(() -> {
	         // create three new objects
	         new SlotMachineGUI();
	         new SlotMachineGUI();
	      });
		
	}
	
	
}
