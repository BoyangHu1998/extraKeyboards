package com.ek.extrakeyboards.service;

import com.ek.extrakeyboards.dao.JobDao;
import com.ek.extrakeyboards.dao.UserDao;
import com.ek.extrakeyboards.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewJobService {
    @Autowired
    private JobDao jobDao;

    public List<Job> getJobCreated() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userId = loggedInUser.getName();
        return userId != null ? jobDao.getJobListByCreator(userId) : null;
    }

    public List<Job> getJobApplied() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userId = loggedInUser.getName();
        return userId != null ? jobDao.getJobListByApplicant(userId) : null;
    }
}
