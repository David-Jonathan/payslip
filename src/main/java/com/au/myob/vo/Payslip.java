package com.au.myob.vo;

public class Payslip {
	
	String fullName;
	String payPeriod;
	double grossIncome;
	int incomeTax;
	double netIncome;
	int sprAnnuation;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPayPeriod() {
		return payPeriod;
	}
	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}
	public double getGrossIncome() {
		return grossIncome;
	}
	public void setGrossIncome(double grossIncome) {
		this.grossIncome = grossIncome;
	}
	public int getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(int incomeTax) {
		this.incomeTax = incomeTax;
	}
	public double getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}
	public int getSprAnnuation() {
		return sprAnnuation;
	}
	public void setSprAnnuation(int sprAnnuation) {
		this.sprAnnuation = sprAnnuation;
	}
	
	

}
