package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.GarantEntity;
import ru.omel.newpo.repository.GarantRepository;

import java.util.List;

@Service
public class GarantService {
    private GarantRepository garantRepository;

    @Autowired
    public GarantService(GarantRepository garantRepository) {
        this.garantRepository = garantRepository;
    }

    public List<GarantEntity> findAll() { return garantRepository.findAll(); }
}
