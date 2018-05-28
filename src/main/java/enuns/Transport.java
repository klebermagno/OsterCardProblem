package enuns;

public enum Transport {
	BUS("Bus"),
	TUBE("Tube");
	
	private String transport;

    private Transport(String zone)
    {
        this.transport = zone;
    }


    public String getTransport() {
       return transport;
    }
}
