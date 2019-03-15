package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Add User has the responsibility add a new user in the database
     * @param user - is the dto that is saved in the database
     * @return
     */
    @PostMapping
    private ResponseEntity<?> addUsers (@RequestBody ApplicationUser user ) {
        log.debug("Add User Controller...");
        try {
            boolean addSuccess = userService.saveUserData(user);
            return new ResponseEntity<>(addSuccess , HttpStatus.CREATED);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update User is used to update existing user
     * @param user - ApplicationUser that is used to update an existing user
     * @return
     */
    @PutMapping
    private ResponseEntity<?> updateUsers(@RequestBody ApplicationUser user) {
        log.debug("Update User Controller...");
        try {
            boolean updateSuccess = userService.updateUserData(user);
            return new ResponseEntity<>(updateSuccess , HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete user is the API that is used to delete an existing user through
     * an id
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
    private ResponseEntity<?> deleteUsers(@PathVariable("id") Long id) {
        log.debug("Delete User Controller...");
        try {
            boolean deleteSuccess = userService.deleteUserData(id);
            return new ResponseEntity<Boolean>(deleteSuccess , HttpStatus.OK);
        } catch (Exception ex ) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * List all users is used to list the users that is
     * available in the ApplicationUser
     * @return
     */
    @GetMapping
    private ResponseEntity<?> listAllUsers() {
        log.debug("All User List Controller...");
        try {
            List<ApplicationUser> userList = userService.listUserData();
            return new ResponseEntity<List<ApplicationUser>>(userList , HttpStatus.OK);
        } catch (Exception ex ) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path ="/{id}")
    private ApplicationUser getUsers(@PathVariable Long id) {
        log.debug("Get Particular User Controller...");
        return userService.getUser(id);
    }
}
