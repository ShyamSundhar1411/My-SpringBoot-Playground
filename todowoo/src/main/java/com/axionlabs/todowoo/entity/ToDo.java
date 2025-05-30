package com.axionlabs.todowoo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "todowoo_todo")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class ToDo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID todoId;

    private String title;
    @Column(name = "memo",length = 2048)
    private String memo;
    private boolean isCompleted;
    private boolean isImportant;
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
