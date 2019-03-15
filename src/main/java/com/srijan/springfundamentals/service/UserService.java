package com.srijan.springfundamentals.service;

import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Cacheable(value = "ch1" , keyGenerator = "customKeyGenerator")
    public ApplicationUser getUser(Long id) {
        log.debug("User Service Get User...");
        try {
            return userRepository.findById(id).get();
        } catch (Exception ex ) {
            ex.printStackTrace();
            return null;
        }
    }

    @Transactional
    public boolean saveUserData(ApplicationUser user) {
        log.debug("Save User Data Service...");
        try {

            userRepository.save(user);
            return true;
        } catch (Exception ex ) {
            ex.printStackTrace();
            return false;
        }
    }

    @CacheEvict(keyGenerator = "customKeyGenerator")
    public boolean updateUserData(ApplicationUser user) {
        log.debug("Update User Data Service...");
        try {
            ApplicationUser savedUser = userRepository.findById(user.getId()).get();
            modelMapper.map(user, savedUser);
            userRepository.save(user);
            return true;
        } catch (Exception ex ) {
            ex.printStackTrace();
            return false;
        }
    }

    @CacheEvict(keyGenerator = "customKeyGenerator" ,value = "ch1")
    public boolean deleteUserData(Long id) {
        log.debug("Delete User Data Service...");
        try {
            userRepository.delete(new ApplicationUser(id));
            return true;
        } catch (Exception ex ) {
            ex.printStackTrace();
            return false;
        }
    }


    public List<ApplicationUser> listUserData() {
        log.debug("List User Data Service...");
        try {
            return userRepository.findByRole("USER").get();
        } catch (Exception ex ) {
            ex.printStackTrace();
            return null;
        }
    }



}
