package com.example.springsecurity.service;

import com.example.springsecurity.model.Person;
import com.example.springsecurity.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepo personRepo;
    @Autowired
    PersonService(PersonRepo personRepo){
        this.personRepo = personRepo;
    }
    public void addPerson(Person person){
        personRepo.addPerson(person);
    }
    public String getRoleById(int id){
        return personRepo.getRoleByPersonIdx(id);
    }
    public Person getPersonUsername(String name){
        for(Person p : personRepo.getAllPersons()){
            if(p.getUsername().equals(name)){
                return p;
            }
        }
        return null;
    }
}
