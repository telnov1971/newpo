package ru.omel.newpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.UserEntity;

import java.util.List;

public interface DemandRepository extends JpaRepository<DemandEntity, Long> {
    List<DemandEntity> findAll();
    List<DemandEntity> findAllByUser(UserEntity user);

    @Query("select max(id) from DemandEntity ")
    Long maxId();
}
