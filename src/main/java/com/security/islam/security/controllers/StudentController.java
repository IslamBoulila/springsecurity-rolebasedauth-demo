package com.security.islam.security.controllers;

import com.security.islam.security.entities.Student;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    List<Student> students= Arrays.asList(new Student(1, "Islam"), new Student(2, "Mohammed"))  ;

   // @PreAuthorize("hasAuthority('student::read')")
    @Secured("['ROLE_ADMIN','ROLE_STUDENT']")
    @GetMapping("/all")
    List<Student> getAll(){
        return students;
    }

    /*@PreAuthorize("hasAuthority('student::read')")
    @PostMapping("/new")
    Student create(){

        return students;
    }*/

    @GetMapping("/{studentId}")
    Student getOne(@PathVariable("studentId") Integer studentId ){
       return  students.stream().filter(s->s.getId()== studentId).findFirst().get();
    }

}
