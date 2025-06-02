package com.jobportal.jobapp.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// or we can extend CrudRepository -> provides crud functions
// useful for basic crud operations
// JpaRepository extends the functionality of CrudRepository and it adds JPA specific methods too.

// Spring data JPA will automatically generate implementation at the runtime(for methods)
public interface JobRepository extends JpaRepository<Job,Long> {
}
