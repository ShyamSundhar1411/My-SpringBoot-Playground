package com.axionlabs.arkive.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "arkive_files_meta_data")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class FileMetaData extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID fileMetaDataId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "file_id",nullable = false)
    private File file;

    private String contentType;

    @Column(name = "size_in_bytes")
    private Long sizeInBytes;

    private String checksum;

    private String checksumType;

    private String encryptionAlgorithm;

}
