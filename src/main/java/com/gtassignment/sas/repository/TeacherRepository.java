package com.gtassignment.sas.repository;

import com.gtassignment.sas.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByEmail(String email);

    @Query(value = "select count(p) from teacher p where p.email in :emails", nativeQuery = true)
    Long getExistingRecordCountFromEmails(@Param("emails") List<String> emails);
}
