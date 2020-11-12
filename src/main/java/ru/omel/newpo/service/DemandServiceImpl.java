package ru.omel.newpo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.repository.DemandRepository;

import javax.xml.bind.ValidationException;
import java.util.List;

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
}
