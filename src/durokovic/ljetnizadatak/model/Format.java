package durokovic.ljetnizadatak.model;

public class Format {
	private int id;
	private String name;
	
	// Constructors	
	public Format() {
		super();
	}
	public Format(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	 @Override
    public String toString() {
        return String.format
            ("Format [id=%s, name=%s]",  id, name);
    
    } 
    
    public String toString(int i){
        return String.format("%s", i);
    }
	
	
}
