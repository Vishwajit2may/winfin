package com.example.microservices.customerservice.entity;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class EmployeeTest {

	@Test
	void java8FunstionalProgramingTest() {
		List<Employee> employeeList = getListOfEmployee();
		
		
		System.out.println("How many male and female employees are there in the organization?");
		Map<String, Long> countingMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
		System.out.println(countingMap);
		
		System.out.println("\nPrint the name of all departments in the organization?");
		employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
		
		Employee.sum();
		System.out.println("\nPrint department and employee list belongs to that department in the organization?");
		Map<String,List<Employee>> empDepartmentMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.toList()));
		empDepartmentMap.forEach((k,v)->System.out.println(k+" "+v));
		
		System.out.println("\nWhat is the average age of male and female employees?");
		Map<String,Double> averageAgeOfEmpByGender = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge)));
		averageAgeOfEmpByGender.forEach((k,v)->System.out.println(k+" "+v));
		
		System.out.println("\nGet the details of highest paid employee in the organization?");
		Employee highestPaidEmployee = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))).orElseGet(null);
		Employee highestPaidEmployee1 = employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElseGet(null);
		
		System.out.println(highestPaidEmployee);
		System.out.println(highestPaidEmployee1);
		
		System.out.println("\nGet the names of all employees who have joined after 2015?");
		employeeList.stream().filter(e->e.getYearOfJoining()>2015).map(e->e.getName()).collect(Collectors.toList()).forEach(System.out::println);
		
		System.out.println("\nCount the number of employees in each department?");
		System.out.println("Department name" + " - "+"Number of employees working");
		employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting())).forEach((key,value)->System.out.println(key + " - "+value));
		
		System.out.println("\nWhat is the average salary of each department?");
		System.out.println("Department name" + " - "+"Average salary");
		employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))).forEach((key,value)->System.out.println(key + " - "+value));
		
		System.out.println("\nGet the details of youngest male employee in the product development department?");
		Employee youngestEmployee = employeeList.stream().filter(emp->emp.getGender().equals("Male") && emp.getDepartment().equals("Product Development")).min(Comparator.comparing(Employee::getAge)).orElseGet(null);
		System.out.println(youngestEmployee);
		
		System.out.println("\nWho has the most working experience in the organization?");
		Employee mostExperienced = employeeList.stream().sorted(Comparator.comparing(Employee::getYearOfJoining)).findFirst().orElseGet(null);
		System.out.println(mostExperienced);
	
		System.out.println("\nHow many male and female employees are there in the sales and marketing team?");
		employeeList.stream().filter(emp->emp.getDepartment().equals("Sales And Marketing")).collect(Collectors.groupingBy(Employee::getGender,Collectors.counting())).forEach((key,value)->System.out.println(key + " " +value));
		
		
		System.out.println("\nWhat is the average salary of male and female employees?");
		employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary))).forEach((key,value)->System.out.println(key + " " +value));
		
		employeeListByDepartment(employeeList);
		
		System.out.println("\nWhat is the average salary and total salary of the whole organization?");
		DoubleSummaryStatistics summarizingSalary = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(summarizingSalary);
		
		System.out.println("Average salsry : "+summarizingSalary.getAverage());
		System.out.println("Total salsry : "+summarizingSalary.getSum());
		
		System.out.println("\nSeparate the employees who are younger or equal to 25 years from those employees who are older than 25 years.");
		
		Map<Boolean,List<Employee>> empWithAgeGreaterThan25 = employeeList.stream().collect(Collectors.partitioningBy(e -> e.getAge()>25));
		  for(Entry<Boolean,List<Employee>> entry : empWithAgeGreaterThan25.entrySet()) {
			  
			  if(entry.getKey()) {
				  System.out.println("\nEmployee more than 25 years age");
			  } else {
				  System.out.println("\nEmployee less than 25 years age");
			  }
			  
			  entry.getValue().forEach(emp-> System.out.println(emp.getName()));
			  
		  }
		  
		  System.out.println("\nWho is the oldest employee in the organization? What is his age and which department he belongs to?");
			Employee employee = employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).orElseGet(null);
			
			System.out.println("Oldest Employee : "+employee.getName());
			System.out.println("Age : "+employee.getAge());
			System.out.println("Department : "+employee.getDepartment());
			  
		
		
	}


	private void employeeListByDepartment(List<Employee> employeeList) {
		System.out.println("\nList down the names of all employees in each department?");
		Map<String, List<Employee>> employeeListByDepartment = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.toList()));
		
		for(Entry<String, List<Employee>> entry : employeeListByDepartment.entrySet()) {
			System.out.println("--------------------------------------");
            
		    System.out.println("Employees In "+entry.getKey() + " : ");
		             
		    System.out.println("--------------------------------------");
		             
		    List<Employee> list = entry.getValue();
		             
		    for (Employee e : list) 
		    {
		        System.out.println(e.getName());
		    }
			
		}
	}

	
	public List<Employee> getListOfEmployee(){
		List<Employee> employeeList = new ArrayList<Employee>();
        
		employeeList.add(new Employee(111, "Sara Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
		
		return employeeList;
	}
}
