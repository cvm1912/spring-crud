package com.example.CrudApplication.service;

import com.example.CrudApplication.Dto.StudentRequestDto;
import com.example.CrudApplication.Dto.StudentResponseDto;
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
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto){
//      // business logic
//        student.setDeleted(false);
//        Student response = studentRepository.save(student);
//        return response;

        Student student =  mapToEntity(studentRequestDto);
        Student response = studentRepository.save(student);
        // map to json
        return mapToDTO(response);

    }

    // get by id
    public Student getStudent(Long id)
    {
        Optional<Student> response =  studentRepository.findByIdAndIsDeletedFalse(id);
        if(response.isPresent())
        {
            return response.get();
        }
        return null;

    }

    public List<Student> getAllStudent(){
        List<Student> response = studentRepository.findByIsDeletedFalse ();
        return  response;
    }


    public Student updateStudent(Long id, Student student){
        Optional<Student> existingStudent =  studentRepository.findByIdAndIsDeletedFalse(id);
        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setName(student.getName());
        studentToSave.setAge(student.getAge());
        studentToSave.setEmail(student.getEmail());
        studentToSave.setRoll(student.getRoll());
        studentToSave.setDeleted(false);
       studentToSave.setSubject(student.getSubject());
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

    // exist by id and isDeleted  = false
    public Boolean softDelete(Long id){
        Optional<Student> isDeleted =  studentRepository.findByIdAndIsDeletedFalse(id);
        if(isDeleted.isEmpty()){
                return false;
        }

        Student student = isDeleted.get();
        student.setDeleted(true);
        studentRepository.save(student);
        return  true;

    }

    private Student mapToEntity(StudentRequestDto studentRequestDto){
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setRoll(studentRequestDto.getRoll());
        student.setSubject(studentRequestDto.getSubject());
        student.setEmail(studentRequestDto.getEmail());
        student.setDeleted(false);
        return  student;
    }

    private StudentResponseDto mapToDTO(Student student){
        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRoll(student.getRoll());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student save successfully");
        return responseDto;
    }
}
