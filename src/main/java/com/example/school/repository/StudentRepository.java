package com.example.school.repository;

import com.example.school.model.Student;
import java.util.*;

public interface StudentRepository{
    ArrayList<Student> getAllStudents();
    Student getStudentById(int StudentId);
    Student  addStudent(Student student);
    Student updateStudent(int StudentId, Student  student);
    void  deleteStudent(int StudentId); 
}