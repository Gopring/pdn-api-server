package com.pdn.pdn_api_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    private String id; // 학번

    private String name;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classRoom;
}
