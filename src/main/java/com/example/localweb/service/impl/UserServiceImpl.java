package com.example.localweb.service.impl;
        import com.example.localweb.entity.User;
        import com.example.localweb.service.UserService;
        import org.springframework.stereotype.Service;

        import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    public User getUserInfo(){
        User user = new User();
        user.setName("jack");
        user.setPassword(12341234);
        return user;
    }
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