package org.example.entities;

import java.security.Principal;
import java.util.Set;

public class UserPassRole implements Principal {

    private String username;

    private String password;

    private Set<String> allowedRoles;

    public UserPassRole(String username, String password, Set<String> allowedRoles) {
        this.username = username;
        this.password = password;
        this.allowedRoles = allowedRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getAllowedRoles() {
        return allowedRoles;
    }

    public void setAllowedRoles(Set<String> allowedRoles) {
        this.allowedRoles = allowedRoles;
    }

    @Override
    public String getName() {
        return username;
    }
}
