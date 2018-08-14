package durokovic.ljetnizadatak.model;

public class RoundType {
	private int id;
	private int rank;
	private String name;
	private String cellName;
	private int finale;
	
	// Constructors
	public RoundType() {
	}
	public RoundType(int id, int rank, String name, String cellName, int finale) {
		this.id = id;
		this.rank = rank;
		this.name = name;
		this.cellName = cellName;
		this.finale = finale;
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public int getFinale() {
		return finale;
	}
	public void setFinale(int finale) {
		this.finale = finale;
	}
	
	@Override
    public String toString() {
        return String.format
            ("Round type [id=%s, rank=%s, name=%s, cellName=%s, final=%s]",  id, rank, name, cellName, finale);
    
    } 
    
    public String toString(int i){
        return String.format("%s", i);
    }
	
}
