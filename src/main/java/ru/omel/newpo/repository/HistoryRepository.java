package ru.omel.newpo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.HistoryEntity;

import java.util.List;

public interface HistoryRepository extends CrudRepository<HistoryEntity, Long> {
    List<HistoryEntity> findAllByDemand(DemandEntity demand);
}
