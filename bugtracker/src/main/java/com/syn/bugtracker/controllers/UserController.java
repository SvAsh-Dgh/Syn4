package com.syn.bugtracker.controllers;

import com.syn.bugtracker.entities.User;
import com.syn.bugtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

        @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        User user = userService.getUserDetails();
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        try {
            // Encode the password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            // Register the user
            userService.register(user);
            
        } catch (Exception e) {
            // Log the exception and show an error message
            System.out.println("Error: " + e.getMessage());
            return "redirect:/register?error";
        }
        return "redirect:/login";
    }
 
}

