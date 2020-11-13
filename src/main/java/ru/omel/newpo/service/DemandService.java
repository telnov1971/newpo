package ru.omel.newpo.service;

import ru.omel.newpo.entity.DemandEntity;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

public interface DemandService {
    boolean saveDemand(DemandEntity demand) throws ValidationException;
    List<DemandEntity> findAll();
    DemandEntity findById(Long id);
}
