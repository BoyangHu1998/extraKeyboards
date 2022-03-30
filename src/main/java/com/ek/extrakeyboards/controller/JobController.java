package com.ek.extrakeyboards.controller;

import com.ek.extrakeyboards.entity.Job;
import com.ek.extrakeyboards.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value="/search", method = RequestMethod.GET)
    @ResponseBody
    public List<Job> getJobListByKeyword(@RequestParam(value = "keyword") String keyword) {
        return jobService.getJobListByKeyword(keyword);
    }
}
