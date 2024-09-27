package com.example.springsecurity.repositories;


import com.example.springsecurity.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Data
@AllArgsConstructor
public class UserRepoImpl{
    private final List<User> userList;
    public void addUserToList(User user){
        userList.add(user);
    }
    /**
     * Function to get User with username from repository
     * @param userName of the user to be found
     * @return User object with username
     * */
    public User getUserByName(String userName){
        Optional<User> o = userList.stream().filter(u->u.getUsername().equals(userName)).findFirst();
        if(o.isEmpty()) return null;
        return o.get();
    }
}
