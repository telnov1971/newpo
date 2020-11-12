package ru.omel.newpo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.service.DemandService;

import java.util.List;

@Controller
@AllArgsConstructor
public class DemandController {
    private final DemandService demandService;

    @GetMapping("/")
    public String main(Model model){
        List<DemandEntity> demandEntities = demandService.findAll();
        model.addAttribute("url", "/main");
        model.addAttribute("demands", demandEntities);
        return "main";
    }
}
