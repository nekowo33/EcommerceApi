package com.ws101.novio.EcommerceApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    private String username;
    
    private String password;
    
    // Adding a role field so we can differentiate between ADMIN and standard users
    private String role; 

    // Constructors
    public User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // --- UserDetails Interface Methods ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security expects roles to be prefixed with "ROLE_"
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Returns true by default for active accounts
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Returns true by default for active accounts
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Returns true by default for active accounts
    }

    @Override
    public boolean isEnabled() {
        return true; // Returns true by default for active accounts
    }
}
