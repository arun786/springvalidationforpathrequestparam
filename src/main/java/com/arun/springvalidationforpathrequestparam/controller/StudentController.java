package com.arun.springvalidationforpathrequestparam.controller;

import com.arun.springvalidationforpathrequestparam.exception.ArunRunTimeException;
import com.arun.springvalidationforpathrequestparam.model.Student;
import com.arun.springvalidationforpathrequestparam.service.StudentService;
import com.arun.springvalidationforpathrequestparam.validator.StudentAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class StudentController {

    private StudentAdaptor studentAdaptor;
    private StudentService studentService;

    @Autowired
    public StudentController(StudentAdaptor studentAdaptor, StudentService studentService) {
        this.studentAdaptor = studentAdaptor;
        this.studentService = studentService;
    }

    @PutMapping("/students/v1/student/")
    public ResponseEntity<Student> createStudentDetail(@RequestBody Student student) {
        Errors errors = new BeanPropertyBindingResult(student, "student");
        studentAdaptor.validate(student, errors);
        if (errors.hasErrors()) {
            throw new ArunRunTimeException("INVALID_INPUT", errors);
        }
        Student studentDetail = studentService.createStudentDetail(student);
        return new ResponseEntity<>(studentDetail, HttpStatus.OK);
    }

    @GetMapping("/students/v1/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long id) {
        Student studentBasedOnId = studentService.getStudentBasedOnId(id);
        return new ResponseEntity<>(studentBasedOnId, HttpStatus.OK);
    }

}
