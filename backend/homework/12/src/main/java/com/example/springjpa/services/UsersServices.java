package com.example.springjpa.services;

import com.example.springjpa.entities.Users;
import com.example.springjpa.exceptions.custom.NoSuchUserExistException;
import com.example.springjpa.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UsersServices {
    private UsersRepository usersRepository;
    @Autowired
    public UsersServices(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public void saveUser(Users user){
        usersRepository.save(user);
    }
    public Page<Users> findAll(PageRequest pageRequest){
        return usersRepository.findAll(pageRequest);
    }

    public Users findUserById(UUID userId){
        return usersRepository.findById(userId).get();
    }
    public void updateUser(Users user, UUID userId) throws NoSuchUserExistException{
        Users existingUser = findUserById(userId);
        if(existingUser==null){
            log.error("Invalid userId!!");
            throw new NoSuchUserExistException("Invalid User Id");
        }
        existingUser.setUsername(user.getUsername());
        existingUser.setTimeZone(user.getTimeZone());
        existingUser.setTenant(user.getTenant());
        usersRepository.save(existingUser);
        log.info("User updated successfully");
    }
}
