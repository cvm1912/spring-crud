package com.example.CrudApplication.service;

import com.example.CrudApplication.entity.Student;
import com.example.CrudApplication.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    // create student
    public Student createStudent(Student student){
      // business logic
        Student response = studentRepository.save(student);
        return response;
    }
}
