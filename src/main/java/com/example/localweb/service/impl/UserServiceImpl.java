package com.example.localweb.service.impl;
import com.example.localweb.entity.User;
import com.example.localweb.service.UserService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    public int callClient() throws IOException, InterruptedException {
        User user = new User();
        int status = user.runClient();
        return status;
    }
    public int callUser() throws IOException, InterruptedException {
        User user = new User();
        int status = user.runUser();
        return status;
    }
}