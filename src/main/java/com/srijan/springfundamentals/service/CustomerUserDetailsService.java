package com.srijan.springfundamentals.service;

import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username : {} " , username);
        try {
            ApplicationUser user = loadApplicationUserByUsername(username);
            return User.withDefaultPasswordEncoder().username(user.getUsername()).password(user.getPassword()).roles(user.getRole()).build();
        } catch (Exception ex ) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This is used to load application user for login purpose
     * @param username username of the user trying to login
     * @return returns the applicationuser entity
     */
    public ApplicationUser loadApplicationUserByUsername( String username ){
        return userRepository.findByUsername(username).get();
    }
}
