package ru.omel.newpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.omel.newpo.entity.UserEntity;
import ru.omel.newpo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("profile")
    public String getProfile(Model model,
                             @AuthenticationPrincipal UserEntity user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());

        return "profile";
    }

    @CrossOrigin
    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal UserEntity user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }

    @CrossOrigin
    @PostMapping("/login")
    public String login(
            @AuthenticationPrincipal UserEntity user,
            @RequestParam String password){
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(
            @AuthenticationPrincipal UserEntity user,
            @RequestParam String password){
        return "redirect:/";
    }
}
