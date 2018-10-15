package com.arun.springvalidationforpathrequestparam.dao;

import com.arun.springvalidationforpathrequestparam.model.Student;

public interface StudentDao {
    Student createStudentDetail(Student student);

    Student getStudentBasedOnId(Long id);
}
