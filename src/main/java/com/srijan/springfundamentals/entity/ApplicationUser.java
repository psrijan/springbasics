package com.srijan.springfundamentals.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class ApplicationUser implements UserDetails {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", length = 50 )
    private String firstName;

    @Column(name="USER_NAME" , length =  50 )
    private String username;

    @Column(name = "LAST_NAME", length = 50 )
    private String lastName;

    @Column(name = "EMAIL", length = 50 )
    private String email;

    @Column(name = "PASSWORD", length = 200 )
    private String password;

    @JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "authority_id", referencedColumnName = "id") })
    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName().toString())).collect(Collectors.toList());
//        return Arrays.asList(new SimpleGrantedAuthority("ADMIN") , new SimpleGrantedAuthority("USER")); @todo remove later
    }



    public ApplicationUser() {
    }

    public ApplicationUser(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
