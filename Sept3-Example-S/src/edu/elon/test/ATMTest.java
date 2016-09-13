package edu.elon.test;

import static org.junit.Assert.*;

import java.text.NumberFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ATMTest {
	
	private ATM atmDefConstruct;
	private ATM atmWBalance;

	@Before
	public void setUp() throws Exception {
		atmDefConstruct = new ATM();
		atmWBalance = new ATM(50.00);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeposit() {
		assertTrue(atmDefConstruct.getBalance() == 0);
		atmDefConstruct.deposit(20.00);
		assertTrue(atmDefConstruct.getBalance() == 20.00);
		
		try {
			atmDefConstruct.deposit(-20.00);
		} catch (IllegalArgumentException expected){
		}
		
		assertTrue(atmDefConstruct.getBalance() == 20.00);
		atmDefConstruct.deposit(10.00);
		assertTrue(atmDefConstruct.getBalance() == 30.00);
		
		assertTrue(atmWBalance.getBalance() == 50.00);
		atmWBalance.deposit(20.00);
		assertTrue(atmWBalance.getBalance() == 70.00);
		
		try {
			atmWBalance.deposit(-20.00);
		} catch (IllegalArgumentException expected){
		}
		
		assertTrue(atmWBalance.getBalance() == 70.00);
		atmWBalance.deposit(10.00);
		assertTrue(atmWBalance.getBalance() == 80.00);
	}

	@Test
	public void testGetBalance() {
		assertTrue(atmDefConstruct.getBalance() == 0.00);
		assertTrue(atmWBalance.getBalance() == 50.00);
		
		atmDefConstruct.deposit(10.00);
		atmWBalance.deposit(10.00);
		
		assertTrue(atmDefConstruct.getBalance() == 10.00);
		assertTrue(atmWBalance.getBalance() == 60.00);
		
		atmDefConstruct.withdraw(10.00);
		atmWBalance.withdraw(60.00);
		assertTrue(atmDefConstruct.getBalance() == 0.00);
		assertTrue(atmWBalance.getBalance() == 0.00);
	}

	@Test
	public void testToString() {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		String s = atmDefConstruct.toString();
		assertEquals("Amount balance is " + currencyFormat.format(0.00), s);
		
		String s2 = atmWBalance.toString();
		assertEquals("Amount balance is " + currencyFormat.format(50.00), s2);
	}

	@Test
	public void testWithdraw() {
		assertTrue(atmDefConstruct.getBalance() == 0.00);
		assertTrue(atmWBalance.getBalance() == 50.00);
		
		try {
			atmDefConstruct.withdraw(5.00);
		} catch (IllegalArgumentException expected) {
		}
		
		assertTrue(atmDefConstruct.getBalance() == 0.00);
		
		try {
			atmDefConstruct.withdraw(-5.00);
		} catch (IllegalArgumentException expected) {
		}
		
		assertTrue(atmDefConstruct.getBalance() == 0.00);
		
		try {
			atmWBalance.withdraw(51.00);
		} catch (IllegalArgumentException expected) {
		}

		assertTrue(atmWBalance.getBalance() == 50.00);
		
		try {
			atmWBalance.withdraw(-5.00);
		} catch (IllegalArgumentException expected) {
		}
		
		assertTrue(atmWBalance.getBalance() == 50.00);
		
		atmWBalance.withdraw(10.00);
		assertTrue(atmWBalance.getBalance() == 40.00);
		
		atmDefConstruct.deposit(10.00);
		atmDefConstruct.withdraw(5.00);
		assertTrue(atmDefConstruct.getBalance() == 5.00);
		
		
	}

}
