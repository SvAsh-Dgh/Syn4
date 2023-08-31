package com.syn.bugtracker.repositories;

import com.syn.bugtracker.entities.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
    // You can add custom query methods here if needed
}
