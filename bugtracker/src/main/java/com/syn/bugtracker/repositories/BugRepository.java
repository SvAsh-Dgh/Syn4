package com.syn.bugtracker.repositories;

import com.syn.bugtracker.entities.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
    // You can add custom query methods here if needed
}
