package com.kdu.smarthome.config;

import com.kdu.smarthome.entities.Users;
import com.kdu.smarthome.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomAuthenticationManager implements AuthenticationProvider {
    UsersRepository usersRepo;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public CustomAuthenticationManager(UsersRepository usersRepo, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.usersRepo = usersRepo;
    }

    /**
     * Authenticate the user based on the provided credentials.
     *
     * @param authentication The authentication request object.
     * @return The authenticated user details.
     * @throws AuthenticationException If authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Users person = usersRepo.findByUsername(username);
        log.info("IN authentication manager");
        log.info(pwd);
        log.info(person.getPassword());
        if(person == null){
            throw new BadCredentialsException("No user registered with this details!");
        }else{
            if (passwordEncoder.matches(pwd, person.getPassword())) {
                log.info("Password matched!!!");
                return new UsernamePasswordAuthenticationToken(username, pwd);
            } else {
                log.info("bad credentials");
                throw new BadCredentialsException("Invalid password!");
            }
        }
    }

    /**
     * Checks if this AuthenticationProvider supports the specified authentication class.
     *
     * @param authentication The authentication class to check.
     * @return True if the provider can process the authentication request for the specified class.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    /**
     * Get the granted authorities for the specified role.
     *
     * @param role The role for which authorities are to be granted.
     * @return The list of granted authorities.
     */
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return grantedAuthorities;
    }
}
