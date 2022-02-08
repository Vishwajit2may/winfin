package com.example.microservices.customerservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class StreamTest {

	private static String PREFIX = "{";
	private static String SUFFIX = "}";
	
	
	void test() {
		//fail("Not yet implemented");
		IntStream streamOfChars = "!@#".chars();
		streamOfChars.forEach(System.out::println);
		
		Stream<String> generatedStream = Stream.generate(() -> "pqr").limit(5);
		generatedStream.forEach(System.out::println);
		
		Stream<String> streamBuilder = Stream.<String>builder().add("p").add("q").add("r").build();
		streamBuilder.forEach(System.out::println);
		
//		Stream<Integer> streamIterated = Stream.iterate(0, n -> n  + 1).filter(n->n%2==0).limit(20);
//		streamIterated.forEach(System.out::println);
		
		IntStream.range(0, 40).filter(n->n%2!=0).forEach(System.out::println);
		
		

		StringJoiner joiner = new StringJoiner(", ", PREFIX, SUFFIX);
		
		   joiner.add("Core Java")
		         .add("Spring Boot")
		         .add("Angular");
		System.out.println(joiner.toString());
		
		Supplier<String> otps = () -> {
		     String otp = "";
		     for (int i = 1; i <= 4; i++) {
		        otp = otp + (int) (Math.random() * 10);
		     }
		   return otp;
		};
		System.out.println(otps.get());
		
		
		
		
	}
	
	@Test
	public void streamTest() {
//		Stream<String> streamOfStrings =  Stream.of("Sunday", "Monday", "Wednesday", "Friday");
//		
//		List<Integer> ints = IntStream.rangeClosed(1, 6).mapToObj(Integer::valueOf)
//                .collect(Collectors.toList()); 
//		IntStream.rangeClosed(1, 6).boxed()
//        .collect(Collectors.toList()); 
		
		IntStream.rangeClosed(1, 26).peek(n -> System.out.println("original element : " +n))         //prints value before multiplying by 3
	     .map(n -> n * 3)
	     .peek(n -> System.out.println("Tripled element : " +n))          //prints value before filtering
	     .filter(n -> n % 2 == 0)
	     .peek(n -> System.out.println("Divisible By 2 element : " +n))   //prints value after filtering but before summing
	     .sum();
	}

}
