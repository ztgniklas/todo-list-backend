package com.taigezhang.todolist.filter;

import com.taigezhang.todolist.model.User;
import com.taigezhang.todolist.repository.UserRepository;
import com.taigezhang.todolist.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String jwt = extractJwt(authHeader);
        if (jwt != null && jwt.length() > 0) {
            String userName = jwtTokenUtils.extractUserName(jwt);
            if (userName != null && userName.length() > 0) {
                User userDetails = userRepository.loadUserByName(userName);
                if (jwtTokenUtils.validateToken(jwt, userDetails)) {

                }
            }
        }
        chain.doFilter(request, response);
    }

    private String extractJwt(String authHeader) {
        return authHeader;
    }
}
