package com.example.stumana;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyLinkedList {
    Node head, tail;
    private int nextId = 1;

    public MyLinkedList() {
        head = null;
        tail = null;
    }

    // Validate student name and score
    private void validateStudentData(String studentName, float score) throws InvalidStudentDataException {
        if (!studentName.matches("[a-zA-Z ]+")) {
            throw new InvalidStudentDataException("Name must contain only letters and spaces.");
        }
        if (score < 0 || score > 10) {
            throw new InvalidStudentDataException("Score must be between 0 and 10.");
        }
    }

    public void add(Student s) throws InvalidStudentDataException {
        validateStudentData(s.getStudentName(), s.getScore());
        s.setId(nextId++);
        Node p = new Node(s);
        if (head == null) {
            head = p;
            tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    public void traverse() {
        Node p = head;
        while (p != null) {
            System.out.println(p.info);
            p = p.next;
        }
    }

    public void updateStudent(int id, String newName, float newScore) throws InvalidStudentDataException {
        Node p = head;
        while (p != null) {
            if (p.info.getId() == id) {
                validateStudentData(newName, newScore); // Validate the new student data
                p.info.setStudentName(newName);
                p.info.setScore(newScore);
                System.out.println("Student updated successfully!");
                return;
            }
            p = p.next;
        }
        throw new InvalidStudentDataException("Student with id " + id + " not found.");
    }

    public void deleteById(int id) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.info.getId() == id) {
            head = head.next;
            System.out.println("Student deleted successfully!");
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.info.getId() == id) {
                current.next = current.next.next;
                System.out.println("Student deleted successfully!");
                return;
            }
            current = current.next;
        }
        System.out.println("Student with id " + id + " not found.");
    }

    public void sortByNameLength() {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.info.getStudentName().length() > current.next.info.getStudentName().length()) {
                    Student temp = current.info;
                    current.info = current.next.info;
                    current.next.info = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public Iterable<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Node current = head;
        while (current != null) {
            students.add(current.info);
            current = current.next;
        }
        return students;
    }

    public Student findById(int id) {
        Node current = head;
        while (current != null) {
            if (current.info.getId() == id) {
                return current.info;
            }
            current = current.next;
        }
        return null;
    }

    public Iterable<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        Node current = head;
        while (current != null) {
            if (current.info.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                result.add(current.info);
            }
            current = current.next;
        }
        return result;
    }

    public void sortByScore() {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.info.getScore() < current.next.info.getScore()) { // Sắp xếp điểm từ cao xuống thấp
                    Student temp = current.info;
                    current.info = current.next.info;
                    current.next.info = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
}
