package durokovic.ljetnizadatak.view;

import java.awt.Component;
import java.awt.Cursor;
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
import javax.swing.border.TitledBorder;

import durokovic.ljetnizadatak.controller.ContinentController;
import durokovic.ljetnizadatak.model.Continent;
import durokovic.ljetnizadatak.tablemodel.ContinentTableModel;

public class ContinentFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField nameField;
	private JTextField recordNameField;
	private JTextField latitudeField;
	private JTextField zoomField;
	private JTextField longitudeField;
	private ContinentController continentController;
	private int row;

	/**
	 * Create the frame.
	 */
	public ContinentFrame() {
		try {
            continentController = new ContinentController();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
		setTitle("Continent Frame");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 598, 473);
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
		        Continent tempContinent = (Continent) table.getValueAt(row, ContinentTableModel.OBJECT_COL);
		        populateContinentGui(tempContinent);
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setName("");
		fieldsPanel.setBounds(10, 159, 570, 195);
		contentPane.add(fieldsPanel);
		fieldsPanel.setLayout(null);
		
		JLabel nameLbl = new JLabel("Name: ");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLbl.setBounds(10, 11, 96, 27);
		fieldsPanel.add(nameLbl);
		
		JLabel recordNameLbl = new JLabel("Record Name: ");
		recordNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		recordNameLbl.setBounds(10, 49, 96, 27);
		fieldsPanel.add(recordNameLbl);
		
		JLabel latitudeLbl = new JLabel("Latitude: ");
		latitudeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		latitudeLbl.setBounds(10, 87, 96, 27);
		fieldsPanel.add(latitudeLbl);
		
		JLabel zoomLbl = new JLabel("Zoom: ");
		zoomLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		zoomLbl.setBounds(305, 49, 96, 27);
		fieldsPanel.add(zoomLbl);
		
		JLabel longitudeLbl = new JLabel("Longitude: ");
		longitudeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		longitudeLbl.setBounds(305, 87, 96, 27);
		fieldsPanel.add(longitudeLbl);
		
		JLabel infoLbl = new JLabel("");
		infoLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		infoLbl.setBounds(10, 125, 564, 59);
		infoLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		infoLbl.setBorder(new TitledBorder(null, "Info Label", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		fieldsPanel.add(infoLbl);
		
		nameField = new JTextField();
		nameField.setName("Name");
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
		nameField.setBounds(116, 16, 167, 20);
		fieldsPanel.add(nameField);
		nameField.setColumns(10);
		
		recordNameField = new JTextField();
		recordNameField.setName("Record Name");
		recordNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
		        if(recordNameField.getText().length() > 2){
		            infoLbl.setText("Limit is 3 characters!");
		            getToolkit().beep();
		            arg0.consume();
		        }
			}
		});
		recordNameField.setColumns(10);
		recordNameField.setBounds(116, 54, 167, 20);
		fieldsPanel.add(recordNameField);
		
		latitudeField = new JTextField();
		latitudeField.setName("Latitude");
		latitudeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char e = evt.getKeyChar();
		        if(!(Character.isDigit(e))){
		            infoLbl.setText("Numbers only!");
		            getToolkit().beep();
		            evt.consume();
		        }
		        
		        if(latitudeField.getText().contains(".") && e == '.'){
		            infoLbl.setText("Only one dot is allowed!");
		            getToolkit().beep();
		            evt.consume();
		        }
		        
		        if(latitudeField.getText().length() > 10){
		            infoLbl.setText("You can't input more than 11 numbers!");
		            getToolkit().beep();
		            evt.consume();
		        }
			}
		});
		latitudeField.setColumns(10);
		latitudeField.setBounds(116, 94, 167, 20);
		fieldsPanel.add(latitudeField);
		
		zoomField = new JTextField();
		zoomField.setName("Zoom");
		zoomField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char e = evt.getKeyChar();
		        if(!(Character.isDigit(e))){
		            infoLbl.setText("Numbers only!");
		            getToolkit().beep();
		            evt.consume();
		        }
		        if(zoomField.getText().length() > 1){
		            infoLbl.setText("You can't input more than 2 numbers!");
		            getToolkit().beep();
		            evt.consume();
		        }
			}
		});
		zoomField.setColumns(10);
		zoomField.setBounds(383, 54, 167, 20);
		fieldsPanel.add(zoomField);
		
		longitudeField = new JTextField();
		longitudeField.setName("Longitude");
		longitudeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char e = evt.getKeyChar();
		        if(!(Character.isDigit(e))){
		            infoLbl.setText("Numbers only!");
		            getToolkit().beep();
		            evt.consume();
		        }
		        
		        if(longitudeField.getText().contains(".") && e == '.'){
		            infoLbl.setText("Only one dot is allowed!");
		            getToolkit().beep();
		            evt.consume();
		        }
		        
		        if(longitudeField.getText().length() > 10){
		            infoLbl.setText("You can't input more than 11 numbers!");
		            getToolkit().beep();
		            evt.consume();
		        }
			}
		});
		longitudeField.setColumns(10);
		longitudeField.setBounds(383, 92, 167, 20);
		fieldsPanel.add(longitudeField);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setBounds(10, 365, 570, 68);
		contentPane.add(btnPanel);
		
		JButton addBtn = new JButton("Add");
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(nameField.getText().trim().isEmpty() 
		                || recordNameField.getText().trim().isEmpty()
		                || latitudeField.getText().trim().isEmpty()
		                || longitudeField.getText().trim().isEmpty()
		                || zoomField.getText().trim().isEmpty()
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
		            String recordName = recordNameField.getText();
		            String latitude = latitudeField.getText();
		            String longitude = longitudeField.getText();
		            int zoom = Integer.parseInt(zoomField.getText());
		            
		            continentController.addContinent(name, recordName, latitude, longitude, zoom);

		            refreshContinentsView();

		            for (Component components : fieldsPanel.getComponents()) {
		                if (components instanceof JTextField) {
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
		            //  Getting id to erase from table continents
		            row = table.getSelectedRow();
		            int id = (int) table.getValueAt(row, ContinentTableModel.ID_COL);
		            //  Deleting continent
		            continentController.deleteContinent(id);
		            //  Showing table view after change
		            refreshContinentsView();
		            //  Clearing text fields
		            for (Component components : fieldsPanel.getComponents()) {
		                if (components instanceof JTextField) {
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
			public void mouseEntered(MouseEvent evt) {
				row = table.getSelectedRow();
		        if(nameField.getText().trim().isEmpty() 
		                || recordNameField.getText().trim().isEmpty()
		                || latitudeField.getText().trim().isEmpty()
		                || longitudeField.getText().trim().isEmpty()
		                || zoomField.getText().trim().isEmpty()
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
		            String recordName = recordNameField.getText();
		            String latitude = latitudeField.getText();
		            String longitude = longitudeField.getText();
		            int zoom = Integer.parseInt(zoomField.getText());
		            
		            row = table.getSelectedRow();
		            
		            int id = (int) table.getValueAt(row, ContinentTableModel.ID_COL);
		            
		            continentController.updateContinent(id, name, recordName, latitude, longitude, zoom);
		            
		            refreshContinentsView();
		            
		            table.setRowSelectionInterval(row, row);
		        }catch (Exception e) {
					e.printStackTrace();
				}  
			}
		});
		updateBtn.setBounds(216, 11, 149, 33);
		btnPanel.add(updateBtn);
		
		JLabel clearSelectionLbl = new JLabel("Clear Selection");
		clearSelectionLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clearSelectionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				for (Component components : fieldsPanel.getComponents()) {
		            if (components instanceof JTextField) {
		                ((JTextField) components).setText("");
		            }
		        }
		        infoLbl.setText("");
		        table.clearSelection();
			}
		});
		clearSelectionLbl.setBounds(10, 55, 149, 14);
		btnPanel.add(clearSelectionLbl);
		
		refreshContinentsView();
	}
	
    private void refreshContinentsView(){
        try {
            List<Continent> continents = continentController.getAllContinents();
            ContinentTableModel model = new ContinentTableModel(continents);
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void populateContinentGui(Continent tempContinent) {
        nameField.setText(tempContinent.getName());
        recordNameField.setText(tempContinent.getRecordName());
        longitudeField.setText(tempContinent.getLongitude());
        latitudeField.setText(tempContinent.getLatitude());
        zoomField.setText(tempContinent.toString(tempContinent.getZoom()));
    }
}
