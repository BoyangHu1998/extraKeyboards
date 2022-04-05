package com.ek.extrakeyboards.service;

import com.ek.extrakeyboards.dao.ApplicationDao;
import com.ek.extrakeyboards.dao.JobDao;
import com.ek.extrakeyboards.entity.ApplicationStatus;
import com.ek.extrakeyboards.entity.Job;
import com.ek.extrakeyboards.entity.JobApplication;
import com.ek.extrakeyboards.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

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

    public JobApplication applyApplication(String jobId) {
        final JobApplication jobApplication = new JobApplication();
        final Job job = jobService.getJob(jobId);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUser(username);

        jobApplication.setUser(user);
        jobApplication.setJob(job);
        jobApplication.setStatus(ApplicationStatus.IN_PROCESS);
        applicationDao.saveApplication(jobApplication);

        return jobApplication;
    }

    public JobApplication approveApplication(String applicationId) {
        final JobApplication jobApplication = this.getApplicationById(applicationId);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUser(username);

        if (user != null && jobApplication != null && user.getUserId() == jobApplication.getUser().getUserId()) {
            jobApplication.setStatus(ApplicationStatus.ACCEPTED);
            applicationDao.saveApplication(jobApplication);
        }
        return jobApplication;
    }

    public JobApplication declineApplication(String applicationId) {
        final JobApplication jobApplication = this.getApplicationById(applicationId);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUser(username);

        if (user != null && jobApplication != null && user.getUserId() == jobApplication.getUser().getUserId()) {
            jobApplication.setStatus(ApplicationStatus.DECLINED);
            applicationDao.saveApplication(jobApplication);
        }
        return jobApplication;
    }

    private JobApplication getApplicationById(String applicationId) {
        return applicationDao.getApplicationById(applicationId);
    }
}
