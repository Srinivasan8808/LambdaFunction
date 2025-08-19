package LambdaPackage;

@FunctionalInterface
interface StudentFilter {
    boolean test(Student s);
}