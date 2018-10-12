# springvalidationforpathrequestparam

## Validation of Request Param and Path Param can be done using the below 

## Step 1 

### Create a Validator class as below

    package com.arun.springvalidationforpathrequestparam.validator;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;
    import org.springframework.validation.Errors;
    import org.springframework.validation.ValidationUtils;
    import org.springframework.validation.Validator;
    
    @Component
    public abstract class ValidatorBaseClass implements Validator {
    
        @Autowired
        private Validator validatorBaseClass;
    
        @Override
        public boolean supports(Class<?> aClass) {
            return true;
        }
    
        @Override
        public void validate(Object o, Errors errors) {
    
            ValidationUtils.invokeValidator(validatorBaseClass, o, errors);
            manualValidation(o, errors);
        }
    
        protected abstract void manualValidation(Object o, Errors errors);
    }
 

### Extends the Validtor class, this will be specific to the api

    In this class, we will write the manual validation

    package com.arun.springvalidationforpathrequestparam.validator;
    
    import org.springframework.stereotype.Component;
    import org.springframework.validation.Errors;
    
    @Component
    public class StudentValidator extends ValidatorBaseClass {
        @Override
        protected void manualValidation(Object o, Errors errors) {
    
        }
    }

### call the validator in the controller

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


### Model class will have all the validation

    package com.arun.springvalidationforpathrequestparam.model;
    
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.validation.Valid;
    import javax.validation.constraints.NotEmpty;
    import java.time.LocalDate;
    
    @Getter
    @Setter
    @Entity
    @AllArgsConstructor
    @Valid
    @NoArgsConstructor
    public class Student {
        @JsonIgnore
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long studentId;
        @NotEmpty(message = "Name cannot be empty")
        private String name;
        private String age;
        private LocalDate dob;
        private LocalDate doa;
    }
