package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.PlanEntity;
import ru.omel.newpo.repository.PlanRepository;

import java.util.List;

@Service
public class PlanService {
    private PlanRepository planRepository;

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<PlanEntity> findAll() { return planRepository.findAll(); }
}
