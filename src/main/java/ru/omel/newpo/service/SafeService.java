package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.SafeEntity;
import ru.omel.newpo.repository.SafeRepository;

import java.util.List;

@Service
public class SafeService {
    private SafeRepository safeRepository;

    @Autowired
    public SafeService(SafeRepository safeRepository) {
        this.safeRepository = safeRepository;
    }

    public List<SafeEntity> findAll(){
        return safeRepository.findAll();
    }
}
