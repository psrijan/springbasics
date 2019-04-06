package com.srijan.springfundamentals.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name="API_HITS" , length = 50 , nullable = false)
    private Long apiHits;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ADMIN") , new SimpleGrantedAuthority("USER"));
    }

    public ApplicationUser() {
    }

    public ApplicationUser(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
        return getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }

    public String getName () {
        return getFirstName() + " " + getLastName();
    }
}
