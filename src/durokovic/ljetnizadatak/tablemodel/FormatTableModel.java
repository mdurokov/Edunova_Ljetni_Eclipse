package durokovic.ljetnizadatak.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import durokovic.ljetnizadatak.model.Format;

public class FormatTableModel extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
    public static final int ID_COL = 0;
    public static final int NAME_COL = 1;
    
    private static final String[] columnNames = {"id", "name"};
    private List<Format> formats;
            
    public FormatTableModel(List<Format> theFormats){
        formats = theFormats;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() {
        return formats.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Format tempFormats = formats.get(row);
        
        switch(col){
            case OBJECT_COL:
                return tempFormats;
            case ID_COL:
                return tempFormats.getId();
            case NAME_COL:
                return tempFormats.getName();
            default:
                return tempFormats.getName();
        }
    }

    @Override
    public Class getColumnClass(int col){
        return getValueAt(0, col).getClass();
    }

}
