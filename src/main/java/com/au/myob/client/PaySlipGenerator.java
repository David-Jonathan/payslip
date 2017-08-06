package com.au.myob.client;

import com.au.myob.main.GeneratePaySlip;

public class PaySlipGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeneratePaySlip paySlipGen = GeneratePaySlip.getPaySlipGenerator();
		
		String employeesFileName = "C:/Users/Immanuel/Documents/Silas_Files/Code_Test/input_employees.csv";
		String rulesFileName = "C:/Users/Immanuel/Documents/Silas_Files/Code_Test/taxcalculation.csv";
		
		paySlipGen.readInputFile(employeesFileName, rulesFileName);
		
		System.out.println(paySlipGen.getEmployees().size());
		
		paySlipGen.computeTax(paySlipGen.getEmployees());

	}

}
