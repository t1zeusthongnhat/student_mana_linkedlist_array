package com.example.stumana;

import java.util.concurrent.atomic.AtomicInteger;

public class Student {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String studentName;
    private float score;

    public Student(String studentName, float score) {
        this.studentName = studentName;
        this.score = score;
    }

    public Student() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", score=" + score +
                ", rank=" + rank() +
                '}';
    }

    public String rank() {
        if (score >= 0 && score < 5.0) {
            return "Fail";
        } else if (score >= 5.0 && score < 6.5) {
            return "Medium";
        } else if (score >= 6.5 && score < 7.5) {
            return "Good";
        } else if (score >= 7.5 && score < 9.0) {
            return "Very Good";
        } else if (score >= 9.0 && score <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid Score";
        }
    }
}