package com.example.CrudApplication.controller;

import com.example.CrudApplication.Dto.CreateStudentRequestDto;
import com.example.CrudApplication.Dto.CreateStudentResponseDto;
import com.example.CrudApplication.Dto.GetStudentResponseDto;
import com.example.CrudApplication.Dto.UpdateStudentRequestDto;
import com.example.CrudApplication.Dto.UpdateStudentResponseDto;
import com.example.CrudApplication.entity.Student;
import com.example.CrudApplication.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;
    public  StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // create record
    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDto> createStudent(@RequestBody CreateStudentRequestDto studentRequestDto){
          CreateStudentResponseDto createdStudent =  studentService.createStudent(studentRequestDto);
          return ResponseEntity.status(200).body(createdStudent);
    }

    // get record
    @GetMapping("/get/{id}")
    public ResponseEntity<GetStudentResponseDto> getStudent(@PathVariable Long id){
        GetStudentResponseDto student = studentService.getStudent(id);
        if(student == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student);
    }

    // get all
    @GetMapping("/get")
    public ResponseEntity<List<GetStudentResponseDto>> getALlStudent(){
        List<GetStudentResponseDto> students = studentService.getAllStudent();
        if(students.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(students);
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDto updateStudentRequestDto){
        UpdateStudentResponseDto student = studentService.updateStudent(id, updateStudentRequestDto);
        if(student==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(student);

    }

    // deleted
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        Boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted)
        {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("record deleted");

    }

    // soft delete
    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDelete(@PathVariable Long id){

        Boolean isSoftDelete = studentService.softDelete(id);
        if(!isSoftDelete){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("record deleted");
    }



}
