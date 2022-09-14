package com.gtassignment.sas.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = { "id" })
public class Teacher {

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "teacher_student",
            joinColumns = @JoinColumn(name = "teacher_email"),
            inverseJoinColumns = @JoinColumn(name = "student_email"))
    private Set<Student> students = new HashSet<>();
}