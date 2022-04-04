package com.ek.extrakeyboards.controller;

import com.ek.extrakeyboards.entity.Job;
import com.ek.extrakeyboards.entity.User;
import com.ek.extrakeyboards.service.UserService;
import com.ek.extrakeyboards.service.ViewJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ViewJobController {
    @Autowired
    private ViewJobService viewJobService;

    @ResponseBody
    @RequestMapping(value="/viewJobByCreator", method = RequestMethod.GET)
    public ResponseEntity<List<Job>> viewJobByCreator(@RequestParam(value = "user_id") String userId) {
        List<Job> jobs = viewJobService.getJobCreated(userId);
        return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value="/viewJobByApplicant", method = RequestMethod.GET)
    public ResponseEntity<List<Job>> viewJobByApplicant(@RequestParam(value = "user_id") String userId) {
        List<Job> jobs = viewJobService.getJobApplied(userId);
        return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
    }
}
