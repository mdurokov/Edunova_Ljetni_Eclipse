package durokovic.ljetnizadatak.model;

public class Continent {
	private int id;
	private String name;
	private String recordName;
	private String longitude;
	private String latitude;
	private int zoom;
	
	// Constructors
	public Continent() {
	}
	
	public Continent(int id, String name, String recordName, String longitude, String latitude, int zoom) {
		this.id = id;
		this.name = name;
		this.recordName = recordName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.zoom = zoom;
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
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	@Override
	public String toString() {
		return String.format
	            ("Continent [id=%s, name=%s, recordName=%s, longitue=%s, latitude=%s, zoom=%s]",  id, name, recordName, longitude, latitude, zoom);
	}
	
	public String toString(int i){
        return String.format("%s", i);
    }
	
	
	
	
}
