package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query("select u from ApplicationUser u where username=:username")
    Optional<ApplicationUser> findByUsername(@Param("username") String username);

}
