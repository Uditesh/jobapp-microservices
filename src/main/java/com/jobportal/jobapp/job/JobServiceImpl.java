package com.jobportal.jobapp.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService{
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAllJobs() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        // if we pass id in json, it will be overridden by this logic
        // auto increment by 1
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for(Job job : jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteJobById(Long id){
        for(Job job: jobs){
            if(job.getId().equals(id)){
                jobs.remove(job);
                return true;
            }
        }

        // Or use Iterator class to use its method and tp loop over collection
        /*Iterator<Job> iterator = jobs.iterator();
        while(iterator.hasNext()){
            Job job = iterator.next();
            if(job.getId().equals(id)){
                iterator.remove();
                return true;
            }
        }*/
        return false;
    }

    @Override
    public Boolean updateJobById(Long id, Job job) {
        for(Job jobObj : jobs){
            if(jobObj.getId().equals(id)){
                jobObj.setTitle(job.getTitle());
                jobObj.setDescription(job.getDescription());
                jobObj.setLocation(job.getLocation());
                jobObj.setMinSalary(job.getMinSalary());
                jobObj.setMaxSalary(job.getMaxSalary());
                return true;
            }
        }
        return false;
    }

}
