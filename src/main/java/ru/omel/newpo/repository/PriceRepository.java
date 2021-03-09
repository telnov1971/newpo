package ru.omel.newpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omel.newpo.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
}
