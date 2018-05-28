package com.kleber.app;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.Card;
import exception.FareException;

public class CardTest {

	@Test(expected = FareException.class) 
	public void testValidadeExeption() {
		Card card = new Card(30f);
		card.validade(31);
	}
	
	@Test(expected = FareException.class) 
	public void testOutException() {
		Card card = new Card(30f);
		card.out(31);
	}


}
