package durokovic.ljetnizadatak.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import durokovic.ljetnizadatak.model.RoundType;

public class RoundTypeTableModel extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
    public static final int ID_COL = 0;
    public static final int RANK_COL = 1;
    public static final int NAME_COL = 2;
    public static final int CELL_NAME_COL = 3;
    public static final int FINAL_NAME_COL = 4;
    
    private static final String[] columnNames = {"id", "rank", "name", "cellName", "final"};
    private List<RoundType> roundTypes;
    
    public RoundTypeTableModel(List<RoundType> theRoundTypes) {
        roundTypes = theRoundTypes;
    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public int getRowCount() {
        return roundTypes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        RoundType tempRoundTypes = roundTypes.get(row);
        
        switch (col){
            case OBJECT_COL:
                return tempRoundTypes;
            case ID_COL:
                return tempRoundTypes.getId();
            case RANK_COL:
                return tempRoundTypes.getRank();
            case NAME_COL:
                return tempRoundTypes.getName();
            case CELL_NAME_COL:
                return tempRoundTypes.getCellName();
            case FINAL_NAME_COL:
                return tempRoundTypes.getFinale();
            default:
                return tempRoundTypes.getName();
        }
    }

    @Override
    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

}
