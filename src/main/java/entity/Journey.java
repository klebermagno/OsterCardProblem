package entity;

import enuns.StationZone;
import enuns.Transport;
import exception.FareException;
import exception.JourneyException;

public class Journey {

	private StationZone startPoint;
	private StationZone endPoint;
	private Transport transport;

	private Card card;

	public StationZone getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Transport transport, StationZone startPoint, Card card) {
		try {
			Fare.validade(transport, card);
			Fare.chargeMax(transport, card);

		} catch (FareException e) {
			System.out.println(e.getMessage());
		}
		this.transport = transport;
		this.card = card;
		this.startPoint = startPoint;
	}

	public StationZone getEndPoint() {
		return endPoint;
	}

	public void setEndPoint( StationZone endPoint)throws JourneyException {
	
		this.endPoint = endPoint;
		Fare.charge(transport,this, card);
	}

}
