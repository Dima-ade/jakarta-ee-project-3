package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entities.UserPassRole;

import java.io.IOException;
import java.util.Base64;
import java.util.Set;

@WebFilter("/api") // Filter applies to /api/hello/ endpoints
public class BasicAuthFilter implements Filter {

    public BasicAuthFilter() {
        System.out.println("BasicAuthFilter.BasicAuthFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Extract credentials
            String base64Credentials = authHeader.substring("Basic ".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] values = credentials.split(":", 2);
            String username = values[0];
            String password = values[1];

            // Validate user (Example: Hardcoded check)
            if ("user".equals(username) && "password".equals(password)) {

                HttpSession session = ((HttpServletRequest) request).getSession(true);

                UserPassRole principal = new UserPassRole(username, password, Set.of("USER"));

                session.setAttribute("principal", principal);

                 // User authorized, continue
                chain.doFilter(request, response);
                return;
            }
        }

        // Authentication failed or not provided
        httpResponse.setHeader("WWW-Authenticate", "Basic realm=\"Secure Area\"");
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}