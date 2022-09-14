package com.gtassignment.sas.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = { "id" })
public class Student {

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "suspend")
    private Boolean suspend;

    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();
}