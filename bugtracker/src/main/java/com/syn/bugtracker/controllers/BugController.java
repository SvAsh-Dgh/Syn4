package com.syn.bugtracker.controllers;

import com.syn.bugtracker.entities.Bug;
import com.syn.bugtracker.services.BugService;
import com.syn.bugtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BugController {

    @Autowired
    private BugService bugService;

    @Autowired
    private UserService userService;

    @GetMapping("/submit-bug")
    public String showBugForm(Model model) {
        model.addAttribute("bug", new Bug());
        return "submit-bug";
    }

    @PostMapping("/submit-bug")
    public String submitBug(@ModelAttribute Bug bug) {
        try {
            bug.setSubmitter(userService.getUserDetails());
            bugService.createBug(bug);
        } catch (Exception e) {
            // Handle exception (e.g., title cannot be empty)
        }
        return "redirect:/profile";
    }

    @GetMapping("/bug-details/{id}")
    public String showBugDetails(@PathVariable Long id, Model model) {
        Optional<Bug> bugOpt = bugService.getBugById(id);
        if (bugOpt.isPresent()) {
            model.addAttribute("bug", bugOpt.get());
        } else {
            // Handle bug not found
        }
        return "bug-details";
    }

    @GetMapping("/edit-bug/{id}")
    public String showBugEditForm(@PathVariable Long id, Model model) {
        Optional<Bug> bugOpt = bugService.getBugById(id);
        if (bugOpt.isPresent()) {
            model.addAttribute("bug", bugOpt.get());
        } else {
            // Handle bug not found
        }
        return "edit-bug";
    }

    @PostMapping("/edit-bug/{id}")
    public String updateBugDetails(@PathVariable Long id,
                                   @RequestParam String newTitle,
                                   @RequestParam String newDescription,
                                   @RequestParam String newPriority) {
        try {
            bugService.updateBugDetails(id, newTitle, newDescription, newPriority);
        } catch (Exception e) {
            // Handle exception (e.g., Bug not found)
        }
        return "redirect:/bug-details/" + id;
    }

    @PostMapping("/update-status/{id}")
    public String updateBugStatus(@PathVariable Long id, @RequestParam String newStatus) {
        try {
            bugService.updateBugStatus(id, newStatus);
        } catch (Exception e) {
            // Handle exception (e.g., Bug not found)
        }
        return "redirect:/bug-details/" + id;
    }
}



