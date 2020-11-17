package ru.omel.newpo.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.UserEntity;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

public interface DemandService {
    boolean saveDemand(DemandEntity demand) throws ValidationException;
    List<DemandEntity> findAll();
    List<DemandEntity> findAllByUser(UserEntity user);
    DemandEntity findById(Long id);
    boolean saveDemand(Long id,
                       String object,
                       String adress,
                       Double powerCur,
                       Double powerDec,
                       String volt,
                       String safe,
                       UserEntity user);
}
