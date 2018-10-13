package com.arun.springvalidationforpathrequestparam.repository;

import com.arun.springvalidationforpathrequestparam.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
