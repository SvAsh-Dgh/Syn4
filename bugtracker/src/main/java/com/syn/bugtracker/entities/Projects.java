package com.syn.bugtracker.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projects")
@Data
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String projectName;
    private String description;
    private String dateCreated;
    
    @OneToMany(mappedBy = "projects", cascade = CascadeType.ALL)
    private Set<Bug> bugs;
    
    // Many-to-many relationship with User entity
    @ManyToMany
    @JoinTable(
        name = "project_user",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;
    
    // Constructors, getters, and setters
    // ...
}

