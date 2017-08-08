package com.au.myob.main;

import java.util.List;

import com.au.myob.vo.Employee;
import com.au.myob.vo.Payslip;

public interface IGeneratePaySlip {
	
    public List<Employee> readInputFile(String employeeFileName);
    
    public List<Payslip> computeTaxAndCreatePaySlip(List<Employee> employees, String _rulesFileName);
    
    public void printPaySlips(List<Payslip> payslips, String outputFileName);

}
