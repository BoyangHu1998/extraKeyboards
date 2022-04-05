package com.ek.extrakeyboards.controller;

import com.ek.extrakeyboards.dao.ApplicationDao;
import com.ek.extrakeyboards.entity.Job;
import com.ek.extrakeyboards.entity.JobApplication;
import com.ek.extrakeyboards.service.ApplicationService;
import com.ek.extrakeyboards.service.JobService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class JobApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value="/jobApplicationsApplied", method = RequestMethod.GET)
    @ResponseBody
    public List<JobApplication> getJobApplicationApplied() {
        return applicationService.getApplicationByApplicant();
    }

    @RequestMapping(value="/jobApplicationsByJobId", method = RequestMethod.GET)
    @ResponseBody
    public List<JobApplication> getJobApplicationsByJobId(@RequestParam(value = "job_id") String jobId) {
        return applicationService.getJobApplicationsByJobId(jobId);
    }

    @RequestMapping(value="/applyApplication", method = RequestMethod.GET)
    @ResponseBody
    public JobApplication applyApplication(@RequestParam(value = "job_id") String jobId) {
        return applicationService.applyApplication(jobId);
    }
}
