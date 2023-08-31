package com.syn.bugtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syn.bugtracker.entities.Bug;
import com.syn.bugtracker.entities.Projects;
import com.syn.bugtracker.entities.User;
import com.syn.bugtracker.repositories.ProjectRepository;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Projects createProject(Projects project) throws Exception {
        if (project.getProjectName() == null || project.getProjectName().isEmpty()) {
            throw new Exception("Project name cannot be empty.");
        }
        return projectRepository.save(project);
    }

    public Optional<Projects> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Projects assignUserToProject(Long projectId, User user) throws Exception {
        Optional<Projects> projectOpt = projectRepository.findById(projectId);
        if (!projectOpt.isPresent()) {
            throw new Exception("Project not found.");
        }
        Projects project = projectOpt.get();
        Set<User> users = project.getUsers();
        users.add(user);
        project.setUsers(users);
        return projectRepository.save(project);
    }

    public Set<Bug> getAllBugsForProject(Long projectId) throws Exception {
        Optional<Projects> projectOpt = projectRepository.findById(projectId);
        if (!projectOpt.isPresent()) {
            throw new Exception("Project not found.");
        }
        return projectOpt.get().getBugs();
    }
}
