package ru.omel.newpo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.repository.DemandRepository;
import ru.omel.newpo.repository.SafeRepository;
import ru.omel.newpo.repository.VoltRepository;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DemandServiceImpl implements DemandService {

    private VoltRepository voltRepository;
    private SafeRepository safeRepository;
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

    @Override
    public boolean saveDemand(Long id,
                              String object,
                              String adress,
                              Double powerCur,
                              Double powerDec,
                              String volt,
                              String safe) {
        DemandEntity demandEntity;
        Optional<DemandEntity> demandEntityOptional = demandRepository.findById(id);
        if(!demandEntityOptional.isEmpty()) {
            demandEntity = demandEntityOptional.get();
            demandEntity.setObject(object);
            demandEntity.setAdress(adress);
            demandEntity.setPowerCur(powerCur);
            demandEntity.setPowerDec(powerDec);
            demandEntity.setVolt(voltRepository.findByName(volt));
            demandEntity.setSafe(safeRepository.findByName(safe));
            demandRepository.save(demandEntity);
            return true;
        }
        else
            return false;

    }

}
