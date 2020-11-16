package ru.omel.newpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omel.newpo.entity.VoltEntity;

import java.util.List;

public interface VoltRepository extends JpaRepository<VoltEntity, Long> {
    List<VoltEntity> findAll();
    VoltEntity findByName(String name);
}
