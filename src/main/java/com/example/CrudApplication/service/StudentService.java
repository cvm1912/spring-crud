package com.example.CrudApplication.service;

import com.example.CrudApplication.entity.Student;
import com.example.CrudApplication.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // get by id
    public Student getStudent(Long id)
    {
        Optional<Student> response =  studentRepository.findById(id);
        if(response.isPresent())
        {
            return response.get();
        }
        return null;

    }

    public List<Student> getAllStudent(){
        List<Student> response = studentRepository.findAll();
       return  response;
    }
}
