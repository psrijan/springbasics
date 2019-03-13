package com.srijan.springfundamentals.service;

import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public List<ApplicationUser> listAdmins() {
        try {
            Optional<List<ApplicationUser>> admins = userRepository.findByRole("ADMIN");
            return admins.get();
        } catch (NoSuchElementException ex ) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

}
