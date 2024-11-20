package com.example.stumana.Array;

import com.example.stumana.InvalidStudentDataException;
import com.example.stumana.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyArray {
    private Student[] students;
    private int size;
    private final int INITIAL_CAPACITY = 10;
    private final AtomicInteger count = new AtomicInteger(0);

    public MyArray() {
        students = new Student[INITIAL_CAPACITY];
        size = 0;
    }

    private void validateStudent(Student student) throws InvalidStudentDataException {
        if (!student.getStudentName().matches("[a-zA-Z ]+")) {
            throw new InvalidStudentDataException("Name must contain only letters and spaces.");
        }
        if (student.getScore() < 0 || student.getScore() > 10) {
            throw new InvalidStudentDataException("Score must be between 0 and 10.");
        }
    }

    private void validateStudentData(String studentName, float score) throws InvalidStudentDataException {
        if (!studentName.matches("[a-zA-Z ]+")) {
            throw new InvalidStudentDataException("Name must contain only letters and spaces.");
        }
        if (score < 0 || score > 10) {
            throw new InvalidStudentDataException("Score must be between 0 and 10.");
        }
    }

    public void add(Student s) throws InvalidStudentDataException {
        validateStudent(s);

        if (size == students.length) {
            students = Arrays.copyOf(students, students.length * 2);
        }

        s.setId(count.incrementAndGet()); // Set unique ID to the student
        students[size++] = s;
    }

    public void traverse() {
        try {
            for (int i = 0; i < size; i++) {
                System.out.println(students[i]);
            }
        } catch (Exception e) {
            System.err.println("Error while traversing students: " + e.getMessage());
        }
    }

    public void updateStudent(int id, String newName, float newScore) throws InvalidStudentDataException {
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == id) {
                validateStudentData(newName, newScore);
                students[i].setStudentName(newName);
                students[i].setScore(newScore);
                System.out.println("Student updated successfully!");
                return;
            }
        }
        throw new InvalidStudentDataException("Student with id " + id + " not found.");
    }

    public void deleteById(int id) {
        try {
            for (int i = 0; i < size; i++) {
                if (students[i].getId() == id) {
                    for (int j = i; j < size - 1; j++) {
                        students[j] = students[j + 1];
                    }
                    students[--size] = null;
                    System.out.println("Student deleted successfully!");
                    return;
                }
            }
            System.out.println("Student with id " + id + " not found.");
        } catch (Exception e) {
            System.err.println("Error while deleting student: " + e.getMessage());
        }
    }

    public void sortByNameLength() {
        try {
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (students[i].getStudentName().length() > students[j].getStudentName().length()) {
                        Student temp = students[i];
                        students[i] = students[j];
                        students[j] = temp;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error while sorting students by name length: " + e.getMessage());
        }
    }

    public Iterable<Student> getAllStudents() {
        try {
            return Arrays.asList(Arrays.copyOf(students, size));
        } catch (Exception e) {
            System.err.println("Error while getting all students: " + e.getMessage());
            return null;
        }
    }

    public Student findById(int id) {
        try {
            for (int i = 0; i < size; i++) {
                if (students[i].getId() == id) {
                    return students[i];
                }
            }
        } catch (Exception e) {
            System.err.println("Error while finding student by ID: " + e.getMessage());
        }
        return null;
    }

    public Iterable<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        try {
            for (int i = 0; i < size; i++) {
                if (students[i].getStudentName().toLowerCase().contains(name.toLowerCase())) {
                    result.add(students[i]);
                }
            }
        } catch (Exception e) {
            System.err.println("Error while searching students by name: " + e.getMessage());
        }
        return result;
    }

    public void sortByScore() {
        try {
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (students[i].getScore() < students[j].getScore()) {
                        Student temp = students[i];
                        students[i] = students[j];
                        students[j] = temp;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error while sorting students by score: " + e.getMessage());
        }
    }
}