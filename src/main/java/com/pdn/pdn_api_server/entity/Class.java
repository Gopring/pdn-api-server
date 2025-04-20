package com.pdn.pdn_api_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String className;
    private String password;
    private String professorName;
    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<User> students = new ArrayList<>();

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

}
