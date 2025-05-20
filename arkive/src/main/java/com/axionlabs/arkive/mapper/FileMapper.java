package com.axionlabs.arkive.mapper;

import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.entity.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
    File toFileEntity(FileDto fileDto);
    FileDto toFileDto(File file);
}
