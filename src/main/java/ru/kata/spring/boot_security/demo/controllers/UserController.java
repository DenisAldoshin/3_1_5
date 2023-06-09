package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        User authentificatedUser = userService.findByUsername(principal.getName());
        model.addAttribute("authenticatedUserRoles", authentificatedUser.getRoles());
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        User authentificatedUser = userService.findByUsername(principal.getName());
        model.addAttribute("authenticatedUserRoles", authentificatedUser.getRoles());
        return "admin";
    }
}


