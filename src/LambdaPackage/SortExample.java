package LambdaPackage;

import java.util.*;
import java.util.stream.Collectors;

@FunctionalInterface
interface StringFilter {
    boolean test(String s);
}

public class SortExample {
	public static void main(String[] args) {
		List<String> name=Arrays.asList("Srinivasan","Arvin","Rajkumar");
		Collections.sort(name,(a,b) ->a.compareTo(b));
		
		name.forEach(System.out::println);
		
		System.out.println();
		
		List<Integer> numbers=Arrays.asList(11,22,33,44,55,66,77,88,99);
		numbers.stream()
			.filter(n -> n%2==1)
			.forEach(System.out::println);
		
		 StringFilter startsWithA = s -> s.startsWith("A");

	        System.out.println(startsWithA.test("Apple"));   // true
	        System.out.println(startsWithA.test("Banana"));  // false
		 
	        List<String> upperCaseNames=name.stream()
	        		.map(String::toUpperCase)
	        		.collect(Collectors.toList());
	        upperCaseNames.forEach(System.out::println);
	}

}
