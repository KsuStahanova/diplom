package com.example.officeapp.controller;

import org.apache.catalina.User;

import com.example.officeapp.model.User;
import com.example.officeapp.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") User user, 
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("emailError", "Email already registered");
            return "registration";
        }

        userService.registerUser(user);
        return "redirect:/login?registered";
    }
}