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


    public Student updateStudent(Long id, Student student){
        Optional<Student> existingStudent =  studentRepository.findById(id);
        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setName(student.getName());
        studentToSave.setAge(student.getAge());
        studentToSave.setEmail(student.getEmail());
        studentToSave.setRoll(student.getRoll());
//        studentToSave.setSubject(student.getSubject());
        return studentRepository.save(studentToSave);
    }

    public Boolean deleteStudent(Long id){
        Boolean isstudent = studentRepository.existsById(id);
        if(!isstudent){
            return  false;
        }

        studentRepository.deleteById(id);
        return true;
    }
}
