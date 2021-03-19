package com.celso.springmc.services;

import com.celso.springmc.domain.Cliente;
import com.celso.springmc.repositories.ClienteRepository;
import com.celso.springmc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente c1 = clienteRepository.findByEmail(email);
        if(c1 == null){
            throw new UsernameNotFoundException("Email não existe! "+email);
        }
        return new UserSS(c1.getId(),c1.getEmail(),c1.getSenha(),c1.getPerfis());
    }
}
