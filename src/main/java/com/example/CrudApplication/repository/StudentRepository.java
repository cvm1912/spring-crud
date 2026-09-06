package com.example.CrudApplication.repository;

import com.example.CrudApplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndIsDeletedFalse(Long id);
    List<Student> findByIsDeletedFalse();
}
