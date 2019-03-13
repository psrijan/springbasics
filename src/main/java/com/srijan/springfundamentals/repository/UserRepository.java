package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query("select u from ApplicationUser u where username=:username")
    public Optional<ApplicationUser> findByUsername(@Param("username") String username);


    @Query("select u from ApplicationUser u where u.role = :role")
    public Optional<List<ApplicationUser>> findByRole(@Param("role") String role);


}
