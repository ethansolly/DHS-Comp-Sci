package com.brisk.usualTwo.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**X-Drive Navigation Class
 * @author Lutalica
 * @version 1.0
 */
public class NavX extends JPanel implements ChangeListener, ActionListener {
	
	private static final long serialVersionUID = -9118110400215071176L;
	
	private JLabel sliderLabel;
	private JSpinner scroll;
	private JTextArea filepath;
	private JButton confirm;
	private JButton evplayer;
	private JButton switchToWOE;
	private JButton backup;
	
	int newSlide;

	static boolean warn = true; 	// Inverted by -w when ran
	static boolean selectingWOE = false;
	
	/**
	 * @param args Needed for main method
	 */
	public static void main(String...args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-w"))
				warn = false;
		}
		
		createAndShowGUI();
	}

	/**
	 *  Navigator Constructor
	 */
	public NavX() {
		sliderLabel = new JLabel("Slide Selection");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	
        scroll = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        scroll.addChangeListener(this);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        
        confirm = new JButton("Open Slide");
        confirm.addActionListener(this);
       
        switchToWOE = new JButton("Select a Worked Out Exercise");
        switchToWOE.addActionListener(e -> selectingWOE = !selectingWOE);
        
        evplayer = new JButton("Open ExamView");
        evplayer.addActionListener((ActionEvent e) -> {
			try {
				Process p = new ProcessBuilder( "\\\\fs03\\common2\\DHS\\ComputerScience"
						+ "\\StudentExamView\\ExamViewPlayer6\\evplay.exe").start();
				Scanner evsc = new Scanner(p.getInputStream());
				p.waitFor();
				while (evsc.hasNextLine())
					System.out.println(evsc.nextLine());
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		});
        
        filepath = new JTextArea("Scroll to select the slide");
        filepath.setEditable(false);
        filepath.setLineWrap(true);
        filepath.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        
        backup = new JButton("Backup Workspace");
        backup.addActionListener(e -> backup());
        
        add(sliderLabel);
        add(scroll);
        add(filepath);
        add(confirm);
        add(switchToWOE);
        add(evplayer);
        add(backup);
        setBorder(BorderFactory.createEmptyBorder(10,10,150,10));
	}
	
	private static void createAndShowGUI() {
        JFrame frame = new JFrame("NavX");
        NavX navigator = new NavX();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(navigator, BorderLayout.CENTER);   
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(0, 0);
    }
	
	public void stateChanged(ChangeEvent e) {
		JSpinner source = (JSpinner)(e.getSource());
			newSlide = (int)source.getValue();
            String chapter;
            if (!selectingWOE) { //Exposure java chapter x test key?
	            switch (newSlide) {
	            	case 0 :  chapter = "APCS-00-Basic CS Review"; 				break;
		            case 1 :  chapter = "APCS-01-Introduction to CS"; 			break;
		            case 2 :  chapter = "APCS-02-Introduction To Java"; 		break;
		            case 3 :  chapter = "APCS-03-Java Simple Data Types"; 		break;
		            case 4 :  chapter = "APCS-04-Using Methods and Parameters"; break;
		            case 5 :  chapter = "APCS-05-Control Structures"; 			break;
		            case 6 :  chapter = "APCS-06-Using Object Methods"; 		break;
		            case 7 :  chapter = "APCS-07-Creating Class Methods"; 		break;
		            case 8 :  chapter = "APCS-08-Creating Object Methods"; 		break;
		            case 9 :  chapter = "APCS-09-Inheritance and Composition"; 	break;
		            case 10 : chapter = "APCS-10-Boolean Logic"; 				break;
		            case 11 : chapter = "APCS-11-Control Structures 2"; 		break;
		            case 12 : chapter = "APCS-12-Static 1D & 2D Arrays"; 		break;
		            case 13 : chapter = "APCS-13-The ArrayList Class"; 			break;
		            case 14 : chapter = "APCS-14-Serious OOP"; 					break;
		            case 15 : chapter = "APCS-15-Program Design"; 				break;
		            case 16 : chapter = "APCS-16-String Methods"; 				break;
		            case 17 : chapter = "APCS-17-Sequential Files"; 			break;
		            case 18 : chapter = "APCS-18-Algorithms"; 					break;
		            case 19 : chapter = "APCS-19-Recursion"; 					break;
		            case 20 : chapter = "APCS-20-Redefining Methods";			break;
		            case 21 : chapter = "APCS-21-Polymorphism"; 				break;
		            case 22 : chapter = "APCS-22-AP Examination Review"; 		break;
		            case 23 : chapter = "APCS-23-Advanced Graphics"; 			break;
		            default : chapter = ""; 									break;
	        	}
            	filepath.setText("X:\\Business\\ComputerScience\\Ms Coffman\\" //There were more subfolders
            		+ "Books to take home\\__LearnAPCS(A)\\APCS-LearningUnits\\"
            		+ chapter + "\\Slides" + ((newSlide<10)? "0" + newSlide : newSlide) + 
            		"\\Slides" + ((newSlide<10)? "0" + newSlide : newSlide) + ".ppt");
            }
            else {
            	filepath.setText("X:\\Business\\ComputerScience\\Ms Coffman\\" //There were more subfolders
                		+ "My Added Stuff\\CS2\\WOE\\WOExercisesSlides" + ((newSlide<10)? "0" + newSlide : newSlide)
            			+ "Key.ppt");
            	if (!(new File(filepath.getText()).exists())) {
            		filepath.setText("--Worked Out Exercise #" + newSlide + " does not exist--");
            	}
            }
     	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String slideFile = filepath.getText();
		try {
			ProcessBuilder pb = new ProcessBuilder( 
						"C:\\Program Files (x86)\\Microsoft Office\\Office14\\POWERPNT.EXE",
						slideFile);
			@SuppressWarnings("unused")
			Process p = pb.start();
			if (warn)
				EnumerateWindows.run(newSlide);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Pretty bloody obvious :(
	 */
	public void backup() {
		String from = JOptionPane.showInputDialog("Type the input directory for the backup");
		String to = JOptionPane.showInputDialog("Type the output file(with .zip extension) for the backup");
		try (
				FileOutputStream fos = new FileOutputStream(new File(to));
	            ZipOutputStream zos = new ZipOutputStream(fos)
		) {
			Files.walkFileTree(new File(from).toPath(), new SimpleFileVisitor<Path>() {
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			      zos.putNextEntry(new ZipEntry(new File(from).toPath().relativize(file).toString()));
			      Files.copy(file, zos);
			      zos.closeEntry();
			      return FileVisitResult.CONTINUE;
			    }
		
			    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			      zos.putNextEntry(new ZipEntry(new File(from).toPath().relativize(dir).toString() + "/"));
			      zos.closeEntry();
			      return FileVisitResult.CONTINUE;
			    }
			});
	    } catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}