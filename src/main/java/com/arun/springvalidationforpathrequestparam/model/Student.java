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
