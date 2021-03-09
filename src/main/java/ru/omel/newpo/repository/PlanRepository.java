package ru.omel.newpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omel.newpo.entity.PlanEntity;

public interface PlanRepository extends JpaRepository<PlanEntity,Long> {

}
