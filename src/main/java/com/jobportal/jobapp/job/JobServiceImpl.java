package com.jobportal.jobapp.job;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{

    // we used List before using h2 DB to store persistent data.
    // private List<Job> jobs = new ArrayList<>();

    JobRepository jobRepository;

    // autowire at runtime
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private Long nextId = 1L;

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();

        // return jobs;
    }

    @Override
    public void createJob(Job job) {
        // if we pass id in json, it will be overridden by this logic
        // auto increment by 1
        job.setId(nextId++);
        jobRepository.save(job);

        //jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        /*for(Job job : jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;*/
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteJobById(Long id){
        /*for(Job job: jobs){
            if(job.getId().equals(id)){
                jobs.remove(job);
                return true;
            }
        }
        return false;*/

        // Or use Iterator class to use its method and tp loop over collection
        /*Iterator<Job> iterator = jobs.iterator();
        while(iterator.hasNext()){
            Job job = iterator.next();
            if(job.getId().equals(id)){
                iterator.remove();
                return true;
            }
        }*/

        // If job is not found, use try catch
        try{
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public Boolean updateJobById(Long id, Job job) {
        /*for(Job jobObj : jobs){
            if(jobObj.getId().equals(id)){
                jobObj.setTitle(job.getTitle());
                jobObj.setDescription(job.getDescription());
                jobObj.setLocation(job.getLocation());
                jobObj.setMinSalary(job.getMinSalary());
                jobObj.setMaxSalary(job.getMaxSalary());
                return true;
            }
        }
        return false;*/

        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job newJob = jobOptional.get();
            newJob.setTitle(job.getTitle());
            newJob.setDescription(job.getDescription());
            newJob.setLocation(job.getLocation());
            newJob.setMinSalary(job.getMinSalary());
            newJob.setMaxSalary(job.getMaxSalary());
            return true;
        }
        return false;
    }

}
