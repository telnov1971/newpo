package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.HistoryEntity;
import ru.omel.newpo.repository.DemandRepository;
import ru.omel.newpo.repository.HistoryRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    private HistoryRepository historyRepository;
    private DemandRepository demandRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository, DemandRepository demandRepository) {
        this.historyRepository = historyRepository;
        this.demandRepository = demandRepository;
    }

    public List<HistoryEntity> findAllByDemand(DemandEntity demand){
        return historyRepository.findAllByDemand(demand);
    }

    public boolean saveHistory(String history, DemandEntity newDemand){
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setCreateDate(new Date());
        historyEntity.setDemand(newDemand);
        historyEntity.setEvent(history);
        historyEntity.setLoad1c(false);
        historyEntity.setClient(true);
        try {
            historyRepository.save(historyEntity);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
