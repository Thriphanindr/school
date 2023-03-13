package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@Service
public class StudentH2Service {

    @Autowired
    private JdbcTemplate db;

    public ArrayList<Student> getAllStudents() {
        return (ArrayList<Student>) db.query("select * from school", new StudentRowMapper());
    }

    public Student getStudentById(int studentId) {
        try {
            return db.queryForObject("select * from school where studentId = ?", new StudentRowMapper(), studentId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Student addStudent(Student student) {
        db.update("insert into school(studentName, gender, standard) values (?,?,?)", student.getStudentName(),
                student.getGender(), student.getStandard());
        return db.queryForObject("select * from school where studentName = ? and gender = ?", new StudentRowMapper(),
                student.getStudentName(), student.getGender());
    }

    public void deleteStudent(int studentId) {
        db.update("delete from school where studentId = ?", studentId);
    }

    public Student updateStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("update school set studentName = ? where studentId =?", student.getStudentName(), studentId);
        }
        if (student.getGender() != null) {
            db.update("update school set gender = ? where studentId =?", student.getGender(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update school set standard = ? where studentId =?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }

}
