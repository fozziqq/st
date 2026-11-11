package Classs;

import java.util.*;
import java.util.stream.Collectors;

public class StudentList {
    public static void main(String[] args) {

        Set<Student> students = new LinkedHashSet<>();

        Student s1 = new Student(1, "Alex", 19, 90);
        Student s2 = new Student(2, "Bob", 20, 75);
        Student s3 = new Student(3, "Mike", 21, 85);
        Student s4 = new Student(4, "David", 18, 95);
        Student s5 = new Student(5, "John", 22, 80);
        Student s6 = new Student(6, "Steve", 20, 88);
        Student s7 = new Student(7, "Merser", 21, 90);
        Student s8 = new Student(8, "Jack", 22, 90);
        Student s10 = new Student(9, "Mas", 21, 95);

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        students.add(s8);
        students.add(s10);

        int count = 0;

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == 1) {
                iterator.remove();
            }
        }

        Student s11 = new Student(11, "Ivan", 20, 80);
        students.add(s11);
        System.out.println("До изменения ID: " + students.contains(s7));

        s7.setId(23);
        System.out.println("После изменения ID: " + students.contains(s7));

        System.out.println("Размер коллекции: " + students.size());

        for (Student findStudentById : students) {
            if (findStudentById.getId() == 9) {
                System.out.println(findStudentById);
            }
        }

        Map<Integer, List<Student>> studentsByGrade = students.stream()
                .collect(Collectors.groupingBy(s -> s.grade));

        int maxGrade = studentsByGrade.keySet().stream().max(Integer::compare).orElse(0);
        List<Student> bestStudents = studentsByGrade.get(maxGrade);
        bestStudents.forEach(s -> System.out.println(s + " - Лучшие студенты!"));

        Student bestStudent = students.stream()
                .max(Comparator.comparingInt(s -> s.grade))
                .orElse(null);
        System.out.println("Лучший студент " + bestStudent);

        for (Student student : students) {
            if (student.age >= 18) {
                count++;
            }
        }
        System.out.println("Все студенты которым больше 18 лет:" + count);

        System.out.println("\n--- Все студенты ---");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("\n--- Группировка по возрасту ---");
        Map<Integer, List<Student>> byAge = groupStudentsByAge(students);
        byAge.forEach((age, list) -> {
            System.out.println("Возраст " + age + ": " + list.size() + " чел.");
            list.forEach(s -> System.out.println("   " + s));
        });
    }

    public static Map<Integer, List<Student>> groupStudentsByAge(Set<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(
                        s -> s.age,
                        TreeMap::new,
                        Collectors.toList()
                ));
    }
}
