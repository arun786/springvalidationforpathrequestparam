# How to insert records in a single table which is mapped to a pojo using Spring Repository

Step 1 : create an interface which extends CrudRepository

    package com.arun.springvalidationforpathrequestparam.repository;
    
    import com.arun.springvalidationforpathrequestparam.model.Student;
    import org.springframework.data.repository.CrudRepository;
    
    public interface StudentRepository extends CrudRepository<Student, Long> {
    }

Step 2 : autowire the above interface in dao layer and call the save method.

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
    }

Step 3: Create a model class as below

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
    import javax.validation.constraints.NotEmpty;
    import javax.validation.constraints.PastOrPresent;
    import javax.validation.constraints.Pattern;
    import java.time.LocalDate;
    
    @Getter
    @Setter
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    public class Student {
        @JsonIgnore
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long studentId;
        @NotEmpty(message = "Name cannot be empty")
        private String name;
        @Pattern(regexp = "[0-9]{1,3}", message = "age can be only numbers")
        private String age;
        @PastOrPresent(message = "DOB can only be past date")
        private LocalDate dob;
        @PastOrPresent(message = "DOA can only be past date")
        private LocalDate doa;
    }

# To get Single POJO based on Id

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
