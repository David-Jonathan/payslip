package com.au.myob.payslip.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.au.myob.payslip.main.GeneratePaySlip;
import com.au.myob.payslip.vo.Employee;
import com.au.myob.payslip.vo.Payslip;

public class PaySlipGenerator {

	final static Logger logger = Logger.getLogger(PaySlipGenerator.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeneratePaySlip paySlipGen = new GeneratePaySlip();
		
		String employeesFileName = "input_employees.csv";
		String rulesFileName = "taxcalculation.csv";
		String outputFileName = "payslip_output.csv";
		
		List<Employee> employees = paySlipGen.readInputFile(employeesFileName);
		
		List<Payslip> payslips = paySlipGen.computeTaxAndCreatePaySlip(employees, rulesFileName);
		
		paySlipGen.printPaySlips(payslips, outputFileName);
		
		Properties props = new Properties();
		try(InputStream resourceStream 
				= Thread.currentThread().getContextClassLoader().getResourceAsStream("payslip.properties")) {
		    props.load(resourceStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info(props.size());

	}

}
