package com.au.myob.payslip.main;

import java.util.List;

import com.au.myob.payslip.vo.Employee;
import com.au.myob.payslip.vo.Payslip;

public interface IGeneratePaySlip {
	
    public List<Employee> readInputFile(String employeeFileName);
    
    public List<Payslip> computeTaxAndCreatePaySlip(List<Employee> employees, String _rulesFileName);
    
    public void printPaySlips(List<Payslip> payslips, String outputFileName);

}
