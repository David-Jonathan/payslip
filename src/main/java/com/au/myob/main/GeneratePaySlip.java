package com.au.myob.main;

import java.io.FileWriter;
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
	private List<Payslip> payslips;
	
		
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
     
    private static final String FILE_HEADER = "name,pay period,gross income,income tax,net income,super";

    public List<Employee> readInputFile(String employeeFileName) {
		try (Stream<String> lines = Files.lines(Paths.get(employeeFileName))) {
			
			Employee employee;
			employees = new ArrayList<Employee>();
			List<List<String>> values = lines.map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
			values.remove(0);
			
			for(List<String> list : values) {
				employee = new Employee();
				employee.setFirstName(list.get(0));
				employee.setLastName(list.get(1));
				employee.setAnnaulSalary(Integer.parseInt(list.get(2)));
				employee.setSuperRate(list.get(3));
				employee.setStateDate(list.get(4));
				employees.add(employee);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return employees;
    }
    
    public List<Payslip> computeTaxAndCreatePaySlip(List<Employee> employees, String _rulesFileName) {
    	
    	Rule rule;
    	Payslip payslip;
    	payslips = new ArrayList<Payslip>();
		rules = new ArrayList<Rule>();
		try (Stream<String> rulesLines = Files.lines(Paths.get(_rulesFileName))) {
			
			List<List<String>> rulesList = rulesLines.map(ruleLine -> Arrays.asList(ruleLine.split(","))).collect(Collectors.toList());
			rulesList.remove(0);
			
			for(List<String> list : rulesList) {
				rule = new Rule();
				rule.setMinimum_salary(Integer.parseInt(list.get(0)));
				rule.setMaximum_salary(Integer.parseInt((list.get(1) != null && list.get(1).equals(""))?new Integer(Integer.MAX_VALUE).toString():list.get(1)));
				rule.setFixed_tax(Integer.parseInt(list.get(2)));
				rule.setVariable_tax(Float.parseFloat(list.get(3)));
				rules.add(rule);
			}

			for(Employee employee : employees) {
				
				   Predicate<Rule> rulePredicate = predicateCandidate -> (employee.getAnnaulSalary() >= predicateCandidate.getMinimum_salary() 
						   && employee.getAnnaulSalary() < predicateCandidate.getMaximum_salary());
				   Rule matchingRule = rules.stream().filter(rulePredicate).findFirst().get();
				   
				   payslip = new Payslip();
				   int incomeTax = 0;
				   payslip.setFullName(employee.getFirstName() + " " + employee.getLastName());
				   payslip.setPayPeriod(employee.getStateDate());
				   payslip.setGrossIncome(employee.getAnnaulSalary() / 12);
				   
				   incomeTax = (int) ((matchingRule.getFixed_tax() + (employee.getAnnaulSalary() - matchingRule.getMinimum_salary()) * 
						   (matchingRule.getVariable_tax() / 100)) / 12);
				   payslip.setIncomeTax(incomeTax);
				   payslip.setNetIncome(payslip.getGrossIncome() - incomeTax);
				   payslip.setSprAnnuation((int) (payslip.getGrossIncome() * (Float.parseFloat(employee.getSuperRate())/100)));
				   payslips.add(payslip);
				   
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return payslips;
		
    }
    
    public void printPaySlips(List<Payslip> payslips, String outputFileName) {
    	
    	FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(outputFileName);
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
            
        	for(Payslip paySlip : payslips) {
        		fileWriter.append(paySlip.getFullName());
                fileWriter.append(COMMA_DELIMITER);
        		fileWriter.append(paySlip.getPayPeriod());
                fileWriter.append(COMMA_DELIMITER);
        		fileWriter.append(new Integer(paySlip.getGrossIncome()).toString());
                fileWriter.append(COMMA_DELIMITER);
        		fileWriter.append(new Integer(paySlip.getIncomeTax()).toString());
                fileWriter.append(COMMA_DELIMITER);
        		fileWriter.append(new Double(paySlip.getNetIncome()).toString());
                fileWriter.append(COMMA_DELIMITER);
        		fileWriter.append(new Integer(paySlip.getSprAnnuation()).toString());
                fileWriter.append(NEW_LINE_SEPARATOR);
        	}

            System.out.println("CSV file was created successfully !!!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }
    
}

