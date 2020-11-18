package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.FileEntity;
import ru.omel.newpo.repository.FileRepository;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public List<FileEntity> findAllByDemand(DemandEntity demand){
        return fileRepository.findAllByDemand(demand);
    }
}
