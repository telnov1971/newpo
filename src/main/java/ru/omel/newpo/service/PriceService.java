package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.PriceEntity;
import ru.omel.newpo.repository.PriceRepository;

import java.util.List;

@Service
public class PriceService {
    private PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<PriceEntity> findAll() { return priceRepository.findAll(); }
}
