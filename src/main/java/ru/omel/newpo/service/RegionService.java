package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.RegionEntity;
import ru.omel.newpo.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {
    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<RegionEntity> findAll() { return regionRepository.findAll(); }
}
