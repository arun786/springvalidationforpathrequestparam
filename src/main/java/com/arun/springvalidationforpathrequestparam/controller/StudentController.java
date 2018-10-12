package com.arun.springvalidationforpathrequestparam.controller;

import com.arun.springvalidationforpathrequestparam.model.Student;
import com.arun.springvalidationforpathrequestparam.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class StudentController {

    private StudentValidator studentValidator;

    @Autowired
    public StudentController(StudentValidator studentValidator) {
        this.studentValidator = studentValidator;
    }

    @PutMapping("/students/v1/student/{id}")
    public ResponseEntity<Student> upsertStudentDetails(@PathVariable(value = "id") Long studentId, @RequestBody Student student) {
        student.setStudentId(studentId);
        Errors errors = new BeanPropertyBindingResult(student, "student");
        studentValidator.validate(student, errors);
        List<ObjectError> allErrors = errors.getAllErrors();
        if(!allErrors.isEmpty()){

        }
        return null;
    }

}