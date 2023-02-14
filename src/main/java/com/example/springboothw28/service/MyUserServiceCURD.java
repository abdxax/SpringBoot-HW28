package com.example.springboothw28.service;

import com.example.springboothw28.handling.ApiException;
import com.example.springboothw28.model.MyUser;
import com.example.springboothw28.repstory.MyUserRepstory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserServiceCURD {
    private final MyUserRepstory myUserRepstory;
    public void register(MyUser user){
        String password=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        myUserRepstory.save(user);
    }

    public MyUser getUser(Integer id){
        MyUser user=myUserRepstory.findByIdEquals(id);
        if(user==null){
            throw new ApiException("The id not fount");
        }
        return user;
    }


}
