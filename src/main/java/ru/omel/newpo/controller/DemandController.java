package ru.omel.newpo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.SafeEntity;
import ru.omel.newpo.entity.VoltEntity;
import ru.omel.newpo.service.DemandService;
import ru.omel.newpo.service.SafeService;
import ru.omel.newpo.service.VoltService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DemandController {
    private final DemandService demandService;
    private final SafeService safeService;
    private final VoltService voltService;

    @GetMapping("/")
    public String main(Model model){
        List<DemandEntity> demandEntities = demandService.findAll();
        model.addAttribute("url", "/main");
        model.addAttribute("demands", demandEntities);
        return "main";
    }

    @GetMapping("/demand/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        DemandEntity demandEntity = demandService.findById(id);
        List<SafeEntity> safeEntities = safeService.findAll();
        List<VoltEntity> voltEntities = voltService.findAll();
        model.addAttribute("demand", demandEntity);
        model.addAttribute("safes", safeEntities);
        model.addAttribute("volts", voltEntities);
        return "demand";
    }

    @PostMapping("/demand/{id}")
    public String saveEdit(Model model,
                           @RequestParam Long id,
                           @RequestParam String object,
                           @RequestParam String adress,
                           @RequestParam Double powerCur,
                           @RequestParam Double powerDec,
                           @RequestParam String volt,
                           @RequestParam String safe){
        demandService.saveDemand(id, object, adress, powerCur, powerDec, volt, safe);
        return "redirect:/";
    }

}
