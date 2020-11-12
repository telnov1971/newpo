package ru.omel.newpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omel.newpo.entity.SafeEntity;

import java.util.List;

public interface SafeRepository extends JpaRepository<SafeEntity, Long> {
    List<SafeEntity> findAll();
}
