package durokovic.ljetnizadatak.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import durokovic.ljetnizadatak.controller.EventController;
import durokovic.ljetnizadatak.model.Event;
import durokovic.ljetnizadatak.tablemodel.EventTableModel;
import java.awt.Cursor;

public class EventFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField nameField;
	private JTextField formatField;
	private JTextField rankField;
	private JTextField cellNameField;
	private	EventController eventController;
	private int row;

	/**
	 * Create the frame.
	 */
	public EventFrame() {
		try {
            eventController = new EventController();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
		setTitle("Event Frame");
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
		        Event tempEvent = (Event) table.getValueAt(row, EventTableModel.OBJECT_COL);
		        populateEventGui(tempEvent);
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
		
		JLabel formatLbl = new JLabel("Format");
		formatLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formatLbl.setBounds(10, 49, 96, 27);
		fieldsPanel.add(formatLbl);
		
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
			public void keyTyped(KeyEvent arg0) {
				if(nameField.getText().length() > 49){
		            infoLbl.setText("Limit is 50 characters!");
		            getToolkit().beep();
		            arg0.consume();
		        }
			}
		});
		nameField.setBounds(77, 16, 167, 20);
		fieldsPanel.add(nameField);
		nameField.setColumns(10);
		
		formatField = new JTextField();
		formatField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
		        if(formatField.getText().length() > 9){
		            infoLbl.setText("Limit is 10 characters!");
		            getToolkit().beep();
		            evt.consume();
		        }
			}
		});
		formatField.setColumns(10);
		formatField.setBounds(77, 54, 167, 20);
		fieldsPanel.add(formatField);
		
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
		            infoLbl.setText("Limit is 10 numbers!");
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
		btnPanel.setBounds(10, 322, 570, 68);
		contentPane.add(btnPanel);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
		            String name = nameField.getText();
		            int rank = Integer.parseInt(rankField.getText());
		            String format = formatField.getText();
		            String cellName = cellNameField.getText();
		            
		            eventController.addEvent(name, rank, format, cellName);
		            refreshEventView();
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
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(nameField.getText().trim().isEmpty() 
		                || rankField.getText().trim().isEmpty()
		                || formatField.getText().trim().isEmpty()
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
		addBtn.setBounds(10, 11, 149, 33);
		btnPanel.add(addBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
		            row = table.getSelectedRow();
		            int id = (int) table.getValueAt(row, EventTableModel.ID_COL);
		            eventController.deleteEvent(id);
		            refreshEventView();
		            for(Component components : fieldsPanel.getComponents()){
			            if(components instanceof JTextField){
			                ((JTextField) components).setText("");
			            }
			        }
			        infoLbl.setText("");
			        table.clearSelection();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}
		});
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
		deleteBtn.setBounds(411, 11, 149, 33);
		btnPanel.add(deleteBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
		            String name = nameField.getText();
		            int rank = Integer.parseInt(rankField.getText());
		            String format = formatField.getText();
		            String cellName = cellNameField.getText();
		            
		            row = table.getSelectedRow();
		            int id = (int) table.getValueAt(row, EventTableModel.ID_COL);
		            
		            eventController.updateEvent(id, name, rank, format, cellName);
		            refreshEventView();
		            table.setRowSelectionInterval(row, row);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}
		});
		updateBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				row = table.getSelectedRow();
		        if(nameField.getText().trim().isEmpty() 
		                || rankField.getText().trim().isEmpty()
		                || formatField.getText().trim().isEmpty()
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
		updateBtn.setBounds(216, 11, 149, 33);
		btnPanel.add(updateBtn);
		
		JLabel clearSelectionLbl = new JLabel("Clear Selection");
		clearSelectionLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		refreshEventView();
	}
	
	private void refreshEventView(){
        try {
            List<Event> events = eventController.getAllEvents();
            EventTableModel model = new EventTableModel(events);
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void populateEventGui(Event tempEvent){
        nameField.setText(tempEvent.getName());
        rankField.setText(tempEvent.toString(tempEvent.getRank()));
        formatField.setText(tempEvent.getFormat());
        cellNameField.setText(tempEvent.getCellName());
    }
}
