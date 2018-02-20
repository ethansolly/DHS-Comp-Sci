package com.brisk.usualTwo.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**Client program. Run this!
 * @author Lutalica
 * @version 1.0
 *
 */
public class Client extends JPanel {

	private static final long serialVersionUID = 1007910758875123036L;
	
	static JButton navX;
	static JTextArea quest;
	static JButton accept;
	
	/**
	 * @param args Needed for main method
	 */
	public static void main(String...args) {
		JFrame frame = new JFrame("Client");
		Client client = new Client(args);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(client, BorderLayout.CENTER);   
        frame.pack();
        frame.setVisible(true);
        frame.setSize(250, 500);
	}
	
	/**Constructor
	 * @param args arguments used in a command line!
	 */
	public Client(String...args) {
		//WIP
		quest = new JTextArea("--Ask a question--");
		quest.setEditable(true);
		quest.setLineWrap(true);
		quest.setWrapStyleWord(true);
		quest.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder(25,25,25,25)));
		
		accept = new JButton("Press to ask");
		accept.addActionListener(e -> quest.setText("--Sent message--"));
		accept.setAlignmentX(Component.LEFT_ALIGNMENT);
		accept.setAlignmentY(BOTTOM_ALIGNMENT);

		navX = new JButton("Press to open NavX");
		navX.addActionListener(e -> NavX.main("-w"));
		navX.setAlignmentX(Component.RIGHT_ALIGNMENT);
		navX.setAlignmentY(BOTTOM_ALIGNMENT);
		
		add(quest);
		add(accept);
		add(navX);
	}
}
