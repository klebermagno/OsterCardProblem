package com.kleber.app;

import entity.Card;
import entity.Journey;
import enuns.Transport;
import enuns.StationZone;

/**
 *Kleber Magno Maciel Vieira
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       Card card = new Card();
       System.out.println("Loading a card with £30");
       card.addMoney(30);
       
       Journey jorneyHolbornToCourt = new Journey();
       jorneyHolbornToCourt.setStartPoint(Transport.TUBE,StationZone.HOLBORN,card);
       jorneyHolbornToCourt.setEndPoint(StationZone.EARLS_COURT);
       

       
       System.out.printf("Card Balance after first journey (Tube Holborn to Earl’s Court)  is : %s \n",card.getBalance());
       
       Journey jorneyBusEarlToChelsea = new Journey();
       jorneyBusEarlToChelsea.setStartPoint(Transport.BUS,null,card);
       jorneyBusEarlToChelsea.setEndPoint(null);
       
       System.out.printf("Card Balance after second journey (328 bus from Earl’s Court to Chelsea) is : %s \n",card.getBalance());
       
       Journey jorneyEarlsToHammersmith = new Journey();
       jorneyEarlsToHammersmith.setStartPoint(Transport.TUBE, StationZone.EARLS_COURT, card);
       jorneyEarlsToHammersmith.setEndPoint(StationZone.HAMMERSMITH);
       
       System.out.printf("Card Balance after third journey (Tube Earl’s court to Hammersmith)  is : %s \n",card.getBalance());
    }
}
