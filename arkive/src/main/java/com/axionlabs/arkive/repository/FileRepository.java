package com.axionlabs.arkive.repository;

import com.axionlabs.arkive.entity.File;
import com.axionlabs.arkive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
    @Query("SELECT f FROM File f LEFT JOIN FETCH f.fileMetaData WHERE f.user = :user ORDER BY f.createdAt DESC ")
    List<File> getAllFilesFromUser(@Param("user") User user);
    @Query("SELECT f from File f LEFT JOIN FETCH f.fileMetaData WHERE f.user = :user AND f.fileId = :id")
    Optional<File> getFileById(@Param("user") User user, @Param("id") UUID id);
}
