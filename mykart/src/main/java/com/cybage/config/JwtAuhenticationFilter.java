package com.cybage.config;

import com.cybage.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuhenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. read token from Auth head
        String requestToken = request.getHeader("Authorization");

        String username = null;
        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);
            //do validation
            username = jwtUtils.getUsername(token);

            //username should not be empty, context-auth must be empty
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                UserDetails user = userDetailsService.loadUserByUsername(username);

                //validate token
                boolean isValid = jwtUtils.validateToken(token, user.getUsername()/*database username*/);


                if (isValid) {
                    //Authentication object will be created
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //final object stored in SecurityContext with user(username,password)
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

            }

        }
        filterChain.doFilter(request, response);
    }
}
