package com.example.microservices.customerservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.microservices.customerservice.entity.Customer;
import com.example.microservices.customerservice.entity.Employee;
import com.example.microservices.customerservice.entity.Product;
import com.example.microservices.customerservice.repository.CustomerRepository;

@SpringBootTest
class CustomerServiceImplTest {

	@Autowired
	private CustomerService customerService;
//	
//	@Autowired
//	CustomerRepository repository;
	
	@Test
	void test() {
		
		System.out.println("------------Stream API Tests-------------\n");
		List<Customer>  customers = customerService.getAllCustomers();
		
		System.out.println("Customer List :\n");
		customers.forEach(System.out::println);
		System.out.println("------------\n");
		List<String> nameList = customers.stream().filter(c->c.getAge()>44).map(c->c.getName().toLowerCase()).collect(Collectors.toList());
		
		nameList.forEach(System.out::println);
		
		
		System.out.println("Customer with age = 50 : ");
		 customers.stream().filter(c->c.getAge()==50).findFirst().ifPresentOrElse(System.out::println,()->{System.out.println("Data Not found"); });
		System.out.println("------------\n");
		
		
		customers.stream().map(c->c.getAge()).limit(5).forEach(System.out::println);
		
		List<Customer> custs = customers.stream().map(cust->{
			Customer cu = new Customer();
			cu.setId(cust.getId());
			cu.setName(cust.getName());
			cu.setAge(cust.getAge());
			cu.setGender(cust.getGender());
			cu.setEmail(cust.getEmail());
			return cu;
		}).collect(Collectors.toList());
		
		custs.forEach(System.out::println);
		
		List<Customer> custWithAgeLessThan30 = customers.stream().filter(c->c.getAge()<30).map(cust->new Customer()).collect(Collectors.toList());
		
		
		List<Integer> ageList = customers.stream().map(cust->cust.getAge()).collect(Collectors.toList());
		
		
		Integer totalSum = customers.stream().mapToInt(cust->cust.getAge()).sum();
		System.out.println("Total sum : " + totalSum);
		totalSum = customers.stream().filter(cust->cust.getAge()>44).map(cust->cust.getAge()).reduce(0, (age1,age2)->age1+age2);
		System.out.println("Total sum with map() and reduce : " + totalSum);
		Integer totalReduceSum = customers.stream().filter(cust->cust.getAge()>44).mapToInt(cust->cust.getAge()).reduce(0,(a,b)->a+b);
		System.out.println("Total sum with mapToInt() and reduce: " + totalReduceSum);
		

		Integer summingInt = customers.stream().map(cust->cust.getAge()).collect(Collectors.summingInt(age->age>44?age:0));
		System.out.println("Total sum with Collectors.summingInt : " + summingInt);
		
		IntStream.of(0,10).filter(i->i==5).forEach(System.out::println);
		System.out.println("Sum of int"+ IntStream.of(0,10).sum());
		OptionalInt totalReduceIntSum = customers.stream().mapToInt(cust->cust.getAge()).reduce(Integer::sum);
		System.out.println("Total Reduce Int sum : " + totalReduceIntSum.getAsInt());
		
		OptionalInt max = customers.stream().mapToInt(cust->cust.getAge()).reduce(Integer::max);
		
		Integer maxAge = customers.stream().mapToInt(cust->cust.getAge()).reduce(0,(a,b) -> a>b ? a:b);
		System.out.println("Max age using reduce"+maxAge);
		
		
		String maxLengthName = customers.stream().map(cust->cust.getName()).reduce((word1,word2)->word1.length()>word2.length()?word1:word2).get();
		System.out.println("Word with max length : "+maxLengthName);
		List<String> producstNameList = Arrays.asList("Product1","Product2","Product3");
		customers.stream().forEach(cust->cust.setProducts(producstNameList));
		List<String> productsName = customers.stream().flatMap(cust->cust.getProducts().stream().map(name->name)).distinct().collect(Collectors.toList());
		System.out.println("------------\n---Products : "+productsName);
		//customers.forEach(System.out::println);
		 String productsNames = customers.stream().flatMap(cust->cust.getProducts().stream()).distinct().reduce(" ", (name,name1)->name.concat(",").concat(name1));
		 
		 System.out.println("Peoduct Name with string : "+productsNames);
		
		Double averageAge = customers.stream().filter(cust->cust.getGender().equalsIgnoreCase("male")).mapToDouble(age->age.getAge()).average().getAsDouble();
		System.out.println("Average age : " + averageAge);
		
		customers.stream().filter(cust->cust.getGender().equalsIgnoreCase("male")).mapToDouble(cust->cust.getAge()).forEach(System.out::println);
		
		customers.stream().map(cust->cust.getAge()).collect(Collectors.toList()).forEach(System.out::println);
		System.out.println("------------\n---Products : ");
		
		System.out.println(customers.stream().filter(cust->cust.getGender().equalsIgnoreCase("male")).mapToDouble(cust->cust.getAge()).summaryStatistics());
		//customers.stream().filter(cust->cust.getAge()<50).collect(Collectors.toMap(Customer::getAge, cust));
		
		System.out.println("Sum : "+ customers.stream().map(cust->cust.getAge()).reduce(0,(a,b)->a+b));
		
		System.out.println("Double & Sum of even ages : "+ customers.stream().filter(cust->cust.getAge()%2==0).mapToInt(cust-> (cust.getAge()*2)).sum());
		
//		System.out.println("Double & Sum of even ages : "+ customers.stream().filter(cust->cust.getAge()%2==0).map(cust-> (cust.getAge()*2)).reduce(0, Integer::sum));
		System.out.println("Double & Sum of even ages : "+ customers.stream().filter(cust->cust.getAge()%2==0).map(cust-> (cust.getAge()*2)).reduce(0, (a,b)->a+b));
		
		System.out.println("All names : "+ customers.stream().map(cust->cust.getName()).reduce(" ",(a,b)-> a.concat(",").concat(b)));
		/*
		System.out.println("Product of 1-5 : "+ IntStream.of(1,5).reduce(1,(a,b) -> a*b));
		
		System.out.println("Map example 1" + customers.stream().collect(Collectors.groupingBy(Customer::getName)));
		
		System.out.println("Map example 2"+customers.stream().collect(Collectors.groupingBy(Customer::getName,Collectors.mapping(Customer::getAge,Collectors.toList()))));
		
		System.out.println("Name with findAny : "+ customers.stream().filter(cust->cust.getAge()>30).findAny().get().getId());
		
		System.out.println("Name with findFirst : "+ getCustomer(customers,(cust)->cust.getAge()>300));
		
		Predicate<Customer> agePredicate = (cust)->cust.getId()>40 ;
		
		System.out.println("Customer list with gender : "+ getFilteredCustomers(customers,(cust)->cust.getGender().equalsIgnoreCase("male")));
		
		Predicate<Customer> genderPredicate = (cust)-> cust.getGender().equalsIgnoreCase("male") ;
		System.out.println("Customer list with Id > 50 : "+ getFilteredCustomers(customers,agePredicate) );
		
		
		System.out.println("Customer list with Id> 40 and gender = Male : ");
		getFilteredCustomers(customers, agePredicate.and(genderPredicate).and((cust)->cust.getName().startsWith("vish"))).forEach(System.out::println);
		
		List<String> listStr = Arrays.asList("fruit : apple","veggie:potato","fruit : banana");
        Map<String,List<String>> strMap = listStr.stream().collect(Collectors.groupingBy(s->s.substring(0,s.indexOf(":")),Collectors.toList()));
        System.out.println(strMap);

		//getFilteredCustomers(customers, agePredicate.and((cust)->cust.getId()>40 ));
		System.out.println("All Customers ");
		customers.stream().distinct().forEach(System.out::println);
		
		Stream.of("one", "two", "three", "four")
        .filter(e -> e.length() > 3)
        .peek(e -> System.out.println("Filtered value: " + e))
        .map(String::toUpperCase)
        .peek(e -> System.out.println("Mapped value: " + e))
        .collect(Collectors.toList());
*/
		assertFalse(customers.isEmpty());
	}
	
	public int totalAge(List<Customer> customers, Predicate<Customer> selector) {
		 return customers.stream().filter(selector).map(cust->cust.getAge()).mapToInt(e->e).sum();
	}
	
	public List<Customer> getFilteredCustomers(List<Customer> customers, Predicate<Customer> selector){
		return customers.stream().filter(selector).collect(Collectors.toList());
	}
	
	public Map<Integer,List<Customer>> getCustomersMap(List<Customer> customers, Predicate<Customer> selector){
		return customers.stream().filter(selector).collect(Collectors.groupingBy(Customer::getAge));
	}
	public Customer getCustomer(List<Customer> customers, Predicate<Customer> selector) {
		 return customers.stream().filter(selector).findFirst().orElse(null);
	}
//	@Test
//	void intStreamTest() {
//		//IntStream.range(1, 10).forEach(System.out::println);
//		
//		//skip(5) will skip first 5 elements
//		IntStream.range(1, 10).skip(5).forEach(System.out::println);
//		System.out.println("------------\n");
//		
//		//Square for numbers
//		Arrays.stream(new int[] {2,4,6,8,10}).map(x->x*x).forEach(System.out::println);
//		System.out.println("------------\n");
//		//Square and then average of numbers
//				System.out.println("Square and then average of numbers : "+ Arrays.stream(new int[] {2,4,6,8,10}).map(x->x*x).average().getAsDouble());
//				
//		System.out.println("------------\n");
//		//sum of no in a range given
//		System.out.println("Sum of 1 to 9 : "+IntStream.range(1, 10).sum());
//		
//		System.out.println("------------\n");
//		//sum of no in a range given
//		System.out.println("Reduce and sum: "+IntStream.range(1, 5).reduce(0, (a,b)-> a+b));
//		
//		assertFalse(false);
//	}

//	@Test
//	void stringStreamTest() {
//		
//		System.out.println("------------\n");
//		//it will sort first then selects first name and then prints on console
//		Stream.of("Ava","Vish","Alex").sorted().findFirst().ifPresent(System.out::println);
//		
//		System.out.println("------------\n");
//		//It will filters first with name starts with V then sort and then prints all on console
//		String[] strArray = {"Ava","Vish","Alex","Minal","Viren","Mom"};
//		Arrays.stream(strArray).filter(x->x.startsWith("V")).sorted().forEach(System.out::println);
//		
//		System.out.println("------------\n");
//		//Lowers the case of string and prints to console
//		List<String> listStr = Arrays.asList(strArray);
//		listStr.stream().filter(x->x.startsWith("M")).map(s->s.toLowerCase()).forEach(System.out::println);
//		
//		System.out.println(listStr.stream().filter(x->x.startsWith("V")).map(s->s.toLowerCase()).findAny().get());
//		
//		System.out.println(listStr.stream().filter(x->x.startsWith("V")).count());
//		
//		assertFalse(false);
//	}

}
