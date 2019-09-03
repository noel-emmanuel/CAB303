package ourPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.FilerException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Main {
	
	/**
	 * Creates a GUI
	 * 
	 * @author Vaibhav Vachhani	
	 */
	private static JButton button;
	private static JButton button1;
	private static JButton button2;
	private static JButton button4;
	
	private static JFrame frame;
	private static JFormattedTextField amount;
	private static Store myStore = Store.getInstance();
	//initialising the table
	private static String[] columns = {"item name", "quantity", "manufacturing cost","sell price", "reorder point", "reorder amount", "temperature"};
    private static DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
    private static JTable table = new JTable(tableModel);
	    
	public Main() {
		// TODO Auto-generated constructor stub
		
		
		/*
		 * Create a frame, set the size and make it visible
		 */
		frame = new JFrame("SuperMart");		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel( new GridBagLayout());
		 
		
		GridBagConstraints c = new GridBagConstraints();
		
		// creating labels and buttons and adding an action listener to them
		JLabel label_name = new JLabel("Welcome to SuperMart!");
		
		JLabel label = new JLabel("Import item properties:");
		button = new JButton("browse");
		button.addActionListener(new Action());
		
		JLabel label1 = new JLabel("Import sales log:");
		button1 = new JButton("browse");
		button1.addActionListener(new Action());
		
		JLabel label2 = new JLabel("Import manifest:");
		button2 = new JButton("browse");
		button2.addActionListener(new Action());
		
		JLabel label4 = new JLabel("Export manifest:");
		button4 = new JButton("Export manifest");
		button4.addActionListener(new Action());

		JLabel label3 = new JLabel("Store Capital:");
	    amount = new JFormattedTextField(java.text.NumberFormat.getCurrencyInstance());
	    
	    amount.setValue(new Float(myStore.capital));

	    
		c.insets = new Insets(7, 7, 7, 7);
		
		
		
		// adding buttons and labels to the panel
		
		c.gridwidth = 4;
		c.gridx = -1;
		c.gridy = 0;
		panel.add(label_name, c);
		c.gridwidth = 1;
		c.gridx = -1;
		c.gridy = 1;
		panel.add(label, c);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(button, c); 
		c.gridx = -1;
		c.gridy = 2;
		panel.add(label1, c); 
		c.gridx = 1;
		c.gridy = 2;
		panel.add(button1, c);
		c.gridx = -1;
		c.gridy =  3;
		panel.add(label2, c); 
		c.gridx = 1;
		c.gridy = 3;
		panel.add(button2, c);
		c.gridx = -1;
		c.gridy = 4;
		panel.add(label3, c);
		c.gridx = 1;
		c.gridy = 4;
		panel.add(amount, c);
		c.gridy = 5;
		c.gridx = -1;
		panel.add(label4, c);
		c.gridx = 1;
		c.gridy = 5;
		panel.add(button4, c);
		c.gridy = 6;
		c.gridx = -1;
		
		// formatting the table
		table.setForeground(Color.BLACK);
		table.setGridColor(Color.BLUE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		//adding the table to a new pane
		JScrollPane jpane = new JScrollPane(table);
		
		//adding everything to the main frame
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		frame.add(jpane);
		frame.setVisible(true);
		frame.pack();
		
		
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
		
	}

	static class Action implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)  {
			
			if(e.getSource() == button)
			{
				try {
					importItems();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
			
			}
			
			if(e.getSource() == button1)
			{
				try {
					importSalesLog();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
			
			
			}
			
			if(e.getSource() == button2) 
			{
				try {
					loadManifest();
				} 
				
				catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
			
			}
			
			if(e.getSource() == button4) 
			{
				//exportmanifest method here
				try {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
					int result = fileChooser.showOpenDialog(fileChooser);
					File selectedFile = null;
					if (result == JFileChooser.APPROVE_OPTION) {
						selectedFile = fileChooser.getSelectedFile();
					   String path = selectedFile.getPath();
					   myStore.exportManifest(path);
					   }
				}
				
				catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage());
					}
			}
			
			ArrayList<Item> myList = myStore.inventory.loop();
			table.setShowGrid(true);
			for(Item i : myList)
			 {
			    	Object[] objs = {i.getName(), myStore.inventory.getItemCount(i),  i.getManCost(), i.getSellPrice(), i.getReorderPoint(), i.getReorderAmount(), i.getTempControl()};
			    	tableModel.addRow(objs); 
			    	
			 }
			 amount.setValue(new Float(myStore.capital));
			
			
		}

		private void importSalesLog() throws Exception{
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(fileChooser);
			File selectedFile = null;
			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
			   //String type = fileChooser.getDescription(selectedFile);
			   String path = selectedFile.getPath();
			   myStore.loadSalesLog(path);
			   String type = selectedFile.getName();
			   String extension = type.substring(type.lastIndexOf(".")+1);
			   
			   if(extension.equalsIgnoreCase("csv"))
			   {
				   myStore.loadSalesLog(path);
				  
			   }
			   else
			   {
				   throw new Exception("Something wemt wrong. Please try again.");
			   }
			   
			   
			   
			  
			

			}
		}

		private void loadManifest() throws Exception {
			
			
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(fileChooser);
				if (result == JFileChooser.APPROVE_OPTION) {
				   File selectedFile = fileChooser.getSelectedFile();
				   String path = selectedFile.getPath();
				   String type = selectedFile.getName();
				   String extension = type.substring(type.lastIndexOf(".")+1);
				   
				   if(extension.equalsIgnoreCase("csv"))
				   {
					   myStore.importManifest(path);
					  
				   }
				   else
				   {
					   throw new Exception("Something wemt wrong. Please try again.");
				   }
				}
			
		}

		private void importItems() throws Exception {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(fileChooser);
			if (result == JFileChooser.APPROVE_OPTION) {
			  File selectedFile = fileChooser.getSelectedFile();
			 
			  String path = selectedFile.getPath();
			  String type = selectedFile.getName();
			  String extension = type.substring(type.lastIndexOf(".")+1);
			   
			   if(extension.equalsIgnoreCase("csv"))
			   {
				   //use method
				   myStore.loadItemProperties(path);
				
				  

			   }
			   else
			   {
				   throw new Exception("Something wemt wrong. Please try again.");
			   }
			  
			  
			}
		}
		
		
	}
	

}
