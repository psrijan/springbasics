package com.srijan.springfundamentals.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRST_NAME", length = 50 , nullable = false)
    private String firstName;

    @Column(name="USER_NAME" , length =  50 , nullable = false)
    private String username;

    @Column(name = "LAST_NAME", length = 50 , nullable = false)
    private String lastName;

    @Column(name = "EMAIL", length = 50 , nullable = false)
    private String email;

    @Column(name = "PASSWORD", length = 50 , nullable = false)
    private String password;

    @Column(name="ROLE" , length = 50, nullable = false)
    private String role;

    public ApplicationUser() {
    }

    public ApplicationUser(Long id) {
        this.id = id;
    }
}
