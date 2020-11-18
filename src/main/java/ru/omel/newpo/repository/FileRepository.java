package ru.omel.newpo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.FileEntity;

import java.util.List;

public interface FileRepository extends CrudRepository<FileEntity, Long> {
    List<FileEntity> findAllByDemand(DemandEntity demand);
}
