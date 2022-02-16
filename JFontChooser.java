
// Name: De Rivera, Jann Lowe


import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JFontChooser{

	private static int fontSize;

	public static Font showDialog(JFrame parent, Font initialFont) {
		JDialog fontChooser = new JDialog(parent, "Font", true);
		fontChooser.setSize(500, 400);
		fontChooser.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		fontChooser.setLocationRelativeTo(parent);
		fontChooser.setResizable(false);
		
		JLabel fontLabel = new JLabel("Font:");
		JLabel styleLabel = new JLabel("Style:");
		
		JPanel outerPanel = new JPanel(new GridLayout(3, 1));
		JPanel listPanel = new JPanel(new GridLayout(1, 2));
		JPanel buttonPanel = new JPanel();
		JPanel labelPanel = new JPanel(new GridLayout(1, 2));
		JLabel sample = new JLabel("The quick brown fox jumps over the lazy dog 0123456789");
		sample.setFont(initialFont);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener((ae) -> fontChooser.dispose());
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener((ae) -> fontChooser.dispose());		
		
		JList<String> fontList = new JList<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontList.setSelectedIndex(0);
        
		
		JList<String> styleList = new JList<>(new String[] {"Regular", "Italic", "Bold", "Bold Italic"});
		styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		styleList.setSelectedIndex(0);

		JSlider sizeSlider = new JSlider(8, 50, 12);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setMajorTickSpacing(2);
		sizeSlider.setLabelTable(sizeSlider.createStandardLabels(2));
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		sizeSlider.addChangeListener(new ChangeListener() {  
		      public void stateChanged(ChangeEvent e) {  
		        if(sizeSlider.getValueIsAdjusting()) return; 
		        
		        fontSize = sizeSlider.getValue();
		        sample.setFont(new Font((String)fontList.getSelectedValue(), sample.getFont().getStyle(), fontSize));
		      }
		    });  
        
        fontList.addListSelectionListener((le) -> {;
        	sample.setFont(new Font((String)fontList.getSelectedValue(), sample.getFont().getStyle(), fontSize));
        });
        
        styleList.addListSelectionListener((le) -> {
        	if(styleList.getSelectedIndex() == 0) {
        		sample.setFont(new Font((String)fontList.getSelectedValue(), Font.PLAIN, fontSize));
        	}else if(styleList.getSelectedIndex() == 1) {
        		sample.setFont(new Font((String)fontList.getSelectedValue(), Font.ITALIC, fontSize));
        	}else if(styleList.getSelectedIndex() == 2) {
        		sample.setFont(new Font((String)fontList.getSelectedValue(), Font.BOLD, fontSize));
        	}else if(styleList.getSelectedIndex() == 3) {
        		sample.setFont(new Font((String)fontList.getSelectedValue(), Font.BOLD + Font.ITALIC, fontSize));
        	}
        }
        
        );
        labelPanel.add(fontLabel);
        labelPanel.add(styleLabel);
        
        listPanel.add(new JScrollPane(fontList));
        listPanel.add(styleList);
        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        outerPanel.add(listPanel);
        outerPanel.add(sizeSlider);
        outerPanel.add(buttonPanel);
        
        fontChooser.add(labelPanel, BorderLayout.NORTH);
        fontChooser.add(outerPanel);
        fontChooser.add(sample, BorderLayout.SOUTH);
        
        fontChooser.setVisible(true);
        
        return okButton.getActionCommand().equals("Ok") ? sample.getFont() : null;
	}
}
