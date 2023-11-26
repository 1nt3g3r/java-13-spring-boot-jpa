package com.goit.java13.mvc.feature.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrincipalService {
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Set<String> getRoles() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

    public boolean hasRole(String role) {
        return getRoles().contains(role);
    }

}
