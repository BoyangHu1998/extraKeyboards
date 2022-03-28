package com.ek.extrakeyboards.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "job")
public class Job implements Serializable {
    private static final long serialVersionUID = 8734140534986494039L;

    @Id
    @Column(name="job_id")
    @JsonProperty("job_id")
    private String jobId;

    @Column(name="job_title")
    @JsonProperty("job_title")
    private String jobTitle;

    @Column(name="job_description")
    @JsonProperty("job_description")
    private String jobDescription;

    private String category;

    private String country;

    @ManyToOne
    private User user; // this job created by this user

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<JobApplication> jobApplications;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
