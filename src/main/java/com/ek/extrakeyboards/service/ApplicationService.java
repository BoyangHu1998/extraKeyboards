package com.ek.extrakeyboards.service;

import com.ek.extrakeyboards.dao.ApplicationDao;
import com.ek.extrakeyboards.dao.JobDao;
import com.ek.extrakeyboards.entity.Job;
import com.ek.extrakeyboards.entity.JobApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    public List<JobApplication> getApplicationByApplicant() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userId = loggedInUser.getName();
        return userId != null ? applicationDao.getApplicationByApplicant(userId) : null;
    }

    public List<JobApplication> getJobApplicationsByJobId(String jobId) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userId = loggedInUser.getName();
        return userId != null ? applicationDao.getJobApplicationsByJobId(userId, jobId) : null;
    }
}
