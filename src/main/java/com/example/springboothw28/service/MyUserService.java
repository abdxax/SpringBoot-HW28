package com.example.springboothw28.service;

import com.example.springboothw28.model.MyUser;
import com.example.springboothw28.repstory.MyUserRepstory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {
    private final MyUserRepstory myUserRepstory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user=myUserRepstory.findMyUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("the user name or password is not correct ");
        }

        return user;
    }


}
