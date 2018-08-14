package durokovic.ljetnizadatak.view;

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

import durokovic.ljetnizadatak.controller.FormatController;
import durokovic.ljetnizadatak.model.Format;
import durokovic.ljetnizadatak.tablemodel.FormatTableModel;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormatFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField nameField;
	private JLabel infoLbl;
	private FormatController formatController;
    private int row;

	/**
	 * Create the frame.
	 */
	public FormatFrame() {
		try {
            formatController = new FormatController();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
		setTitle("Format Frame");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 598, 392);
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
		        Format tempFormat = (Format) table.getValueAt(row, FormatTableModel.OBJECT_COL);
		        populateFormatGui(tempFormat);
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setBounds(10, 159, 570, 115);
		contentPane.add(fieldsPanel);
		fieldsPanel.setLayout(null);
		
		JLabel infoLbl = new JLabel("");
		infoLbl.setFont(new Font("Tahoma", Font.BOLD, 17));
		infoLbl.setBounds(10, 49, 564, 59);
		infoLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		infoLbl.setBorder(javax.swing.BorderFactory.createTitledBorder("Info Table"));
		fieldsPanel.add(infoLbl);
		
		JLabel nameLbl = new JLabel("Name: ");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLbl.setBounds(10, 11, 96, 27);
		fieldsPanel.add(nameLbl);
		
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
		nameField.setBounds(76, 16, 484, 20);
		fieldsPanel.add(nameField);
		nameField.setColumns(10);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(10, 285, 570, 68);
		contentPane.add(btnPanel);
		btnPanel.setLayout(null);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
		            String name = nameField.getText();
		            
		            formatController.addFormat(name);
		            refreshFormatView();
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
			public void mouseEntered(MouseEvent e) {
				if(nameField.getText().trim().isEmpty()){
		            addBtn.setEnabled(false);
		            infoLbl.setText("Field Name can't be empty!");
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
		            int id = (int) table.getValueAt(row, FormatTableModel.ID_COL);
		            
		            formatController.deleteFormat(id);
		            refreshFormatView();
		            for(Component components : fieldsPanel.getComponents()){
		                if(components instanceof JTextField){
		                    ((JTextField) components).setText("");
		                }
		            }         
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
		            row = table.getSelectedRow();
		            int id = (int) table.getValueAt(row, FormatTableModel.ID_COL);
		            String name = nameField.getText();
		            
		            formatController.updateFormat(id, name);
		            refreshFormatView();
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
		        if(nameField.getText().trim().isEmpty()){
		            updateBtn.setEnabled(false);
		            infoLbl.setText("Field Name can't be empty!");
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
		clearSelectionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (Component components : fieldsPanel.getComponents()) {
		            if (components instanceof JTextField) {
		                ((JTextField) components).setText("");
		            }
		        }
		        infoLbl.setText("");
		        table.clearSelection();
			}
		});
		clearSelectionLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clearSelectionLbl.setBounds(10, 55, 149, 14);
		btnPanel.add(clearSelectionLbl);
        refreshFormatView();
	}
	
    public void refreshFormatView(){
        try {
            List<Format> events = formatController.getAllFormats();
            FormatTableModel model = new FormatTableModel(events);
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void populateFormatGui(Format tempEvent){
        nameField.setText(tempEvent.getName());
    }
}
