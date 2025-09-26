// Case2_StudentGrades.java
import java.util.*;

class Student {
    private String id;
    private String name;
    private double[] marks; // size 3

    public Student(String id, String name, double m1, double m2, double m3) {
        this.id = id;
        this.name = name;
        this.marks = new double[]{m1, m2, m3};
    }

    public double average() {
        double sum = 0;
        for (double m : marks) sum += m;
        return sum / marks.length;
    }

    public String grade() {
        double avg = average();
        if (avg >= 80) return "A";
        if (avg >= 60) return "B";
        if (avg >= 40) return "C";
        return "Fail";
    }

    @Override
    public String toString() {
        return id + " - " + name + " | Avg: " + String.format("%.2f", average()) + " | Grade: " + grade();
    }

    // Demo
    public static void main(String[] args) {
        Student s1 = new Student("S001", "Alice", 85, 78, 90);
        Student s2 = new Student("S002", "Bob", 60, 65, 62);
        Student s3 = new Student("S003", "Charlie", 35, 40, 38);

        Student[] students = new Student[]{s1, s2, s3};
        double highestAvg = Double.NEGATIVE_INFINITY;
        Student best = null;

        for (Student s : students) {
            System.out.println(s);
            if (s.average() > highestAvg) {
                highestAvg = s.average();
                best = s;
            }
        }

        System.out.println();
        System.out.printf("Highest average: %.2f (Student: %s)%n", highestAvg, best != null ? best.toString() : "none");
    }
}
