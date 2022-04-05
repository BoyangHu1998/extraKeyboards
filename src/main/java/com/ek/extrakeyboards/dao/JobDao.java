package com.ek.extrakeyboards.dao;

import com.ek.extrakeyboards.entity.Job;
import com.ek.extrakeyboards.entity.JobApplication;
import com.ek.extrakeyboards.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class JobDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Job> getJobListByKeyword(String keyword) {
        try (Session session = sessionFactory.openSession()) {//The try-with-resources statement ensures that each resource is closed at the end of the statement.
            //return session.get(User.class, keyword).getItemSet();
            String hql = "SELECT o FROM Job o WHERE o.jobTitle like : kw";
            String s="%"+keyword+"%";
            return session.createQuery(hql, Job.class).setParameter("kw", s).getResultList();
            //List<Job> result = session.createQuery(hql).setParameter("kw", s).getResultList();
            //return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Job> getJobListByCreator(String userId) {
        List<Job> jobs = null;
        try {
            Session session = sessionFactory.openSession();
            User user = session.get(User.class, userId);
            jobs = new ArrayList<>(user.getJobs());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jobs;
    }

    public List<Job> getJobListByApplicant(String userId) {
        List<Job> jobs = null;
        try {
            Session session = sessionFactory.openSession();
            User user = session.get(User.class, userId);
            List<JobApplication> applications = new ArrayList<>(user.getJobApplications());
            jobs = new ArrayList<>();
            for (JobApplication application : applications) {
                jobs.add(application.getJob());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jobs;
    }

    public Job getJob(String job_id) {
        Session session = null;
        Job job = null;
        try {
            session = sessionFactory.openSession();
            job = session.get(Job.class, job_id);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return job;
    }
}
