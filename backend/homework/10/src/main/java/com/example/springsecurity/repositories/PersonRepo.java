package com.example.springsecurity.repositories;

import com.example.springsecurity.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepo {
    private final List<Person> personList;

    public PersonRepo() {
        this.personList = new ArrayList<>();
    }

    public void addPerson(Person person){
        personList.add(person);
    }
    public String getRoleByPersonIdx(int index){
        return personList.get(index).getRole();
    }

    public List<Person> getAllPersons(){
        return personList;
    }
}
