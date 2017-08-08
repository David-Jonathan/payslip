package com.au.myob.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.au.myob.client.PaySlipGenerator;
import com.au.myob.vo.Employee;
import com.au.myob.vo.Payslip;

/**
 * Unit test for simple GeneratePaySlip.
 */
public class PaySlipGeneratorTest 
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	@Test
    public void testCreateEmployeeObject_validInputCSVFile()
    {
		String fileName = "input_employees.csv";
    	GeneratePaySlip generatePaySlip = new GeneratePaySlip();
    	List<Employee> employees = generatePaySlip.readInputFile(fileName);
        
    	Assert.assertNotNull(employees);
    }

	@Test
    public void testComputeTax_validInputEmployeeObjects() {
		String fileName = "input_employees.csv";
    	GeneratePaySlip generatePaySlip = new GeneratePaySlip();
    	List<Employee> employees = generatePaySlip.readInputFile(fileName);

    	String rulesFile = "taxcalculation.csv";
    	List<Payslip> payslips = generatePaySlip.computeTaxAndCreatePaySlip(employees, rulesFile);
    	
    	Assert.assertNotNull(payslips);
    }
    
	@Test
    public void testPrintPaySlips() {
    	GeneratePaySlip generatePaySlip = new GeneratePaySlip();
    	List<Payslip> payslips = new ArrayList<Payslip>();
    	String outputFileName = "payslip_output.csv";

    	generatePaySlip.printPaySlips(payslips, outputFileName);
    	
    	Assert.assertNotNull(new File(outputFileName));
    }
    
	@Test
    public void testPrintPaySlips_with_paySlipObject_asNull() {
    	GeneratePaySlip generatePaySlip = new GeneratePaySlip();
    	String outputFileName = "payslip_output.csv";
    	
    	generatePaySlip.printPaySlips(null, outputFileName);
    	
    	Assert.assertNotNull(new File(outputFileName));
    }

	@Test
    public void testPrintPaySlips_with_paySlipObject_and_outputFile_asNull() {
    	GeneratePaySlip generatePaySlip = new GeneratePaySlip();
    	String outputFileName = "_payslip_output.csv";
    	
    	generatePaySlip.printPaySlips(null, outputFileName);
    	
    }
	@Test
    public void testPaySlipGenerator_client() {
    	PaySlipGenerator paySlipGenerator = new PaySlipGenerator();
    	
    	paySlipGenerator.main(null);
    }
    
 }