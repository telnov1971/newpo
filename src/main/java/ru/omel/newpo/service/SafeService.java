package ru.omel.newpo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.SafeEntity;
import ru.omel.newpo.repository.SafeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SafeService {
    private SafeRepository safeRepository;

    public List<SafeEntity> findAll(){
        return safeRepository.findAll();
    }
}
