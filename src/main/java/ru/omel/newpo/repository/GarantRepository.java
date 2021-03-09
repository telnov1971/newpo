package ru.omel.newpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omel.newpo.entity.GarantEntity;

public interface GarantRepository extends JpaRepository<GarantEntity, Long> {
}
