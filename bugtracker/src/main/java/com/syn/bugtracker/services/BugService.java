package com.syn.bugtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syn.bugtracker.entities.Bug;
import com.syn.bugtracker.repositories.BugRepository;
import java.util.Optional;

@Service
public class BugService {

    @Autowired
    private BugRepository bugRepository;

    public Bug createBug(Bug bug) throws Exception {
        if (bug.getTitle() == null || bug.getTitle().isEmpty()) {
            throw new Exception("Title cannot be empty.");
        }
        return bugRepository.save(bug);
    }

    public Optional<Bug> getBugById(Long id) {
        return bugRepository.findById(id);
    }

    public Bug updateBugStatus(Long id, String newStatus) throws Exception {
        Optional<Bug> bugOpt = bugRepository.findById(id);
        if (!bugOpt.isPresent()) {
            throw new Exception("Bug not found.");
        }
        Bug bug = bugOpt.get();
        bug.setStatus(newStatus);
        return bugRepository.save(bug);
    }

    public Bug updateBugDetails(Long id, String newTitle, String newDescription, String newPriority) throws Exception {
        Optional<Bug> bugOpt = bugRepository.findById(id);
        if (!bugOpt.isPresent()) {
            throw new Exception("Bug not found.");
        }
        Bug bug = bugOpt.get();
        bug.setTitle(newTitle);
        bug.setDescription(newDescription);
        bug.setPriority(newPriority);
        return bugRepository.save(bug);
    }
}
