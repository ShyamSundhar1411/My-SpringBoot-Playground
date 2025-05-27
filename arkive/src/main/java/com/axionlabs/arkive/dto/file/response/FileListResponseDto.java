package com.axionlabs.arkive.dto.file.response;

import com.axionlabs.arkive.dto.BaseResponseDto;
import com.axionlabs.arkive.dto.file.FileDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data

public class FileListResponseDto extends BaseResponseDto {
    private List<FileDto> files;
    public FileListResponseDto(HttpStatusCode statusCode, String statusMessage, List<FileDto> files){
        super(statusCode,statusMessage);
        this.files = files;
    }
}
