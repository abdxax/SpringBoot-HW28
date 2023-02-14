package com.example.springboothw28.controller;

import com.example.springboothw28.model.MyUser;
import com.example.springboothw28.service.MyUserService;
import com.example.springboothw28.service.MyUserServiceCURD;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MyUserServiceCURD myUserService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser user){
        myUserService.register(user);
        return  ResponseEntity.status(200).body("Refister is done");
    }
    @PostMapping("/login")
    public ResponseEntity login(@AuthenticationPrincipal MyUser user){

        return  ResponseEntity.status(200).body("Welcome "+user.getUsername());
    }
    @PostMapping("/logout")
    public ResponseEntity logout(){

        return  ResponseEntity.status(200).body("Good bye! ");
    }
}
