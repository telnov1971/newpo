package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.omel.newpo.entity.SendEntity;
import ru.omel.newpo.repository.SendRepository;

import java.util.List;

@Service
public class SendService {
    private SendRepository sendRepository;

    @Autowired
    public SendService(SendRepository sendRepository) {
        this.sendRepository = sendRepository;
    }

    public List<SendEntity> findAll() { return sendRepository.findAll(); }
}
