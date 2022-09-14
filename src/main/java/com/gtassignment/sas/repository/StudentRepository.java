package com.gtassignment.sas.repository;

import com.gtassignment.sas.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select count(p) from student p where p.email in :emails", nativeQuery = true)
    Long getExistingRecordCountFromEmails(@Param("emails") List<String> emails);
}