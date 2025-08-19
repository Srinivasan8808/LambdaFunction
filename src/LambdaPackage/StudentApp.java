package LambdaPackage;

import java.util.*;
import java.util.stream.*;

public class StudentApp {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student(1, "Alice", 20, 85.5),
            new Student(2, "Bob", 22, 72.0),
            new Student(3, "Charlie", 19, 90.0),
            new Student(4, "David", 21, 65.0),
            new Student(5, "Eva", 20, 95.0)
        );

        // ✅ Lambda with Functional Interface
        StudentFilter highGradeFilter = s -> s.getGrade() >= 80;

        System.out.println("Students with grade >= 80:");
        students.stream()
                .filter(highGradeFilter::test)   // ✅ Method reference
                .forEach(System.out::println);   // ✅ Method reference

        // ✅ Streams + Lambda: Filter by age
        System.out.println("\nStudents younger than 21:");
        students.stream()
                .filter(s -> s.getAge() < 21)
                .forEach(System.out::println);

        // ✅ Streams + Method Reference: Get all names
        System.out.println("\nStudent Names:");
        students.stream()
                .map(Student::getName)    // Method reference to getter
                .forEach(System.out::println);

        // ✅ Streams + Reduce (average grade)
        double avgGrade = students.stream()
                .mapToDouble(Student::getGrade)
                .average()
                .orElse(0);
        System.out.println("\nAverage Grade: " + avgGrade);
    }
}
