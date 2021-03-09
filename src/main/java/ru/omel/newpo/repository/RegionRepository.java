package ru.omel.newpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omel.newpo.entity.RegionEntity;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
}
