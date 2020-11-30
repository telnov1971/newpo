package ru.omel.newpo.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.UserEntity;
import ru.omel.newpo.repository.DemandRepository;
import ru.omel.newpo.repository.HistoryRepository;
import ru.omel.newpo.repository.SafeRepository;
import ru.omel.newpo.repository.VoltRepository;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DemandServiceImpl implements DemandService {

    private VoltRepository voltRepository;
    private SafeRepository safeRepository;
    private DemandRepository demandRepository;
    private HistoryService historyService;

    @Override
    public boolean saveDemand(DemandEntity demand) throws ValidationException {
        demandRepository.save(demand);
        return true;
    }

    @Override
    public List<DemandEntity> findAll() {
        return demandRepository.findAll();
    }

    @Override
    public List<DemandEntity> findAllByUser(UserEntity user) {
        return demandRepository.findAllByUser(user);
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
                              String safe,
                              UserEntity user) {
        String history = new String();
        DemandEntity demandEntity;
        Optional<DemandEntity> demandEntityOptional = demandRepository.findById(id);
        if(!demandEntityOptional.isEmpty()) {
            demandEntity = demandEntityOptional.get();
            if (!demandEntityOptional.isEmpty()) {
                demandEntity = demandEntityOptional.get();
                if(!demandEntity.getObject().equals(object))
                    history += "Объект с '" + demandEntity.getObject() +
                                "' на '" + object + "'. ";
                if(!demandEntity.getAdress().equals(adress))
                    history += "Адрес с '" + demandEntity.getAdress() +
                                "' на '" + adress + "'. ";
                if(!demandEntity.getPowerCur().equals(powerCur))
                    history += "Мощность текущая с '" + demandEntity.getPowerCur().toString() +
                                "' на '" + powerCur.toString() + "'. ";
                if(!demandEntity.getPowerDec().equals(powerDec))
                    history += "Мощность требуемая с '" + demandEntity.getPowerDec().toString() +
                                "' на '" + powerDec.toString() + "'. ";
                if(!demandEntity.getVolt().equals(voltRepository.findByName(volt)))
                    history += "Класс напряжения с '" + demandEntity.getVolt().getName() +
                                "' на '" + voltRepository.findByName(volt).getName() + "'. ";
                if(!demandEntity.getSafe().equals(safeRepository.findByName(safe)))
                        history += "Категория надёжности с '" + demandEntity.getSafe().getName() +
                                "' на '" + safeRepository.findByName(safe).getName() + "'.";
                }
            if(history!=""){
                demandEntity.setObject(object);
                demandEntity.setAdress(adress);
                demandEntity.setPowerCur(powerCur);
                demandEntity.setPowerDec(powerDec);
                demandEntity.setVolt(voltRepository.findByName(volt));
                demandEntity.setSafe(safeRepository.findByName(safe));
                demandEntity.setUser(user);
                demandEntity.setRewrite(true);
                try {
                    historyService.saveHistory(history, demandEntity);
                    demandRepository.save(demandEntity);
                    return true;
                } catch (DataAccessException e) {
                    return false;
                }
            }
        }
        else
            return false;
        return false;
    }

    @Override
    public DemandEntity newDemand(String object, String adress, Double powerCur, Double powerDec, String volt, String safe, UserEntity user) {

        DemandEntity demandEntity = new DemandEntity();
        /*
        Long id = demandRepository.maxId() + 1;
        demandEntity.setId(id);

         */
        demandEntity.setCreateDate(new Date());
        demandEntity.setObject(object);
        demandEntity.setAdress(adress);
        demandEntity.setPowerCur(powerCur);
        demandEntity.setPowerDec(powerDec);
        demandEntity.setVolt(voltRepository.findByName(volt));
        demandEntity.setSafe(safeRepository.findByName(safe));
        demandEntity.setUser(user);
        demandEntity.setLoad1c(false);
        demandEntity.setRewrite(true);
        demandRepository.save(demandEntity);
        historyService.saveHistory("Новый запрос: " + demandEntity.forHistory(), demandEntity);
        return demandEntity;
    }

}
