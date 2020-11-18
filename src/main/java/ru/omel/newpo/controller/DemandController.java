package ru.omel.newpo.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.omel.newpo.entity.*;
import ru.omel.newpo.repository.DemandRepository;
import ru.omel.newpo.service.DemandService;
import ru.omel.newpo.service.HistoryService;
import ru.omel.newpo.service.SafeService;
import ru.omel.newpo.service.VoltService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DemandController {
    private final DemandService demandService;
    private final DemandRepository demandRepository;
    private final SafeService safeService;
    private final VoltService voltService;
    private final HistoryService historyService;

    @GetMapping("/")
    public String main(Model model,
                       @AuthenticationPrincipal UserEntity user){
        List<DemandEntity> demandEntities = demandService.findAllByUser(user);
        model.addAttribute("url", "/main");
        model.addAttribute("demands", demandEntities);
        return "main";
    }

    @GetMapping("/demand/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        DemandEntity demandEntity = demandService.findById(id);
        List<SafeEntity> safeEntities = safeService.findAll();
        List<VoltEntity> voltEntities = voltService.findAll();
        List<HistoryEntity> historyEntities = historyService.findAllByDemand(demandEntity);
        model.addAttribute("demand", demandEntity);
        model.addAttribute("safes", safeEntities);
        model.addAttribute("volts", voltEntities);
        model.addAttribute("history", historyEntities);
        return "demand";
    }

    @PostMapping("/demand/{id}")
    public String saveEdit(Model model,
                           @AuthenticationPrincipal UserEntity user,
                           @RequestParam Long id,
                           @RequestParam String object,
                           @RequestParam String adress,
                           @RequestParam Double powerCur,
                           @RequestParam Double powerDec,
                           @RequestParam String volt,
                           @RequestParam String safe){
        demandService.saveDemand(id, object, adress, powerCur, powerDec, volt, safe, user);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newDemand(Model model,
                            @AuthenticationPrincipal UserEntity user){
        List<SafeEntity> safeEntities = safeService.findAll();
        List<VoltEntity> voltEntities = voltService.findAll();
        model.addAttribute("safes", safeEntities);
        model.addAttribute("volts", voltEntities);
        return "demand";
    }

    @PostMapping("/new")
    public String saveNewDemand(Model model,
                           @AuthenticationPrincipal UserEntity user,
                           @RequestParam String object,
                           @RequestParam String adress,
                           @RequestParam Double powerCur,
                           @RequestParam Double powerDec,
                           @RequestParam String volt,
                           @RequestParam String safe){
        demandService.newDemand(object, adress, powerCur, powerDec, volt, safe, user);
        return "redirect:/";
    }

}
