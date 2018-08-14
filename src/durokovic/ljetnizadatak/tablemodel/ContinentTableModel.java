package durokovic.ljetnizadatak.tablemodel;

import durokovic.ljetnizadatak.model.*;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ContinentTableModel extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
    public static final int ID_COL = 0;
    public static final int NAME_COL = 1;
    public static final int RECORD_NAME_COL = 2;
    public static final int LATITUDE_COL = 3;
    public static final int LONGITUDE_COL = 4;
    public static final int ZOOM_COL = 5;
    
    private static final String[] columnNames = 
        {"id", "name", "recordName", "latitude", "longitude", "zoom"};
    
    private List<Continent> continents;
    
    public ContinentTableModel(List<Continent> theContinents) {
        continents = theContinents;
    }
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return continents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Continent tempContinent = continents.get(row);
        
        switch (col) {
            case ID_COL:
                return tempContinent.getId();
            case NAME_COL:
                return tempContinent.getName();
            case RECORD_NAME_COL:
                return tempContinent.getRecordName();
            case LATITUDE_COL:
                return tempContinent.getLatitude();
            case LONGITUDE_COL:
                return tempContinent.getLongitude();
            case ZOOM_COL:
                return tempContinent.getZoom();
            case OBJECT_COL:
                return tempContinent;
            default:
                return tempContinent.getName();    
        }
    }
    
    @Override
    public Class getColumnClass(int col){
        return getValueAt(0, col).getClass();
    }
}
