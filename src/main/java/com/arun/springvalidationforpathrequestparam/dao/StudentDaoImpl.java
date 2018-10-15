package com.arun.springvalidationforpathrequestparam.dao;

import com.arun.springvalidationforpathrequestparam.model.Student;
import com.arun.springvalidationforpathrequestparam.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudentDetail(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentBasedOnId(Long id) {
        return studentRepository.findById(id).get();
    }
}
