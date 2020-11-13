package ru.omel.newpo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.VoltEntity;
import ru.omel.newpo.repository.VoltRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class VoltService {
    private VoltRepository voltRepository;

    public List<VoltEntity> findAll(){
        return voltRepository.findAll();
    }
}
