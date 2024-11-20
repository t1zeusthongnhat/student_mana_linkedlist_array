package com.example.stumana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final MyLinkedList studentList;

    @Autowired
    public WebController(MyLinkedList studentList) {
        this.studentList = studentList;
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false) String query, @RequestParam(value = "error", required = false) String error, Model model) {
        Iterable<Student> students;
        if (query != null && !query.isEmpty()) {
            students = studentList.searchByName(query);
        } else {
            students = studentList.getAllStudents();
        }
        model.addAttribute("students", students);
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "index";
    }

    @GetMapping("/add-student")
    public String showAddStudentForm() {
        return "add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestParam String name, @RequestParam float score, Model model) {
        try {
            Student student = new Student(name, score); // ID will be auto-incremented
            studentList.add(student);
        } catch (InvalidStudentDataException e) {
            model.addAttribute("error", e.getMessage());
            return "add-student"; // Show the add-student form with error message
        }
        return "redirect:/";
    }

    @GetMapping("/delete-student")
    public String deleteStudent(@RequestParam int id) {
        studentList.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/update-student")
    public String showUpdateStudentForm(@RequestParam int id, Model model) {
        Student student = studentList.findById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "update-student";
        }
        return "redirect:/"; // Redirect if student not found
    }

    @PostMapping("/update-student")
    public String updateStudent(@RequestParam int id, @RequestParam String name, @RequestParam float score, Model model) {
        try {
            studentList.updateStudent(id, name, score);
        } catch (InvalidStudentDataException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("student", new Student(name, score));
            return "update-student"; // Show the update-student form with error message
        }
        return "redirect:/";
    }

    @GetMapping("/sort-students")
    public String sortStudents() {
        studentList.sortByNameLength();
        return "redirect:/";
    }

    @GetMapping("/sort-students-score")
    public String sortStudentsByScore() {
        studentList.sortByScore();
        return "redirect:/";
    }
}
