package com.srijan.springfundamentals.service;

import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public boolean saveUserData(ApplicationUser user) {
        try {

            userRepository.save(user);
            return true;
        } catch (Exception ex ) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateUserData(ApplicationUser user) {

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

    public boolean deleteUserData(Long id) {

        try {
            userRepository.delete(new ApplicationUser(id));
            return true;
        } catch (Exception ex ) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<ApplicationUser> listUserData() {

        try {
            return userRepository.findByRole("USER").get();
        } catch (Exception ex ) {
            ex.printStackTrace();
            return null;
        }
    }



}
