package com.axionlabs.arkive.mapper;

import com.axionlabs.arkive.dto.file.FileMetaDataDto;
import com.axionlabs.arkive.entity.FileMetaData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMetaDataMapper {
    FileMetaData toFileMetaDataEntity(FileMetaDataDto fileMetaDataDto);
    FileMetaDataDto toFileMetaDataDto(FileMetaData fileMetaData);
}
