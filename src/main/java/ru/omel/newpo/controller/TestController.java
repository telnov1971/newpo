package ru.omel.newpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.omel.newpo.entity.DemandEntity;
import ru.omel.newpo.entity.UserEntity;
import ru.omel.newpo.service.DemandService;
import ru.omel.newpo.service.UserService;

@Controller
public class TestController {
    private final UserService userService;
    private final DemandService demandService;

    @Autowired
    TestController(UserService userService, DemandService demandService){
        this.demandService = demandService;
        this.userService = userService;
    }

    @GetMapping("/fileUpload/{id}")
    public String upload(@PathVariable("id") Long id,
                         Model model,
                         @AuthenticationPrincipal UserEntity user){
        DemandEntity demandEntity = demandService.findById(id);
        model.addAttribute("demand", demandEntity);
        return "fileUpload";
    }
}
