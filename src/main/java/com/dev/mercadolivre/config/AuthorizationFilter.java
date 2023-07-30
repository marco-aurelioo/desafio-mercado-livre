package com.dev.mercadolivre.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               UserDetailsService userDetailsService,
                               JWTUtils jwtUtils) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    private JWTUtils jwtUtils;
    private UserDetailsService userDetailsService;

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {

        String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken authentication = getUserAuthentication(requestTokenHeader);
        }

        super.doFilterInternal(request, response, chain);

    }

    private UsernamePasswordAuthenticationToken getUserAuthentication(String requestTokenHeader) throws AuthenticationException {
        if(!jwtUtils.validateToken(requestTokenHeader)){
            throw new RuntimeException("Token inválido");
        }
        Integer  userId = jwtUtils.getSubject(requestTokenHeader);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId.toString());
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
