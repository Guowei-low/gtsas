package com.gtassignment.sas.repository;

import com.gtassignment.sas.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
