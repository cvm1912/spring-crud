package com.example.CrudApplication.service;

import com.example.CrudApplication.Dto.CreateStudentRequestDto;
import com.example.CrudApplication.Dto.CreateStudentResponseDto;
import com.example.CrudApplication.Dto.UpdateStudentRequestDto;
import com.example.CrudApplication.Dto.UpdateStudentResponseDto;
import com.example.CrudApplication.entity.Student;
import com.example.CrudApplication.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    // create student
    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentRequestDto){
//      // business logic
//        student.setDeleted(false);
//        Student response = studentRepository.save(student);
//        return response;

        Student student =  mapToEntity(studentRequestDto);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
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


    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto updateStudentRequestDto){
        Optional<Student> existingStudent =  studentRepository.findByIdAndIsDeletedFalse(id);
        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setName(updateStudentRequestDto.getName());
        studentToSave.setAge(updateStudentRequestDto.getAge());
        studentToSave.setRoll(updateStudentRequestDto.getRoll());
        studentToSave.setDeleted(false);
        studentToSave.setUpdatedAt(LocalDateTime.now());
        studentToSave.setSubject(updateStudentRequestDto.getSubject());
        Student savedStudent =  studentRepository.save(studentToSave);

        return mapToUpdateDto(savedStudent);
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

    private UpdateStudentResponseDto mapToUpdateDto(Student student){
        UpdateStudentResponseDto responseDto = new UpdateStudentResponseDto();
        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRoll(student.getRoll());
        responseDto.setSubject(student.getSubject());
        responseDto.setDeleted(student.getDeleted());
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());
        return responseDto;
    }

    private Student mapToEntity(CreateStudentRequestDto studentRequestDto){
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setRoll(studentRequestDto.getRoll());
        student.setSubject(studentRequestDto.getSubject());
        student.setEmail(studentRequestDto.getEmail());
        student.setDeleted(false);
        return  student;
    }

    private CreateStudentResponseDto mapToDTO(Student student){
        CreateStudentResponseDto responseDto = new CreateStudentResponseDto();
        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRoll(student.getRoll());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student save successfully");
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());
        return responseDto;
    }
}
