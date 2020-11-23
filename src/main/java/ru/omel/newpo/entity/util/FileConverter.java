package ru.omel.newpo.entity.util;

import org.springframework.stereotype.Component;
import ru.omel.newpo.entity.FileEntity;
import ru.omel.newpo.entity.dto.FileDto;

@Component
public class FileConverter {
    public FileDto fileEntityToFileDto(FileEntity fileEntity){
        FileDto fileDto = new FileDto();
        fileDto.setId(fileEntity.getId());
        fileDto.setName(fileEntity.getName());
        fileDto.setLink(fileEntity.getLink());
        fileDto.setDemand(fileEntity.getDemand().getId());
        return fileDto;
    }
}
