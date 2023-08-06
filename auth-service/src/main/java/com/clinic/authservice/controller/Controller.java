package com.clinic.authservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinic.authservice.dto.RequestDto;
import com.clinic.authservice.dto.TokenDto;
import com.clinic.authservice.dto.UserAuthDto;
import com.clinic.authservice.entity.UserAuth;
import com.clinic.authservice.service.UserAuthService;

@RestController
@RequestMapping(value = "/auth")
@Log4j2
public class Controller {
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/loging")
    public ResponseEntity<TokenDto> login(@RequestBody UserAuthDto authDto){
        TokenDto tokenDto = userAuthService.login(authDto);
        if(tokenDto == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto){
        log.info("Entra a validar token "+requestDto.toString());
        TokenDto tokenDto = userAuthService.validateToken(token, requestDto);
        if(tokenDto == null){
            return ResponseEntity.badRequest().build();
        }
        log.info(tokenDto.getToken());
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserAuth user){
        UserAuth userAuth = userAuthService.createUser(user);
        if(userAuth == null){
            return new ResponseEntity<>("This user already exist", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userAuth);

    }

    @GetMapping("/${username}")
    public  ResponseEntity<?> findUSerByName(@PathVariable("username")String userName){
        try {
            return ResponseEntity.ok(userAuthService.findUserAut(userName));
        }catch(Exception ex){
            log.error("User not found");
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
