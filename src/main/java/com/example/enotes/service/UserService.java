package com.example.enotes.service;

import com.example.enotes.entity.User;

public interface UserService {
    
    public User saveUser(User user);
    public boolean existEmailCheck(String email);
}
