package durokovic.ljetnizadatak.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import durokovic.ljetnizadatak.model.Event;

public class EventTableModel extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
    public static final int ID_COL = 0;    
    public static final int NAME_COL = 1;    
    public static final int RANK_COL = 2;    
    public static final int FORMAT_COL = 3;    
    public static final int CELL_NAME_COL = 4; 
    
private static final String[] columnNames = {"id", "name", "rank", "format", "cellName"};
    
    private List<Event> events;
        
    public EventTableModel(List<Event> theEvents){
        events = theEvents;
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() {
        return events.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Event tempEvent = events.get(row);
        
        switch(col){
            case OBJECT_COL:
                return tempEvent;
            case ID_COL:
                return tempEvent.getId();
            case NAME_COL:
                return tempEvent.getName();
            case RANK_COL:
                return tempEvent.getRank();
            case FORMAT_COL:
                return tempEvent.getFormat();
            case CELL_NAME_COL:
                return tempEvent.getCellName();
            default:
                return tempEvent.getName();
        }
    }
    
    @Override
    public Class getColumnClass(int col){
        return getValueAt(0, col).getClass();
    }

}
