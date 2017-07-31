package com.au.myob.main;

import java.util.List;

import org.junit.Assert;

import com.au.myob.vo.Employee;
import com.au.myob.vo.Payslip;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Unit test for simple PaySlipGenerator.
 */
public class PaySlipGeneratorTest 
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public void testCreateEmployeeObject_validInputCSVFile( String fileName )
    {
    	PaySlipGenerator paySlipGenerator = PaySlipGenerator.getPaySlipGenerator();
        paySlipGenerator.readInputFile(fileName);
        
        List<Employee> employees = PaySlipGenerator.getPaySlipGenerator().getEmployees();
        
        Assert.assertNotNull(employees);
    }


    public void testTaxComputationRules() {
    	PaySlipGenerator paySlipGenerator = PaySlipGenerator.getPaySlipGenerator();
    	paySlipGenerator.loadTaxComputationRules();
    	
    	List<Employee> employees = paySlipGenerator.getEmployees();
    	
    	Assert.assertNotNull(employees);
    }
    
    public void testComputeTax_validInputEmployeeObjects(List<Employee> employees) {
    	PaySlipGenerator paySlipGenerator = PaySlipGenerator.getPaySlipGenerator();
    	paySlipGenerator.computeTax(employees);
    	
    	List<Payslip> payslips = paySlipGenerator.getPayslips();
    	
    	Assert.assertNotNull(payslips);
    }
    
    public void testPrintPaySlips(List<Payslip> payslips) {
    	PaySlipGenerator paySlipGenerator = PaySlipGenerator.getPaySlipGenerator();
    	paySlipGenerator.printPaySlips(payslips);
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PaySlipGeneratorTest.class );
    }

}