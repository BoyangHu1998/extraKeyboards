package com.ek.extrakeyboards.service;

import com.ek.extrakeyboards.dao.JobDao;
import com.ek.extrakeyboards.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobService {
    @Autowired
    private JobDao jobDao;

    public List<Job> getJobListByKeyword(String keyword) {
        List<Job> jobs = jobDao.getJobListByKeyword(keyword);
        return jobs;
    }
}
