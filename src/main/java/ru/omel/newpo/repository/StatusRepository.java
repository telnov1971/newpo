package ru.omel.newpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omel.newpo.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
