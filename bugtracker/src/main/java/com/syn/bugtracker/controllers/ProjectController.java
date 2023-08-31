package com.syn.bugtracker.controllers;

import com.syn.bugtracker.entities.Projects;
import com.syn.bugtracker.entities.User;
import com.syn.bugtracker.services.ProjectService;
import com.syn.bugtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("project", new Projects());
        return "create-project";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute Projects project) {
        try {
            projectService.createProject(project);
        } catch (Exception e) {
            // Handle exception (e.g., invalid project name)
        }
        return "redirect:/projects";
    }

    @GetMapping("/{id}")
    public String showProjectDetails(@PathVariable Long id, Model model) {
        Optional<Projects> projectOpt = projectService.getProjectById(id);
        if (projectOpt.isPresent()) {
            model.addAttribute("project", projectOpt.get());
        } else {
            // Handle project not found
        }
        return "project-details";
    }

    @PostMapping("/{id}/assign-user")
    public String assignUserToProject(@PathVariable Long id, @RequestParam String username) {
        try {
            Optional<User> userOpt = userService.getByUsername(username);
            if (userOpt.isPresent()) {
                projectService.assignUserToProject(id, userOpt.get());
            } else {
                // Handle user not found
            }
        } catch (Exception e) {
            // Handle exception (e.g., project not found)
        }
        return "redirect:/projects/" + id;
    }
}
