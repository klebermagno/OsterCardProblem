package entity;

import enuns.Transport;
import exception.FareException;

public class Fare {

	public static final float ZONE_ONE_FARE = 2.50f;

	public static final float ANY_ZONE_OUTSIDE_ZONE_ONE_FARE = 2.00f;

	public static final float TWO_ZONES_INC_ZONE_ONE_FARE = 3.00f;

	public static final float TWO_ZONES_EXC_ZONE_ONE_FARE = 2.25f;

	public static final float THREE_ZONES_FAIR = 3.20f;

	public static final float BUS_FARE = 1.80f;

	public static final float BASIC_TUBE_FARE = 3.20f;


	public static void validade(Transport transport, Card card) throws FareException {

		if (transport.equals(Transport.BUS))
			card.validade(BUS_FARE);

		if (transport.equals(Transport.TUBE))
			card.validade(BASIC_TUBE_FARE);

	}

	public static void chargeMax(Transport transport, Card card) {
		if (transport.equals(Transport.BUS))
			card.out(BUS_FARE);

		if (transport.equals(Transport.TUBE))
			card.out(BASIC_TUBE_FARE);

	}

	public static void charge(Transport transport, Journey journey, Card card) {

		if (transport.equals(Transport.TUBE)) {

			int count = countZones(journey);

			if ( isOneZones(count) && isZoneTwo(journey)) {
				
				card.in(BASIC_TUBE_FARE - ANY_ZONE_OUTSIDE_ZONE_ONE_FARE);
				
			}else if (haveZoneOne(journey) && isOneZones(count)) {

				card.in(BASIC_TUBE_FARE - ZONE_ONE_FARE);

			} else if (!haveZoneOne(journey) && isOneZones(count)) {

				card.in(BASIC_TUBE_FARE - ANY_ZONE_OUTSIDE_ZONE_ONE_FARE);

			} else if (haveZoneOne(journey) && isTwoZones(count)) {

				card.in(BASIC_TUBE_FARE - TWO_ZONES_INC_ZONE_ONE_FARE);

			} else if (!haveZoneOne(journey) && isTwoZones(count)) {

				card.in(BASIC_TUBE_FARE - TWO_ZONES_EXC_ZONE_ONE_FARE);

			} else if (isThreeZones(count)) {

				card.in(BASIC_TUBE_FARE - THREE_ZONES_FAIR);

			}

		} else if (transport.equals(Transport.BUS)) {
			card.in(0f);
		}

	}

	private static boolean isZoneTwo(Journey journey) {
		if (journey.getEndPoint().getZone().contains("2") && journey.getStartPoint().getZone().contains("2"))
			return true;
		return false;
	}

	private static int countZones(Journey journey) {
		String[] zonesStart = journey.getStartPoint().getZone().split(",");
		String[] zonesEnd = journey.getEndPoint().getZone().split(",");
		int x = 10;
		for (int i = 0; i < zonesStart.length; i++) {
			for (int j = 0; j < zonesEnd.length; j++) {
				int z = Integer.parseInt(zonesStart[i]);
				int y = Integer.parseInt(zonesEnd[j]);
				z = Math.abs(z - y);
				if (z < x)
					x = z;
			}
		}

		return Math.abs(x);
	}

	private static boolean isThreeZones(int count) {
		if (count == 2)
			return true;
		return false;
	}

	private static boolean isTwoZones(int count) {
		if (count == 1)
			return true;
		return false;
	}

	private static boolean isOneZones(int count) {
		if (count == 0)
			return true;
		return false;
	}

	private static boolean haveZoneOne(Journey journey) {
		if (journey.getEndPoint().getZone().contains("1") || journey.getStartPoint().getZone().contains("1"))
			return true;
		return false;
	}

}
