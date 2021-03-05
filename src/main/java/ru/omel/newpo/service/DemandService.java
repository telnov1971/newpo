package ru.omel.newpo.service;

import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.UserEntity;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface DemandService {
    void saveDemand(DemandEntity demand) throws ValidationException;
    DemandEntity newDemand(DemandEntity demand, UserEntity user) throws ValidationException;
    List<DemandEntity> findAll();
    List<DemandEntity> findAllByUser(UserEntity user);
    DemandEntity findById(Long id);
/*
    void saveDemand(Long id,
                       String object,
                       String adress,
                       Double powerCur,
                       Double powerDec,
                       String volt,
                       String safe,
                       UserEntity user);
    DemandEntity newDemand(String object,
                       String adress,
                       Double powerCur,
                       Double powerDec,
                       String volt,
                       String safe,
                       UserEntity user);
*/
}
