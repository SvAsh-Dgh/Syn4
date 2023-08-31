package com.syn.bugtracker.entities;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "bugs")
@Data
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private String status;
    private String priority;
    private String dateSubmitted;
    
    // Many-to-one relationship with User entity (submitter)
    @ManyToOne
    @JoinColumn(name = "submitter_id")
    private User submitter;
    
    // One-to-many relationship with Comment entity
    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL)
    private Set<Comment> comments;


    // Many-to-one relationship with Projects entity
    @ManyToOne
    @JoinColumn(name = "projects_id")
    private Projects projects;
    
    // Constructors, getters, and setters
    // ...
}

    

