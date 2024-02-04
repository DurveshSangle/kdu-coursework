package com.kdu.smarthome.services;
import com.kdu.smarthome.entities.Users;
import com.kdu.smarthome.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServices(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves a user in the repository after encoding the password.
     *
     * @param user The user to be saved.
     */
    public void saveUserInRepo(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }
}
