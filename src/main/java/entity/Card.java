package entity;

import exception.FareException;

public class Card {
	
	private float balance;

	public Card(float balance){
		this.balance = balance;
	}
	public Card(){
		this.balance = 0;
	}
	
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public void addMoney(float money){
		this.balance = balance+money;
	}
	
	public void out(float fare) {
		validade(fare);
		this.balance = this.balance - fare;
		
	}
	
	public void validade(float fare) throws FareException{
		if(balance < fare)
			throw  new FareException("Don't have balnce"); 
	}
	public void in(float f) {
		this.balance = this.balance + f;
		
	}

	
}
