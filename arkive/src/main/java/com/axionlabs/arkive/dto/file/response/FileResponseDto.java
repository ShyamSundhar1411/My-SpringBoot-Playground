package com.axionlabs.arkive.dto.file.response;

import com.axionlabs.arkive.dto.BaseResponseDto;
import com.axionlabs.arkive.dto.file.FileDto;
import com.axionlabs.arkive.dto.file.FileMetaDataDto;
import com.axionlabs.arkive.entity.File;
import com.axionlabs.arkive.entity.FileMetaData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter @Setter
public class FileResponseDto extends BaseResponseDto {
    private FileDto fileData;
    public FileResponseDto(HttpStatusCode statusCode, String statusMessage, FileDto fileDto ){
        super(statusCode,statusMessage);
        this.fileData = fileDto;
    }
}
