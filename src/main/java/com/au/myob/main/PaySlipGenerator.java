package com.au.myob.main;

import java.util.List;

import com.au.myob.vo.Employee;
import com.au.myob.vo.Payslip;

/**
 * Hello world!
 *
 */
public class PaySlipGenerator implements IPaySlipGenerator
{
	private List<Employee> employees;
	private List<Payslip> payslips;
	
	private static PaySlipGenerator paySlipGenerator = new PaySlipGenerator();
	
	private PaySlipGenerator(){}

	public static PaySlipGenerator getPaySlipGenerator(){
	      return paySlipGenerator;
	}
	
    public void readInputFile(String fileName) {
    	
    }
    
    public void loadTaxComputationRules() {
    	
    }
    
    public void computeTax(List<Employee> employees) {
    	
    }
    
    public void printPaySlips(List<Payslip> payslips) {
    	
    }
    
    public List<Employee> getEmployees() {
    	return this.employees;
    }
    
    public List<Payslip> getPayslips() {
    	return this.payslips;
    }
    
}
