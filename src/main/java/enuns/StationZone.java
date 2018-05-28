package enuns;


public enum StationZone {
	
	HOLBORN("1"),
    EARLS_COURT("1,2"),
    WIMBLEDON("3"),
    HAMMERSMITH("2");
	
	private String zone;

    private StationZone(String zone)
    {
        this.zone = zone;
    }


    public String getZone() {
       return zone;
    }
    }
