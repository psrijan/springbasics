package com.srijan.springfundamentals.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
public class JwtUser implements UserDetails {
    private @NotNull final ApplicationUser applicationUser;
    private final Collection<? extends GrantedAuthority> authorities;
    private  String token;

    @Override
    public String getPassword() {
        return applicationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return applicationUser.getUsername();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getName () {
        return applicationUser.getFirstName() + " " + applicationUser.getLastName();
    }
}
