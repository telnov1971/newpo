package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.VoltEntity;
import ru.omel.newpo.repository.VoltRepository;

import java.util.List;

@Service
public class VoltService {
    private VoltRepository voltRepository;

    @Autowired
    public VoltService(VoltRepository voltRepository) {
        this.voltRepository = voltRepository;
    }

    public List<VoltEntity> findAll(){
        return voltRepository.findAll();
    }
}
