package com.au.myob.client;

import java.util.List;

import com.au.myob.main.GeneratePaySlip;
import com.au.myob.vo.Employee;
import com.au.myob.vo.Payslip;

public class PaySlipGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeneratePaySlip paySlipGen = new GeneratePaySlip();
		
		String employeesFileName = "input_employees.csv";
		String rulesFileName = "taxcalculation.csv";
		String outputFileName = "payslip_output.csv";
		
		List<Employee> employees = paySlipGen.readInputFile(employeesFileName);
		
		List<Payslip> payslips = paySlipGen.computeTaxAndCreatePaySlip(employees, rulesFileName);
		
		paySlipGen.printPaySlips(payslips, outputFileName);

	}

}
