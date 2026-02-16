package org.example.filters;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.beans.AllowedRoles;
import org.example.entities.UserPassRole;

import java.io.IOException;

public class RoleBasedAuthFilter implements Filter {

    @Inject
    private AllowedRoles allowedRoles;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("\n\tRoleBasedAuthFilter.doFilter allowedRoles="+allowedRoles);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        // luam principalul (care contine user, parola, lista de roluri) din sesiune
        UserPassRole userPassRole = (UserPassRole) session.getAttribute("principal");
        String url = req.getRequestURI();

        // cautam url-ul de mai sus in allowedRoles, daca nu exista, se trece mai departe(nu e nevoie de autentificare)
        // daca url-ul exista in lista allowed roles, verificam ca pentru userul din principal, lista de roluri este
        // identica cu lista de roluri pentru url-ul respectiv de la allowedRoles

        if (allowedRoles.hasPermission(userPassRole, url)) {
            chain.doFilter(request, response); // User has permission
        } else {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}