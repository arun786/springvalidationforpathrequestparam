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
    @Pattern(regexp = "[0-9][0-9]", message = "age can be only numbers")
    private String age;
    @PastOrPresent(message = "DOB can only be past date")
    private LocalDate dob;
    @PastOrPresent(message = "DOA can only be past date")
    private LocalDate doa;
}
