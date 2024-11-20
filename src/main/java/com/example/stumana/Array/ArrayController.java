package com.example.stumana.Array;

import com.example.stumana.InvalidStudentDataException;
import com.example.stumana.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/array")
public class ArrayController {

    @Autowired
    private MyArray myArray;

    @GetMapping("")
    public String home(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("students", myArray.getAllStudents());
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "array/index";
    }

    @GetMapping("/add-student")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "array/add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(@ModelAttribute Student student, Model model) {
        try {
            myArray.add(student);
        } catch (InvalidStudentDataException e) {
            return "redirect:/array?error=" + e.getMessage();
        }
        return "redirect:/array";
    }

    @GetMapping("/update-student")
    public String showUpdateForm(@RequestParam("id") int id, Model model) {
        Student student = myArray.findById(id);
        if (student == null) {
            return "redirect:/array";
        }
        model.addAttribute("student", student);
        return "array/update-student";
    }

    @PostMapping("/update-student")
    public String updateStudent(@RequestParam("id") int id, @ModelAttribute Student student, Model model) {
        try {
            myArray.updateStudent(id, student.getStudentName(), student.getScore());
        } catch (InvalidStudentDataException e) {
            return "redirect:/array?error=" + e.getMessage();
        }
        return "redirect:/array";
    }

    @GetMapping("/delete-student")
    public String deleteStudent(@RequestParam("id") int id) {
        myArray.deleteById(id);
        return "redirect:/array";
    }

    @GetMapping("/sort-students")
    public String sortByNameLength(Model model) {
        myArray.sortByNameLength();
        model.addAttribute("students", myArray.getAllStudents());
        return "array/index";
    }

    @GetMapping("/sort-students-score")
    public String sortByScore(Model model) {
        myArray.sortByScore();
        model.addAttribute("students", myArray.getAllStudents());
        return "array/index";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam("query") String name, Model model) {
        model.addAttribute("students", myArray.searchByName(name));
        return "array/index";
    }
}
