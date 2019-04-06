package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.dto.request.LoginRequest;
import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody LoginRequest user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ApplicationUser applicationUser = modelMapper.map(user , ApplicationUser.class);
        userRepository.save(applicationUser);
        return "Created new user";
    }
}
