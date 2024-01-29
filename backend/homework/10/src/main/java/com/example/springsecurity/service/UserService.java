package com.example.springsecurity.service;

import com.example.springsecurity.exception.custom.NoSuchUSerException;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repositories.UserRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepoImpl userRepo;
    @Autowired
    UserService(UserRepoImpl userRepo){
        this.userRepo = userRepo;
    }
    public List<User> getUsersListFromRepo() {
        return userRepo.getUserList();
    }
    public void addUserToRepo(User user) {
        userRepo.addUserToList(user);
    }

    /**
     * Function to find user by his username
     * @param userName of the user to be found
     *
     * @return User object with that username
     * */
    public User getUserByName(String userName) throws NoSuchUSerException{
        User u = userRepo.getUserByName(userName);
        if(u==null) {
            log.error("No such user exist!!!");
            throw new NoSuchUSerException("No such user Exists");
        }
        log.info("User with name "+userName+" found successfully.");
        return u;
    }
}
