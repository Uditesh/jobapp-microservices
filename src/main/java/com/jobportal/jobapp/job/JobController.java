package com.jobportal.jobapp.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    // OR constructor injection
    /*public JobController(JobService jobService) {
        this.jobService = jobService;
    }*/

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        // Two best ways to use ResponseEntity class
        // 1) use method of class(ex. ok) and pass parameter(response)
        return ResponseEntity.ok(jobService.findAllJobs());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJobs(@RequestBody Job job){
        jobService.createJob(job);
        // 2) create new object of ResponseEntity class with parameters
        return new ResponseEntity<>("Job added successfully!", HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> findJobById(@PathVariable Long id ){
         Job job = jobService.getJobById(id);
         if(job != null){
             return new ResponseEntity<>(job, HttpStatus.OK);
         }
        return new ResponseEntity<>("No Job found with id: "+id,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){

        Boolean isDeleted = jobService.deleteJobById(id);
        if(isDeleted){
            return new ResponseEntity<>("Job Deleted by id: "+id,
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Job found with id: "+id,
                    HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJobById(@RequestBody Job job, @PathVariable Long id){
        Boolean isUpdated = jobService.updateJobById(id, job);
        if(isUpdated){
            return new ResponseEntity<>("Job with id: "+id+" updated successfully.",
                            HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No job found with id: "+id,
                    HttpStatus.NOT_FOUND);
        }

    }
}
