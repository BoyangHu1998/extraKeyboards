package com.ek.extrakeyboards.dao;


import com.ek.extrakeyboards.entity.Job;
import com.ek.extrakeyboards.entity.JobApplication;
import com.ek.extrakeyboards.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
public class ApplicationDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<JobApplication> getApplicationByApplicant(String userId) {
        List<JobApplication> applications = null;
        try {
            Session session = sessionFactory.openSession();
            User user = session.get(User.class, userId);
            applications = new ArrayList<>(user.getJobApplications());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return applications;
    }


    public List<JobApplication> getJobApplicationsByJobId(String userId, String jobId) {
        List<JobApplication> applications = null;
        try {
            if (jobId == null) {
                throw new Exception("No such jobId");
            }
            Session session = sessionFactory.openSession();
            User user = session.get(User.class, userId);
            ArrayList<Job> jobsCreatedByUid = new ArrayList<>(user.getJobs());
            for (Job job : jobsCreatedByUid) {
                if (Objects.equals(job.getJobId(), jobId)) {
                    applications =  job.getJobApplications();
                    break;
                }
            }
            if (applications == null) {
                throw new Exception("Job is not created by this user");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return applications;
    }
}
