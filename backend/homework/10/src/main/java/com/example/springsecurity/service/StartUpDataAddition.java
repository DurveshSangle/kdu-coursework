package com.example.springsecurity.service;

import com.example.springsecurity.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartUpDataAddition implements CommandLineRunner {
    PersonService personService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public StartUpDataAddition(PersonService personService, PasswordEncoder passwordEncoder){
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }
    /** Function to insert Persons with respective roles at the startup */
    @Override
    public void run(String... args) throws Exception {
        personService.addPerson(new Person("Durvesh", "durvesh", passwordEncoder.encode("test@123"), "ROLE_ADMIN"));
        personService.addPerson(new Person("Alice", "alice", passwordEncoder.encode("test@123"), "ROLE_BASIC"));
    }
}
