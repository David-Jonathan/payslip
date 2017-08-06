package com.au.myob.main;

import java.util.List;

import com.au.myob.vo.Employee;
import com.au.myob.vo.Payslip;

public interface IGeneratePaySlip {
	
    public void readInputFile(String employeeFileName, String rulesFileName);
    
    public void loadTaxComputationRules();
    
    public void computeTax(List<Employee> employees);
    
    public void printPaySlips(List<Payslip> payslips);

}
