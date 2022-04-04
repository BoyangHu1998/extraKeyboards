package com.ek.extrakeyboards.service;

import com.ek.extrakeyboards.dao.JobDao;
import com.ek.extrakeyboards.dao.UserDao;
import com.ek.extrakeyboards.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewJobService {
    @Autowired
    private JobDao jobDao;

    public List<Job> getJobCreated(String userId) {
        return jobDao.getJobListByCreator(userId);
    }

    public List<Job> getJobApplied(String userId) {
        return jobDao.getJobListByApplicant(userId);
    }
}
