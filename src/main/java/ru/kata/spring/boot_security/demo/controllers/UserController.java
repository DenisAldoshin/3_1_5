package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAll(Model model) {
        List<User> users = userService.showAll();
        model.addAttribute("users", users);
        return "showAll";
    }

    @GetMapping("/user")
    public String showOne(Principal principal, Model model ) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/show";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        List<Role> listRoles = userService.findRoles();
        model.addAttribute("listRoles", listRoles);

        return "new";
    }

    @PostMapping("admin/new")
    public String create(@ModelAttribute("user")User user ) {
        userService.addUser(user);
        return "redirect:/admin";

    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        List<Role> listRoles = userService.findRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "/edit";
    }

    @PatchMapping("/admin/edit/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}

