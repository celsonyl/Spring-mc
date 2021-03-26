package com.celso.springmc.security;

import com.celso.springmc.domain.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try{
            CredenciaisDTO credenciaisDTO = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credenciaisDTO.getEmail(),credenciaisDTO.getSenha(), new ArrayList<>()
            );

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return authentication;

        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,FilterChain chain, Authentication authentication) throws IOException, ServletException{

        String username = ((UserSS) authentication.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authorization","Bearer " + token);
        response.addHeader("access-control-expose-headers","Authorization");


    }

}
