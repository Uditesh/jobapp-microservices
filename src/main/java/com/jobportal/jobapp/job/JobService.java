package com.jobportal.jobapp.job;

import java.util.List;

public interface JobService {

    List<Job> findAllJobs();
    void createJob(Job job);
    Job getJobById(Long id);

    Boolean deleteJobById(Long id);

    Boolean updateJobById(Long id, Job job);
}
