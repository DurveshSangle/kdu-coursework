package com.kdu.smarthome.config;


import com.kdu.smarthome.entities.Users;
import com.kdu.smarthome.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonUserDetails implements UserDetailsService {
    private UsersRepository usersRepo;
    @Autowired
    public PersonUserDetails(UsersRepository usersRepo) {
        this.usersRepo = usersRepo;
    }

    /**
     * Loads user details by username.
     *
     * @param username The username to load user details for.
     * @return UserDetails object containing user details.
     * @throws UsernameNotFoundException If the user details are not found for the specified username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users person = usersRepo.findByUsername(username);
        String personUserName = null;
        String personPassword = null;
        List<GrantedAuthority> authorities = null;

        if(person == null){
            throw new UsernameNotFoundException("User details not found for user : " + username + ". Please register fist.");
        }else{
            personUserName = person.getUsername();
            personPassword = person.getPassword();
            authorities = new ArrayList<>();
        }
        return new User(personUserName, personPassword, authorities);
    }
}
