package com.kleber.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entity.Card;
import entity.Fare;
import entity.Journey;
import enuns.StationZone;
import enuns.Transport;
import exception.FareException;

public class FareTest {

	@Test(expected = FareException.class)
	public void testValidadeBusException() {

		Card card = new Card(Fare.BUS_FARE - 1f);
		Fare fare = new Fare();
		fare.validade(Transport.BUS, card);
	}

	@Test(expected = FareException.class)
	public void testValidadeTubeFareException() {

		Card card = new Card(Fare.BASIC_TUBE_FARE - 1f);
		Fare fare = new Fare();
		fare.validade(Transport.TUBE, card);
	}

	@Test
	public void testChargeMaxTube() {
		Card card = new Card(Fare.BASIC_TUBE_FARE);
		Fare fare = new Fare();
		fare.chargeMax(Transport.TUBE, card);
		assertEquals(0f, card.getBalance(), 0.0f);
	}

	@Test
	public void testChargeMaxBus() {
		Card card = new Card(Fare.BUS_FARE);
		Fare fare = new Fare();
		fare.chargeMax(Transport.BUS, card);
		assertEquals(0f, card.getBalance(), 0.0f);
	}

	@Test
	public void testChargeBus() {
		Card card = new Card(Fare.BUS_FARE);
		Fare fare = new Fare();
		Journey jorneyBusEarlToChelsea = new Journey();
		jorneyBusEarlToChelsea.setStartPoint(Transport.BUS, null, card);
		jorneyBusEarlToChelsea.setEndPoint(null);
		fare.charge(Transport.BUS,jorneyBusEarlToChelsea, card);
		assertEquals(0f, card.getBalance(), 0.0f);
	}
	
	@Test
	public void testChargeTubZoneOne() {
		Card card = new Card(Fare.BASIC_TUBE_FARE);
		Fare fare = new Fare();
		Journey jorneyBusEarlToChelsea = new Journey();
		jorneyBusEarlToChelsea.setStartPoint(Transport.TUBE, StationZone.HOLBORN, card);
		jorneyBusEarlToChelsea.setEndPoint(StationZone.EARLS_COURT);
		assertEquals(Fare.BASIC_TUBE_FARE - Fare.ZONE_ONE_FARE, card.getBalance(), 0.001f);
	}
	
	@Test
	public void testChargeTubAnyZoneOutSideZoneOne() {
		Card card = new Card(Fare.BASIC_TUBE_FARE);
		Fare fare = new Fare();
		Journey jorneyBusEarlToChelsea = new Journey();
		jorneyBusEarlToChelsea.setStartPoint(Transport.TUBE, StationZone.HAMMERSMITH, card);
		jorneyBusEarlToChelsea.setEndPoint(StationZone.EARLS_COURT);
	//	assertEquals(Fare.BASIC_TUBE_FARE - Fare.ANY_ZONE_OUTSIDE_ZONE_ONE_FARE, card.getBalance(), 0.001f);
	}
	
	@Test
	public void testChargeTubTwoInZoneOne() {
		Card card = new Card(Fare.BASIC_TUBE_FARE);
		Fare fare = new Fare();
		Journey jorneyBusEarlToChelsea = new Journey();
		jorneyBusEarlToChelsea.setStartPoint(Transport.TUBE, StationZone.HAMMERSMITH, card);
		jorneyBusEarlToChelsea.setEndPoint(StationZone.HOLBORN);
		assertEquals(Fare.BASIC_TUBE_FARE - Fare.TWO_ZONES_INC_ZONE_ONE_FARE, card.getBalance(), 0.001f);
	}
	
	@Test
	public void testChargeTubTwoExcZoneOne() {
		Card card = new Card(Fare.BASIC_TUBE_FARE);
		Fare fare = new Fare();
		Journey jorneyBusEarlToChelsea = new Journey();
		jorneyBusEarlToChelsea.setStartPoint(Transport.TUBE, StationZone.HAMMERSMITH, card);
		jorneyBusEarlToChelsea.setEndPoint(StationZone.WIMBLEDON);
		assertEquals(Fare.BASIC_TUBE_FARE - Fare.TWO_ZONES_EXC_ZONE_ONE_FARE, card.getBalance(), 0.001f);
	}
	
	@Test
	public void testChargeTubThreeoZones() {
		Card card = new Card(Fare.BASIC_TUBE_FARE);
		Fare fare = new Fare();
		Journey jorneyBusEarlToChelsea = new Journey();
		jorneyBusEarlToChelsea.setStartPoint(Transport.TUBE, StationZone.HOLBORN, card);
		jorneyBusEarlToChelsea.setEndPoint(StationZone.WIMBLEDON);
		assertEquals(Fare.BASIC_TUBE_FARE - Fare.THREE_ZONES_FAIR, card.getBalance(), 0.001f);
	}
	
}
