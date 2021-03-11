package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.StatusEntity;
import ru.omel.newpo.repository.StatusRepository;

import java.util.List;

@Service
public class StatusService {
    private StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<StatusEntity> findAll() { return statusRepository.findAll(); }
    public StatusEntity findById(Long id) { return statusRepository.findById(id).get(); }
}
