package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.UserEntity;
import ru.omel.newpo.repository.DemandRepository;
import ru.omel.newpo.repository.SafeRepository;
import ru.omel.newpo.repository.VoltRepository;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DemandServiceImpl implements DemandService {

    private VoltRepository voltRepository;
    private SafeRepository safeRepository;
    private DemandRepository demandRepository;
    private HistoryService historyService;

    @Autowired
    public DemandServiceImpl(VoltRepository voltRepository, SafeRepository safeRepository, DemandRepository demandRepository, HistoryService historyService) {
        this.voltRepository = voltRepository;
        this.safeRepository = safeRepository;
        this.demandRepository = demandRepository;
        this.historyService = historyService;
    }

    @Override
    public void saveDemand(DemandEntity demand) throws ValidationException {
        String history = new String();

        DemandEntity oldDemand = findById(demand.getId());
            if(!oldDemand.getObject().equals(demand.getObject()))
                history += "Объект с '" + oldDemand.getObject() +
                        "' на '" + demand.getObject() + "'. ";
            if(!oldDemand.getAdress().equals(demand.getAdress()))
                history += "Адрес с '" + oldDemand.getAdress() +
                        "' на '" + demand.getAdress() + "'. ";
            if(!oldDemand.getPowerCur().equals(demand.getPowerCur()))
                history += "Мощность текущая с '" + oldDemand.getPowerCur().toString() +
                        "' на '" + demand.getPowerCur().toString() + "'. ";
            if(!oldDemand.getPowerDec().equals(demand.getPowerDec()))
                history += "Мощность требуемая с '" + oldDemand.getPowerDec().toString() +
                        "' на '" + demand.getPowerDec().toString() + "'. ";
            if(!oldDemand.getVolt().equals(demand.getVolt()))
                history += "Класс напряжения с '" + oldDemand.getVolt().getName() +
                        "' на '" + demand.getVolt().getName() + "'. ";
            if(!oldDemand.getSafe().equals(demand.getSafe()))
                history += "Категория надёжности с '" + oldDemand.getSafe().getName() +
                        "' на '" + demand.getSafe().getName() + "'.";
        if(!history.isEmpty()){
            demand.setUser(oldDemand.getUser());
            demand.setCreateDate(oldDemand.getCreateDate());
            demand.setLoad1c(oldDemand.getLoad1c());
            demand.setRewrite(true);
            try {
                historyService.saveHistory(history, demand);
                demandRepository.save(demand);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
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

/*
    @Override
    public void saveDemand(Long id,
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
            if(!history.isEmpty()){
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
                    e.printStackTrace();
                }
            }
        }
        return demandEntity;
    }

    @Override
    public DemandEntity newDemand(String object, String adress, Double powerCur, Double powerDec, String volt, String safe, UserEntity user) {

        DemandEntity demandEntity = new DemandEntity();
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

*/
    public DemandEntity newDemand(DemandEntity demandEntity, UserEntity user){
        demandEntity.setCreateDate(new Date());
        demandEntity.setUser(user);
        demandEntity.setLoad1c(false);
        demandEntity.setRewrite(false);
        demandRepository.save(demandEntity);
        historyService.saveHistory("Новый запрос: " + demandEntity.forHistory(), demandEntity);
        return demandEntity;
    }

}
