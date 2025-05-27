package com.axionlabs.arkive.mapper;

import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.dto.file.FileMetaDataDto;
import com.axionlabs.arkive.entity.File;
import com.axionlabs.arkive.entity.FileMetaData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {
    File toFileEntity(FileDto fileDto);
    FileDto toFileDto(File file);
    List<FileDto> toFileDtoList(List<File> files);
    default FileDto fromFileAndFileMetaData(File file, FileMetaDataDto fileMetaData){
        FileDto fileDto = new FileDto();
        fileDto.setFileId(file.getFileId());
        fileDto.setFileUrl(file.getFileUrl());
        fileDto.setFileName(file.getFileName());
        fileDto.setFileMetaData(fileMetaData);
        return fileDto;
    }
}
