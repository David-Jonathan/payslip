package com.au.myob.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.au.myob.vo.Employee;
import com.au.myob.vo.Payslip;
import com.au.myob.vo.Rule;

/**
 * Hello world!
 *
 */
public class GeneratePaySlip implements IGeneratePaySlip
{
	private List<Employee> employees;
	private List<Rule> rules;
	private String rulesFileName;
	private List<Payslip> payslips;
	
	private static GeneratePaySlip paySlipGenerator = new GeneratePaySlip();
	
	private GeneratePaySlip(){}

	public static GeneratePaySlip getPaySlipGenerator(){
	      return paySlipGenerator;
	}
	
    public void readInputFile(String employeeFileName, String _rulesFileName) {
		try (Stream<String> lines = Files.lines(Paths.get(employeeFileName))) {
			
			Employee employee;
			employees = new ArrayList<Employee>();
			List<List<String>> values = lines.map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
			values.remove(0);
			
			for(List<String> list : values) {
				employee = new Employee();
				employee.setFirstName(list.get(0));
				employee.setLastName(list.get(1));
				employee.setAnnaulSalary(Double.parseDouble(list.get(2)));
				employee.setSuperRate(list.get(3));
				employee.setStateDate(list.get(4));
				employees.add(employee);
			}
			
			rulesFileName = _rulesFileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void loadTaxComputationRules() {
    	
    }
    
    public void computeTax() {
    	
		try (Stream<String> lines = Files.lines(Paths.get(rulesFileName))) {
			
			Rule rule;
			rules = new ArrayList<Rule>();
			List<List<String>> values = lines.map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
			values.remove(0);
			
			for(List<String> list : values) {
				rule = new Rule();
				rule.setMinimum_salary(Integer.parseInt(list.get(0)));
				rule.setMaximum_salary(Integer.parseInt(list.get(1)));
				rule.setFixed_tax(Integer.parseInt(list.get(2)));
				rule.setVariable_tax(Float.parseFloat(list.get(3)));
				rules.add(rule);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		for(Employee employee : employees) {
		   Predicate<Rule> predicate = c-> c.getMaximum_salary() < employee.getAnnaulSalary();
		   Rule obj = rules.stream().filter(predicate).findFirst().get();
		}
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

