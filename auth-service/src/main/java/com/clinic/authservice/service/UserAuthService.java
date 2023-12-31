package com.clinic.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.clinic.authservice.dto.RequestDto;
import com.clinic.authservice.dto.TokenDto;
import com.clinic.authservice.dto.UserAuthDto;
import com.clinic.authservice.entity.UserAuth;
import com.clinic.authservice.repository.UserAuthRepository;
import com.clinic.authservice.security.JwtProvider;
import com.clinic.authservice.security.PasswordEncoderConfig;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserAuthService {
    @Autowired
    UserAuthRepository authRepository;

    @Autowired
    PasswordEncoderConfig passwordEncoderConfig;
    @Autowired
    JwtProvider jwtProvider;

    public UserAuth createUser(UserAuth userAuth){
        Optional<UserAuth> user = authRepository.findByUserName(userAuth.getUserName());
        if(user.isPresent()) {
            return null;
        }
        String password = passwordEncoderConfig.passwordEncoder().encode(userAuth.getPassword());
        userAuth.setPassword(password);
        return authRepository.save(userAuth);
    }

    public TokenDto login (UserAuthDto authDto){
        Optional<UserAuth> user = authRepository.findByUserName(authDto.getUserName());
        if(!user.isPresent()){
            return null;
        }
        if(passwordEncoderConfig.passwordEncoder().matches(authDto.getPassword(), user.get().getPassword())){
            return new TokenDto(jwtProvider.creteToken(user.get()));
        }
        return null;
    }

    public TokenDto validateToken(String token, RequestDto requestDto){
        if(!jwtProvider.validete(token,requestDto)){
            return null;
        }
        String userName = jwtProvider.getUserNameFromToken(token);
        if(!authRepository.findByUserName(userName).isPresent()){
            return null;
        }
        return new TokenDto(token);
    }

    public UserAuth findUserAut (String userName) throws Exception{
        return authRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("User not found with name :" + userName));
    }
}
