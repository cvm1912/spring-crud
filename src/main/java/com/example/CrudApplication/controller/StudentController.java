package com.example.CrudApplication.controller;

import com.example.CrudApplication.entity.Student;
import com.example.CrudApplication.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;
    public  StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // create record
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
          Student createdStudent =  studentService.createStudent(student);
          return ResponseEntity.status(200).body(createdStudent);
    }

    // get record
    @GetMapping("/get/{id}")
    public void StudentById(){

    }
}
