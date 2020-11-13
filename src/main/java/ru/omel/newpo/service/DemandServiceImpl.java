package ru.omel.newpo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.repository.DemandRepository;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DemandServiceImpl implements DemandService {

    private DemandRepository demandRepository;

    @Override
    public boolean saveDemand(DemandEntity demand) throws ValidationException {
        return false;
    }

    @Override
    public List<DemandEntity> findAll() {
        return demandRepository.findAll();
    }

    @Override
    public DemandEntity findById(Long id) {
        Optional<DemandEntity> demandEntity = demandRepository.findById(id);
        return demandEntity.get();
    }

}
