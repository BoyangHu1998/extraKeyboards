package com.ek.extrakeyboards.dao;

import com.ek.extrakeyboards.entity.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
