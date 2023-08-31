package com.syn.bugtracker.entities;

import jakarta.persistence.*;
import java.util.Date;

import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Removed commenterUsername; we'll use a User object instead.
    
    private String commentText;
    private Date timestamp;
    
    // Many-to-one relationship with Bug entity
    @ManyToOne
    @JoinColumn(name = "bug_id")
    private Bug bug;
    
    // Many-to-one relationship with User entity (commenter)
    @ManyToOne
    @JoinColumn(name = "commenter_id")
    private User commenter;

    // Removed nested comments
    
    // Constructors, getters, and setters
    // ...
}
