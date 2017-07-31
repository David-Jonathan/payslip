package com.au.myob.util;

public class PayCalculation {
	
	//pay period = per calendar month
	//gross income = annual salary / 12 months
	//income tax = based on the tax table provide below
	//net income = gross income - income tax
	//super = gross income x super rate

	public double getGrossIncome(double annualSalary) {
		return annualSalary/12;
	}
	
	public int getIncomeTax() {
		return 0;
	}
	
	public double getNetIncome(double annualSalary) {
		return getGrossIncome(annualSalary) - getIncomeTax();
	}
	
	public double getSuperAmount(double annualSalary, int superRate) {
		return getGrossIncome(annualSalary) * superRate;
	}
	
}
