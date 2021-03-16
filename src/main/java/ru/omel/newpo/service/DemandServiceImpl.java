package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.UserEntity;
import ru.omel.newpo.repository.DemandRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DemandServiceImpl implements DemandService {

    private final StatusService statusService;
    private final DemandRepository demandRepository;
    private final HistoryService historyService;

    @Autowired
    public DemandServiceImpl(DemandRepository demandRepository
            , HistoryService historyService
            , StatusService statusService) {
        this.statusService = statusService;
        this.demandRepository = demandRepository;
        this.historyService = historyService;
    }

    @Override
    public void saveDemand(DemandEntity demand) {
        DemandEntity oldDemand = findById(demand.getId());
        String history = history(demand, oldDemand);
        if(!history.isEmpty()){
            demand.setUser(oldDemand.getUser());
            demand.setCreateDate(oldDemand.getCreateDate());
            demand.setDueDate(oldDemand.getDueDate());
            demand.setModDate(new Date());
            demand.setStatus(oldDemand.getStatus());
            demand.setContract(oldDemand.getContract());
            demand.setLoad1c(oldDemand.getLoad1c());
            demand.setUpdate1c(false);
            demand.setRewrite(true);
            try {
                historyService.saveHistory(history, demand);
                demandRepository.save(demand);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private String history(DemandEntity demand, DemandEntity oldDemand){
        String history = "";
        if((oldDemand.getObject()!=null)&&!oldDemand.getObject().equals(demand.getObject()))
            history += "Объект с '" + oldDemand.getObject() +
                    "' на '" + demand.getObject() + "'. ";
        if((oldDemand.getAdress()!=null)&&!oldDemand.getAdress().equals(demand.getAdress()))
            history += "Адрес объекта с '" + oldDemand.getAdress() +
                    "' на '" + demand.getAdress() + "'. ";
        if((oldDemand.getDeclarant()!=null)&&!oldDemand.getDeclarant().equals(demand.getDeclarant()))
            history += "Заявитель с '" + oldDemand.getDeclarant() +
                    "' на '" + demand.getDeclarant() + "'. ";
        if((oldDemand.getContact()!=null)&&!oldDemand.getContact().equals(demand.getContact()))
            history += "Контактный номер с '" + oldDemand.getContact() +
                    "' на '" + demand.getContact() + "'. ";
        if((oldDemand.getRequisite()!=null)&&!oldDemand.getRequisite().equals(demand.getRequisite()))
            history += "Реквизиты заявителя с '" + oldDemand.getRequisite() +
                    "' на '" + demand.getRequisite() + "'. ";
        if((oldDemand.getAdressUr()!=null)&&!oldDemand.getAdressUr().equals(demand.getAdressUr()))
            history += "Юридический адрес объекта с '" + oldDemand.getAdressUr() +
                    "' на '" + demand.getAdressUr() + "'. ";
        if((oldDemand.getAdressFact()!=null)&&!oldDemand.getAdressFact().equals(demand.getAdressFact()))
            history += "Фактический  адрес объекта с '" + oldDemand.getAdressFact() +
                    "' на '" + demand.getAdressFact() + "'. ";
        if((oldDemand.getDescription()!=null)&&!oldDemand.getDescription().equals(demand.getDescription()))
            history += "Пояснения с '" + oldDemand.getDescription() +
                    "' на '" + demand.getDescription() + "'. ";

        if((oldDemand.getPowerCur()!=null)&&!oldDemand.getPowerCur().equals(demand.getPowerCur()))
            history += "Мощность текущая с '" + oldDemand.getPowerCur().toString() +
                    "' на '" + demand.getPowerCur().toString() + "'. ";
        if((oldDemand.getPowerDec()!=null)&&!oldDemand.getPowerDec().equals(demand.getPowerDec()))
            history += "Мощность требуемая с '" + oldDemand.getPowerDec().toString() +
                    "' на '" + demand.getPowerDec().toString() + "'. ";
        if((oldDemand.getPowerMax()!=null)&&!oldDemand.getPowerMax().equals(demand.getPowerMax()))
            history += "Мощность максимальная с '" + oldDemand.getPowerMax().toString() +
                    "' на '" + demand.getPowerMax().toString() + "'. ";

        if((oldDemand.getVolt()!=null)&&!oldDemand.getVolt().equals(demand.getVolt()))
            history += "Класс напряжения с '" + oldDemand.getVolt().getName() +
                    "' на '" + demand.getVolt().getName() + "'. ";
        if((oldDemand.getSafe()!=null)&&!oldDemand.getSafe().equals(demand.getSafe()))
            history += "Категория надёжности с '" + oldDemand.getSafe().getName() +
                    "' на '" + demand.getSafe().getName() + "'.";
        if((oldDemand.getRegion()!=null)&&!oldDemand.getRegion().equals(demand.getRegion()))
            history += "Зона ответственности с '" + oldDemand.getRegion().getName() +
                    "' на '" + demand.getRegion().getName() + "'. ";
        if((oldDemand.getSend()!=null)&&!oldDemand.getSend().equals(demand.getSend()))
            history += "Получение договора с '" + oldDemand.getSend().getName() +
                    "' на '" + demand.getSend().getName() + "'. ";
        if((oldDemand.getPlan()!=null)&&!oldDemand.getPlan().equals(demand.getPlan()))
            history += "Рассрочка платежа с '" + oldDemand.getPlan().getName() +
                    "' на '" + demand.getPlan().getName() + "'. ";
        if((oldDemand.getPrice()!=null)&&!oldDemand.getPrice().equals(demand.getPrice()))
            history += "Ценовая категория с '" + oldDemand.getPrice().getName() +
                    "' на '" + demand.getPrice().getName() + "'. ";
        if((oldDemand.getGarant()!=null)&&!oldDemand.getGarant().equals(demand.getGarant()))
            history += "Гарантирующий поставщик с '" + oldDemand.getGarant().getName() +
                    "' на '" + demand.getGarant().getName() + "'. ";

        return history;
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

    public DemandEntity newDemand(DemandEntity demandEntity, UserEntity user){
        demandEntity.setCreateDate(new Date());
        demandEntity.setUser(user);
        demandEntity.setStatus(statusService.findById((long) 1));
        demandEntity.setLoad1c(false);
        demandEntity.setRewrite(false);
        demandEntity.setModDate(new Date());
        demandEntity.setContract("http://omskelectro.ru");
        demandEntity.setUpdate1c(false);
        demandEntity.setConsent(true);

        demandRepository.save(demandEntity);
        historyService.saveHistory("Новый запрос: " + demandEntity.forHistory(), demandEntity);
        return demandEntity;
    }

}
