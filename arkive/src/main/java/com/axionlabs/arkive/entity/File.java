package com.axionlabs.arkive.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "arkive_files")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class File extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_url")
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(mappedBy = "file",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private FileMetaData fileMetaData;



}
