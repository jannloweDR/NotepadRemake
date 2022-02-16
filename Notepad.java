
// Name: De Rivera, Jann Lowe


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class Notepad implements ActionListener {
	JFrame frame = new JFrame("Untitled - Notepad");
	JFileChooser fileChooser = new JFileChooser(".");
	JTextArea textArea = new JTextArea();
	JPanel statusPanel = new JPanel(new BorderLayout());
	boolean statusCheck;
	JMenuItem gotoMI = new JMenuItem("Go To...");
	JCheckBoxMenuItem wordWrapMI = new JCheckBoxMenuItem("Word Wrap");
	
	public Notepad() {
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon frameIcon = new ImageIcon("Notepad.png");
		frame.setIconImage(frameIcon.getImage());
		
		//Making the different menus
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E');
		JMenu formatMenu = new JMenu("Format");
		formatMenu.setMnemonic('o');
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic('V');
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		
		//Making menu items for File menu
		JMenuItem newMI = new JMenuItem("New");
		newMI.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));
		newMI.setMnemonic('N');
		JMenuItem openMI = new JMenuItem("Open...");
		openMI.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
		openMI.setMnemonic('O');
		JMenuItem saveMI = new JMenuItem("Save");
		saveMI.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
		saveMI.setMnemonic('S');
		JMenuItem saveAsMI = new JMenuItem("Save As...");
		saveAsMI.setMnemonic('A');
		JMenuItem pageSetupMI = new JMenuItem("Page Setup...");
		pageSetupMI.setMnemonic('u');
		JMenuItem printMI = new JMenuItem("Print...");
		printMI.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_MASK));
		printMI.setMnemonic('P');
		JMenuItem exitMI = new JMenuItem("Exit");
		exitMI.setMnemonic('x');
		
		newMI.addActionListener(this);
		openMI.addActionListener(this);
		saveMI.addActionListener(this);
		saveAsMI.addActionListener(this);
		pageSetupMI.addActionListener(this);
		printMI.addActionListener(this);
		exitMI.addActionListener(this);
		
		//Making menu items for edit menu
		JMenuItem undoMI = new JMenuItem("Undo");
		undoMI.setAccelerator(KeyStroke.getKeyStroke('Z', InputEvent.CTRL_MASK));
		undoMI.setMnemonic('U');
		JMenuItem cutMI = new JMenuItem("Cut");
		cutMI.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
		cutMI.setMnemonic('T');
		JMenuItem copyMI = new JMenuItem("Copy");
		copyMI.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		copyMI.setMnemonic('C');
		JMenuItem pasteMI = new JMenuItem("Paste");
		pasteMI.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
		pasteMI.setMnemonic('P');
		JMenuItem deleteMI = new JMenuItem("Delete");
		deleteMI.setMnemonic('l');
		JMenuItem findMI = new JMenuItem("Find...");
		findMI.setMnemonic('F');
		findMI.setAccelerator(KeyStroke.getKeyStroke('F', InputEvent.CTRL_MASK));
		JMenuItem findNextMI = new JMenuItem("Find Next");
		KeyStroke f3KeyStroke = KeyStroke.getKeyStroke("F3");
		findNextMI.setAccelerator(f3KeyStroke);
		findNextMI.setMnemonic('N');
		JMenuItem replaceMI = new JMenuItem("Replace...");
		replaceMI.setMnemonic('R');
		replaceMI.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_MASK));
		gotoMI.setMnemonic('G');
		gotoMI.setAccelerator(KeyStroke.getKeyStroke('G', InputEvent.CTRL_MASK));
		JMenuItem selectAllMI = new JMenuItem("Select All");
		selectAllMI.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_MASK));
		selectAllMI.setMnemonic('A');
		JMenuItem timeDateMI = new JMenuItem("Time/Date");
		KeyStroke f5KeyStroke = KeyStroke.getKeyStroke("F5");
		timeDateMI.setAccelerator(f5KeyStroke);
		timeDateMI.setMnemonic('D');
		
		undoMI.addActionListener(this);
		undoMI.setEnabled(false);
		cutMI.addActionListener(this);
		cutMI.setEnabled(false);
		copyMI.addActionListener(this);
		copyMI.setEnabled(false);
		pasteMI.addActionListener(this);
		deleteMI.addActionListener(this);
		deleteMI.setEnabled(false);
		findMI.addActionListener(this);
		findMI.setEnabled(false);
		findNextMI.addActionListener(this);
		findNextMI.setEnabled(false);
		replaceMI.addActionListener(this);
		gotoMI.addActionListener(this);
		selectAllMI.addActionListener(this);
		timeDateMI.addActionListener(this);
		
		
		//Making menu items for Format menu
		wordWrapMI.setMnemonic('W');
		JMenuItem fontMI = new JMenuItem("Font...");
		fontMI.setMnemonic('F');
		JMenu colorMenu = new JMenu("Color");
		JMenuItem foregroundMI = new JMenuItem("Foreground");
		JMenuItem backgroundMI = new JMenuItem("Background");
		colorMenu.setMnemonic('C');
		colorMenu.add(foregroundMI);
		colorMenu.add(backgroundMI);
		
		foregroundMI.addActionListener(this);
		backgroundMI.addActionListener(this);
		wordWrapMI.addActionListener(this);
		fontMI.addActionListener(this);
		
		//Making menu items for view menu
		JCheckBoxMenuItem statusBarMI = new JCheckBoxMenuItem("Status Bar");
		statusBarMI.setMnemonic('S');
		statusBarMI.addActionListener(this);
		
		//Making menu items for help menu
		JMenuItem viewHelpMI = new JMenuItem("View Help");
		viewHelpMI.setMnemonic('H');
		JMenuItem aboutNotepadMI = new JMenuItem("About Notepad");
		aboutNotepadMI.setMnemonic('A');
		
		
		viewHelpMI.addActionListener(this);
		aboutNotepadMI.addActionListener(this);
		
		//Adding menu items to their respective menus
		fileMenu.add(newMI);
		fileMenu.add(openMI);
		fileMenu.add(saveMI);
		fileMenu.add(saveAsMI);
		fileMenu.addSeparator();
		fileMenu.add(pageSetupMI);
		fileMenu.add(printMI);
		fileMenu.addSeparator();
		fileMenu.add(exitMI);
		
		editMenu.add(undoMI);
		editMenu.addSeparator();
		editMenu.add(cutMI);
		editMenu.add(copyMI);
		editMenu.add(pasteMI);
		editMenu.add(deleteMI);
		editMenu.addSeparator();
		editMenu.add(findMI);
		editMenu.add(findNextMI);
		editMenu.add(replaceMI);
		editMenu.add(gotoMI);
		editMenu.addSeparator();
		editMenu.add(selectAllMI);
		editMenu.add(timeDateMI);
		
		
		formatMenu.add(wordWrapMI);
		formatMenu.add(fontMI);
		formatMenu.add(colorMenu);
		
		viewMenu.add(statusBarMI);
		
		helpMenu.add(viewHelpMI);
		helpMenu.addSeparator();
		helpMenu.add(aboutNotepadMI);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(formatMenu);
		menuBar.add(viewMenu);
		menuBar.add(helpMenu);
		
		JPopupMenu popUpMenu = new JPopupMenu("EditPUM");
		JMenuItem undoPUM = new JMenuItem("Undo");
		JMenuItem cutPUM = new JMenuItem("Cut");
		JMenuItem copyPUM = new JMenuItem("Copy");
		JMenuItem pastePUM = new JMenuItem("Paste");
		JMenuItem deletePUM = new JMenuItem("Delete");
		JMenuItem selectAllPUM = new JMenuItem("Select All");
		
		undoPUM.addActionListener(this);
		cutPUM.addActionListener(this);
		copyPUM.addActionListener(this);
		pastePUM.addActionListener(this);
		deletePUM.addActionListener(this);
		selectAllPUM.addActionListener(this);
		
		popUpMenu.add(undoPUM);
		popUpMenu.addSeparator();
		popUpMenu.add(cutPUM);
		popUpMenu.add(copyPUM);
		popUpMenu.add(pastePUM);
		popUpMenu.add(deletePUM);
		popUpMenu.addSeparator();
		popUpMenu.add(selectAllPUM);
		
		textArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (SwingUtilities.isRightMouseButton(me)){
						popUpMenu.show(frame, me.getX(), me.getY());
				}
			}
		});
		
		  CaretListener caretListener = new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {
		    	  if(textArea.getCaretPosition() != 0) {
		    				undoMI.setEnabled(true);
		    				cutMI.setEnabled(true);
		    				copyMI.setEnabled(true);
		    				deleteMI.setEnabled(true);
		    				findMI.setEnabled(true);
		    				findNextMI.setEnabled(true);
		    	  }else if(textArea.getCaretPosition() == 0 && textArea.getDocument().getLength() == 0){
		    		  undoMI.setEnabled(false);
	    				cutMI.setEnabled(false);
	    				copyMI.setEnabled(false);
	    				deleteMI.setEnabled(false);
	    				findMI.setEnabled(false);
	    				findNextMI.setEnabled(false);
		    	  }
		      }
		    };
		    
		textArea.addCaretListener(caretListener);
		
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		NotepadFilter filterText = new NotepadFilter(".txt", "Text Files");
		NotepadFilter filterJava = new NotepadFilter(".java", "Java Files");
		fileChooser.addChoosableFileFilter(filterText);
		fileChooser.addChoosableFileFilter(filterJava);
		
		frame.setJMenuBar(menuBar);
		frame.add(new JScrollPane(textArea));
		
		frame.setVisible(true);
	}
	

	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getActionCommand().equals("Print...")) {
			JOptionPane.showMessageDialog(frame, "Printing... \n (NOT IMPLEMENTED)");
		}
		if(ae.getActionCommand().equals("Page Setup...")) {
			JOptionPane.showMessageDialog(frame, "Setting up page... \n (NOT IMPLEMENTED)");
		}
		if(ae.getActionCommand().equals("View Help")){
			JOptionPane.showMessageDialog(frame, "Viewing Help... \n (NOT IMPLEMENTED)");
		}
		if(ae.getActionCommand().equals("Undo")) {
			JOptionPane.showMessageDialog(frame, "Undoing... \n (NOT IMPLEMENTED)");
		}
		if(ae.getActionCommand().equals("Replace...")) {
			JOptionPane.showMessageDialog(frame, "Replacing... \n (NOT IMPLEMENTED)");
		}
		if(ae.getActionCommand().equals("Open...")) {
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					textArea.read(new FileReader(fileChooser.getSelectedFile()), fileChooser.getSelectedFile().getName());
					String title = fileChooser.getSelectedFile().getName();
					frame.setTitle(title.substring(0, title.lastIndexOf('.')) + " - Notepad");
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(ae.getActionCommand().equals("Exit")) {
			if(textArea.getDocument().getLength() == 0) {
				System.exit(0);
			}else {
				int option = JOptionPane.showConfirmDialog(frame, "Do you want to save changes to " + frame.getTitle() + "?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					int result = fileChooser.showSaveDialog(null);
					String content = textArea.getText();
					File newFile = fileChooser.getSelectedFile();
					if(result == JFileChooser.APPROVE_OPTION) {
						if(newFile.exists()) {
							int response = JOptionPane.showConfirmDialog(frame, "Do you want to replace the already existing file", "Confirm", JOptionPane.YES_NO_OPTION);
							if(response == JOptionPane.YES_OPTION) {
								try {
									FileWriter fw = new FileWriter(newFile.getPath());
									fw.write(content);
									fw.flush();
									fw.close();
									String title = fileChooser.getSelectedFile().getName();
									frame.setTitle(title.substring(0, title.lastIndexOf('.')) + " - Notepad");
								} catch (IOException e) {
									e.printStackTrace();
								}
								
							}
						}else {
							try {
								FileWriter fw = new FileWriter(newFile.getPath());
								fw.write(content);
								fw.flush();
								fw.close();
								String title = fileChooser.getSelectedFile().getName();
								frame.setTitle(title.substring(0, title.lastIndexOf('.')) + " - Notepad");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
					}
				}else if(option == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}
		}
		if(ae.getActionCommand().equals("About Notepad")) {
			JDialog aboutDialog = new JDialog(frame, "About Notepad", true);
			aboutDialog.setSize(450, 200);
			aboutDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			aboutDialog.setLocationRelativeTo(frame);
			aboutDialog.setResizable(false);
			aboutDialog.setLayout(new FlowLayout());
			JLabel aboutLabel = new JLabel("(c) Jann Lowe De Rivera");
			JButton okButton = new JButton("Ok");
			okButton.setFocusPainted(true);
			okButton.addActionListener(event -> {
				aboutDialog.dispose();
			});
			ImageIcon icon = new ImageIcon(new ImageIcon("Notepad.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			JLabel iconLabel = new JLabel(icon);
			JPanel outerPanel = new JPanel(new GridLayout(2,1));
			JPanel topPanel = new JPanel(new FlowLayout());
			JPanel bottomPanel = new JPanel(new FlowLayout());
			topPanel.add(iconLabel);
			topPanel.add(aboutLabel);
			bottomPanel.add(okButton);
			outerPanel.add(topPanel);
			outerPanel.add(bottomPanel);
			aboutDialog.add(outerPanel);
			aboutDialog.setVisible(true);
			
			
		}
		if(ae.getActionCommand().equals("Go To...")) {
			JDialog gotoDialog = new JDialog(frame, "Go To Line", true );
			JButton gotoButton = new JButton("Go To");
			JButton cancelButton = new JButton("Cancel");
			JLabel gotoLabel = new JLabel("Line Number: ");
			JTextField gotoTextField = new JTextField("1", 10);
			JPanel buttonPanel = new JPanel(new GridLayout(1,2));
			gotoTextField.selectAll();
			gotoDialog.getRootPane().setDefaultButton(gotoButton);
			
			gotoDialog.setLocationRelativeTo(frame);
			gotoDialog.setSize(280, 135);
			gotoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			gotoDialog.setLayout(new FlowLayout());
			gotoDialog.setResizable(false);
			gotoButton.setFocusPainted(true);
			
			buttonPanel.add(gotoButton);
			buttonPanel.add(cancelButton);
			
			
			gotoButton.setActionCommand("Cancel");
			gotoButton.addActionListener(event -> { 
				gotoButton.setActionCommand("ok");
				gotoDialog.dispose();
			});
			
			cancelButton.addActionListener(event -> {
				gotoDialog.dispose();
			});
			
			gotoDialog.add(gotoLabel);
			gotoDialog.add(gotoTextField);
			gotoDialog.add(buttonPanel);
			
			gotoDialog.setVisible(true);
			
			if(gotoButton.getActionCommand().equals("ok")) {
				if(textArea.getLineCount() < Integer.parseInt(gotoTextField.getText())){
					JOptionPane.showMessageDialog(gotoDialog, "The line number is beyond the total number of lines", "Notepad - Go To", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					textArea.setCaretPosition(textArea.getDocument().getDefaultRootElement().getElement(Integer.parseInt(gotoTextField.getText())-1).getStartOffset());
				}
			}
		}
		if(ae.getActionCommand().equals("Time/Date")) {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
			Date currentDate = new Date();
			String date = dateFormat.format(currentDate);
			textArea.insert(date, textArea.getCaretPosition());
		}
		if(ae.getActionCommand().equals("Status Bar")) {
			statusCheck = frame.getJMenuBar().getMenu(3).getItem(0).isSelected();
			JLabel lineStatus = new JLabel("Ln 1, Col 1 \n (NOT IMPLEMENTED)");
			statusPanel.add(lineStatus, BorderLayout.EAST);
			if(statusCheck == true) {
				frame.add(statusPanel, BorderLayout.SOUTH);
				frame.setSize(frame.getWidth(), frame.getHeight()+1);
			}else {
				frame.remove(statusPanel);
				frame.setSize(frame.getWidth(), frame.getHeight()-1);
				
			}
		}
		
		if(ae.getActionCommand().equals("Font...")) {
			Font selectedFont = JFontChooser.showDialog(frame, textArea.getFont());
			if(selectedFont != null) {
				textArea.setFont(selectedFont);
			}
		}
		
		if(ae.getActionCommand().equals("Find...")) {
			JDialog findDialog = new JDialog(frame, "Find");
			findDialog.setSize(475,175);
			findDialog.setLayout(new FlowLayout());
			findDialog.setResizable(false);
			findDialog.setLocationRelativeTo(frame);
			findDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			JLabel findWhat = new JLabel("Find What");
			JTextField findWhatTF = new JTextField(25);
			JButton findNext = new JButton("Find Next");
			findNext.setEnabled(false);
			
			findDialog.getRootPane().setDefaultButton(findNext);
			findWhatTF.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(findWhatTF.getText().length() != 0) {
						findNext.setEnabled(true);
					}
				}
			});
			
			
			
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(event -> {
				findDialog.dispose();
			});
			
			JCheckBox matchCase = new JCheckBox("Match Case");
			JRadioButton upRB = new JRadioButton("Up");
			JRadioButton downRB = new JRadioButton("Down");
		
			ButtonGroup bg = new ButtonGroup();
			bg.add(upRB);
			bg.add(downRB);
			
			upRB.addActionListener(this);
			downRB.addActionListener(this);
			
			JPanel rbPanel = new JPanel();
			rbPanel.add(upRB);
			rbPanel.add(downRB);
			
			rbPanel.setBorder(BorderFactory.createDashedBorder(Color.black));

			
			JLabel direction = new JLabel("Direction:");
			
			findNext.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String findWord = findWhatTF.getText();
					int findWordLength = findWord.length();
					String textAreaString = textArea.getText();
					boolean endOfSearch = false;
					int start = 0;
					if(findWord != null) { 
						while(endOfSearch == false) { 
							int index = 0; 
							if(matchCase.isSelected() == false) { 
								findWord = findWord.toLowerCase(); 
								textAreaString = textAreaString.toLowerCase(); 
								index = textAreaString.indexOf(findWord, start);
							}else { 
								index = textAreaString.indexOf(findWord, start); 
							} 
							if(index > -1) {
								endOfSearch = true;
								textArea.select(index, index + findWordLength); 
								start = textArea.getCaretPosition() + 1;
							}else { 
								endOfSearch = true; 
								JOptionPane.showMessageDialog(findDialog, "No more words found");
							} 
						}
					}
					
				}
			});			
			
			findDialog.add(findWhat);
			findDialog.add(findWhatTF);
			findDialog.add(findNext);
			findDialog.add(matchCase);
			findDialog.add(direction);
			findDialog.add(rbPanel);
			findDialog.add(cancel);
			
			findDialog.setVisible(true);
			
			
			
		}
		if(ae.getActionCommand().equals("Word Wrap")) {
			if(wordWrapMI.getState() == true) {
				textArea.setLineWrap(true);
				gotoMI.setEnabled(false);
			}else {
				textArea.setLineWrap(false);;
				gotoMI.setEnabled(true);
			}
		}
		
		if(ae.getActionCommand().equals("Select All")) {
			textArea.selectAll();
		}
		if(ae.getActionCommand().equals("Delete")) {
			textArea.replaceSelection("");
		}
		
		if(ae.getActionCommand().equals("Cut")) {
			textArea.cut();
		}
		if(ae.getActionCommand().equals("Copy")) {
			textArea.copy();
		}
		if(ae.getActionCommand().equals("Paste")) {
			textArea.paste();
		}
		if(ae.getActionCommand().equals("New")) {
			if(textArea.getDocument().getLength() != 0) {
				JDialog confirmDialog = new JDialog(frame, "Notepad", true);
				confirmDialog.setSize(300,100);
				confirmDialog.setLocationRelativeTo(frame);
				confirmDialog.setLayout(new FlowLayout());
				confirmDialog.setResizable(false);
				confirmDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				JLabel confirmLabel = new JLabel("Do you want to save changes to " + frame.getTitle());
				JButton saveBtn = new JButton("Save");
				saveBtn.addActionListener(this);
				JButton dontSaveBtn = new JButton("Don't Save");
				dontSaveBtn.addActionListener(this);
				JButton cancelBtn = new JButton("Cancel");
				cancelBtn.addActionListener(event -> {
					confirmDialog.dispose();
				});	
				dontSaveBtn.addActionListener(event -> {
					textArea.setText(null);
					frame.setTitle("Untitled - Notepad");
					textArea.setForeground(Color.black);
					textArea.setBackground(Color.white);
					textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
					confirmDialog.dispose();
				});
				saveBtn.addActionListener(even -> {
					textArea.append("SAVING");
					confirmDialog.dispose();
				});

				confirmDialog.add(confirmLabel);
				confirmDialog.add(saveBtn);
				confirmDialog.add(dontSaveBtn);
				confirmDialog.add(cancelBtn);
				
				confirmDialog.setVisible(true);	
			}else{
				textArea.setText(null);
			}
			
			
		}
		if(ae.getActionCommand().equals("Save")) {
			if(frame.getTitle().contains("Untitled")) {
				int result = fileChooser.showSaveDialog(null);
				String content = textArea.getText();
				File newFile = fileChooser.getSelectedFile();
				if(result == JFileChooser.APPROVE_OPTION) {
					if(newFile.exists()) {
						int response = JOptionPane.showConfirmDialog(frame, "Do you want to replace the already existing file", "Confirm", JOptionPane.YES_NO_OPTION);
						if(response == JOptionPane.YES_OPTION) {
							try {
								FileWriter fw = new FileWriter(newFile.getPath());
								fw.write(content);
								fw.flush();
								fw.close();
								String title = fileChooser.getSelectedFile().getName();
								frame.setTitle(title.substring(0, title.lastIndexOf('.')) + " - Notepad");
							} catch (IOException e) {
								e.printStackTrace();
							}
						
						}
					}else {
						try {
							FileWriter fw = new FileWriter(newFile.getPath());
							fw.write(content);
							fw.flush();
							fw.close();
							String title = fileChooser.getSelectedFile().getName();
							frame.setTitle(title.substring(0, title.lastIndexOf('.')) + " - Notepad");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		if(ae.getActionCommand().equals("Save As...")) {
			int result = fileChooser.showSaveDialog(null);
			String content = textArea.getText();
			File newFile = fileChooser.getSelectedFile();
			if(result == JFileChooser.APPROVE_OPTION) {
				if(newFile.exists()) {
					int response = JOptionPane.showConfirmDialog(frame, "Do you want to replace the already existing file", "Confirm", JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.YES_OPTION) {
						try {
							FileWriter fw = new FileWriter(newFile.getPath());
							fw.write(content);
							fw.flush();
							fw.close();
							String title = fileChooser.getSelectedFile().getName();
							frame.setTitle(title.substring(0, title.lastIndexOf('.')) + " - Notepad");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}else {
					try {
						FileWriter fw = new FileWriter(newFile.getPath());
						fw.write(content);
						fw.flush();
						fw.close();
						String title = fileChooser.getSelectedFile().getName();
						frame.setTitle(title.substring(0, title.lastIndexOf('.')) + " - Notepad");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		}	
		
		if(ae.getActionCommand().equals("Foreground")) {
			Color initialColor = textArea.getForeground();
			Color color = JColorChooser.showDialog(frame, "Choose a color", initialColor);
			if(color != null) {
				textArea.setForeground(color);
			}else {
				textArea.setForeground(initialColor);
			}
		}
		if(ae.getActionCommand().equals("Background")) {
			Color initialColor = textArea.getBackground();
			Color color = JColorChooser.showDialog(frame, "Choose a color", initialColor);
			if(color != null){ 
				textArea.setBackground(color);
			}else{
				textArea.setBackground(initialColor);
			}
				
		}
	}
	
	public class NotepadFilter extends FileFilter {
		private String extension;
		private String description;
		
		public NotepadFilter(String extension, String description) {
			this.extension = extension;
			this.description = description;
		}
		
		public boolean accept(File f) {
			if (f.isDirectory()) {
	            return true;
	        }
	        return f.getName().endsWith(extension);
		}

		
		public String getDescription() {
			return description + String.format(" (*%s)", extension);
		}
		
		
	}
	
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
			 public void run() {
				 new Notepad();
			 }
		 });
	 }


	
}
