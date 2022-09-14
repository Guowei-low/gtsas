package com.gtassignment.sas.repository;

import com.gtassignment.sas.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
//    @Query("SELECT s FROM student s WHERE s.email = :email")
    Student findByEmail(String email);
    @Query(value = "select count(p) from student p where p.email in :emails", nativeQuery = true)
    Long getExistingRecordCountFromEmails(@Param("emails") List<String> emails);

    @Query(value = "select * from student p where p.email in :emails", nativeQuery = true)
    List<Student> findStudentByEmails(@Param("emails") List<String> emails);

    @Query(value = """
                select s.* from student s
                    left join teacher_student ts on ts.student_id = s.id  
                    inner join teacher t on ts.teacher_id = t.id
                where t.email in :emails 
                group by s.id
                having count(s.id) = :teacherEmailsCount
                """, nativeQuery = true)
    List<Student> findCommonStudentsByTeacherEmails(
            @Param("emails") List<String> emails,
            @Param("teacherEmailsCount") Long teacherEmailsCount);
}