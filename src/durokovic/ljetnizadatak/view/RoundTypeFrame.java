package durokovic.ljetnizadatak.view;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import durokovic.ljetnizadatak.controller.RoundTypeController;
import durokovic.ljetnizadatak.model.RoundType;
import durokovic.ljetnizadatak.tablemodel.RoundTypeTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundTypeFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField nameField;
	private JTextField finalField;
	private JTextField rankField;
	private JTextField cellNameField;
	private JLabel infoLbl;
	private RoundTypeController roundTypeController;
    private int row;

	/**
	 * Create the frame.
	 */
	public RoundTypeFrame() {
		try {
            roundTypeController = new RoundTypeController();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
		setTitle("Round Type Frame");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 598, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(10, 11, 570, 137);
		contentPane.add(tablePanel);
		tablePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 570, 137);
		tablePanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
		        RoundType tempFormat = (RoundType) table.getValueAt(row, RoundTypeTableModel.OBJECT_COL);
		        populateRoundTypeGui(tempFormat);
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setBounds(10, 159, 570, 152);
		contentPane.add(fieldsPanel);
		fieldsPanel.setLayout(null);
		
		JLabel infoLbl = new JLabel("");
		infoLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		infoLbl.setBounds(10, 87, 564, 59);
		infoLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		infoLbl.setBorder(javax.swing.BorderFactory.createTitledBorder("Info Table"));
		fieldsPanel.add(infoLbl);
		
		JLabel nameLbl = new JLabel("Name: ");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLbl.setBounds(10, 11, 96, 27);
		fieldsPanel.add(nameLbl);
		
		JLabel finalLbl = new JLabel("Final: ");
		finalLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		finalLbl.setBounds(10, 49, 96, 27);
		fieldsPanel.add(finalLbl);
		
		JLabel rankLbl = new JLabel("Rank: ");
		rankLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rankLbl.setBounds(277, 11, 96, 27);
		fieldsPanel.add(rankLbl);
		
		JLabel cellNameLbl = new JLabel("Cell Name: ");
		cellNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cellNameLbl.setBounds(277, 49, 96, 27);
		fieldsPanel.add(cellNameLbl);
		
		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if(nameField.getText().length() > 49){
		            infoLbl.setText("Limit is 50 characters!");
		            getToolkit().beep();
		            evt.consume();
		        }
			}
		});
		nameField.setBounds(77, 16, 167, 20);
		fieldsPanel.add(nameField);
		nameField.setColumns(10);
		
		finalField = new JTextField();
		finalField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char e = evt.getKeyChar();
		        if(e != '1'  && e != '0'){
		            infoLbl.setText("Enter 1 or 0! 1(true), 0(false)");
		            getToolkit().beep();
		            evt.consume();
		        }
		        if(finalField.getText().length() > 0){
		            infoLbl.setText("You can enter only one number!");
		            getToolkit().beep();
		            evt.consume();
		        }
			}
		});
		finalField.setColumns(10);
		finalField.setBounds(77, 54, 167, 20);
		fieldsPanel.add(finalField);
		
		rankField = new JTextField();
		rankField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char e = evt.getKeyChar();
		        if(!(Character.isDigit(e))){
		            infoLbl.setText("Numbers only!");
		            getToolkit().beep();
		            evt.consume();
		        }
		        
		        if(rankField.getText().length() > 9){
		            infoLbl.setText("Limit is 10 characters!");
		            getToolkit().beep();
		            evt.consume();
		        }
			}
		});
		rankField.setColumns(10);
		rankField.setBounds(383, 16, 167, 20);
		fieldsPanel.add(rankField);
		
		cellNameField = new JTextField();
		cellNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if(cellNameField.getText().length() > 49){
		            infoLbl.setText("Limit is 50 characters!");
		            getToolkit().beep();
		            evt.consume();
		        }
			}
		});
		cellNameField.setColumns(10);
		cellNameField.setBounds(383, 54, 167, 20);
		fieldsPanel.add(cellNameField);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setBounds(10, 319, 570, 68);
		contentPane.add(btnPanel);
		
		JButton addBtn = new JButton("Add");
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(nameField.getText().trim().isEmpty() 
		                || rankField.getText().trim().isEmpty()
		                || finalField.getText().trim().isEmpty()
		                || cellNameField.getText().trim().isEmpty()
		            ){
		            addBtn.setEnabled(false);
		            infoLbl.setText("Fields can't be empty!");
		        }
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addBtn.setEnabled(true);
			}
		});
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
		            String name = nameField.getText();
		            int rank = Integer.parseInt(rankField.getText());
		            String cellName = cellNameField.getText();
		            int finale = Integer.parseInt(finalField.getText());
		            
		            roundTypeController.addRoundType(rank, name, cellName, finale);
		            refreshRoundTypeView();
		            for(Component components : fieldsPanel.getComponents()){
		                if(components instanceof JTextField){
		                    ((JTextField) components).setText("");
		                }
		            }
		            infoLbl.setText("");
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}
		});
		addBtn.setBounds(10, 11, 149, 33);
		btnPanel.add(addBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				row = table.getSelectedRow();
		        if(row==-1){
		            deleteBtn.setEnabled(false);
		            infoLbl.setText("Select what to delete!");
		        }
			}
			@Override
			public void mouseExited(MouseEvent e) {
				deleteBtn.setEnabled(true);
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
		            row = table.getSelectedRow();
		            int id = (int) table.getValueAt(row, RoundTypeTableModel.ID_COL);
		            roundTypeController.deleteRoundType(id);
		            refreshRoundTypeView();
		            for(Component components : fieldsPanel.getComponents()){
		                if(components instanceof JTextField){
		                    ((JTextField) components).setText("");
		                }
		            }
		            infoLbl.setText("");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}
		});
		deleteBtn.setBounds(411, 11, 149, 33);
		btnPanel.add(deleteBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				row = table.getSelectedRow();
		        if(nameField.getText().trim().isEmpty() 
		                || rankField.getText().trim().isEmpty()
		                || finalField.getText().trim().isEmpty()
		                || cellNameField.getText().trim().isEmpty()
		            ){
		            updateBtn.setEnabled(false);
		            infoLbl.setText("Fields can't be empty!");
		        }
		        
		        if(row == -1){
		            updateBtn.setEnabled(false);
		            infoLbl.setText("Select what to update!");
		        }
			}
			@Override
			public void mouseExited(MouseEvent e) {
				updateBtn.setEnabled(true);
			}
		});
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
		            String name = nameField.getText();
		            int rank = Integer.parseInt(rankField.getText());
		            String cellName = cellNameField.getText();
		            int finale = Integer.parseInt(finalField.getText());
		            
		            row = table.getSelectedRow();
		            int id = (int) table.getValueAt(row, RoundTypeTableModel.ID_COL);
		            
		            roundTypeController.updateRoundType(id, rank, name, cellName, finale);
		            refreshRoundTypeView();
		            table.setRowSelectionInterval(row, row);
		        } catch (Exception e) {
		            //JOptionPane.showMessageDialog(this,"You need to select what to update!", "Info", JOptionPane.ERROR_MESSAGE);
		            e.printStackTrace();
		        }
			}
		});
		updateBtn.setBounds(216, 11, 149, 33);
		btnPanel.add(updateBtn);
		
		JLabel clearSelectionLbl = new JLabel("Clear Selection");
		clearSelectionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(Component components : fieldsPanel.getComponents()){
	                if(components instanceof JTextField){
	                    ((JTextField) components).setText("");
	                }
	            }
	            infoLbl.setText("");
				table.clearSelection();
			}
		});
		clearSelectionLbl.setBounds(10, 55, 149, 14);
		btnPanel.add(clearSelectionLbl);
        refreshRoundTypeView();
	}
	
	 public void refreshRoundTypeView(){
		 try {
			 List<RoundType> roundTypes = roundTypeController.getAllRoundTypes();
			 RoundTypeTableModel model = new RoundTypeTableModel(roundTypes);
			 table.setModel(model);
	     } catch (Exception e) {
	         JOptionPane.showMessageDialog(this,"Error" + e, "Error", JOptionPane.ERROR_MESSAGE);
	     }
	 }
	 
	 public void populateRoundTypeGui(RoundType tempRoundType){
		 nameField.setText(tempRoundType.getName());
		 rankField.setText(tempRoundType.toString(tempRoundType.getRank()));
		 cellNameField.setText(tempRoundType.getCellName());
		 finalField.setText(tempRoundType.toString(tempRoundType.getFinale()));
	 }
	
}
