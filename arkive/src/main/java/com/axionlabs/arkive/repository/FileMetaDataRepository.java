package com.axionlabs.arkive.repository;

import com.axionlabs.arkive.entity.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileMetaDataRepository extends JpaRepository<FileMetaData, UUID> {

}
