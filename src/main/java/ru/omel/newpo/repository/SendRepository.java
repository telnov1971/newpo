package ru.omel.newpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omel.newpo.entity.SendEntity;

public interface SendRepository extends JpaRepository<SendEntity, Long> {
}
