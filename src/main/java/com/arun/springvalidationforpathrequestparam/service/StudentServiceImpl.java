package com.arun.springvalidationforpathrequestparam.service;

import com.arun.springvalidationforpathrequestparam.dao.StudentDao;
import com.arun.springvalidationforpathrequestparam.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student createStudentDetail(Student student) {
        return studentDao.createStudentDetail(student);
    }
}
