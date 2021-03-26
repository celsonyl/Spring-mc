package com.celso.springmc.resources;

import com.celso.springmc.domain.dto.EmailDTO;
import com.celso.springmc.security.JWTUtil;
import com.celso.springmc.security.UserSS;
import com.celso.springmc.services.AuthService;
import com.celso.springmc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/refresh_token",method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS userSS = UserService.authenticated();
        String token = jwtUtil.generateToken(userSS.getUsername());
        response.addHeader("Authorization ", "Bearer "+token);
        response.addHeader("access-control-expose-headers","Authorization");


        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot",method = RequestMethod.POST)
    public ResponseEntity<Void> forgotPass(@Valid @RequestBody EmailDTO emailDTO){
        authService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }
}
