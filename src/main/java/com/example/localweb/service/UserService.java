package com.example.localweb.service;
import com.example.localweb.entity.User;

import java.io.IOException;

public interface UserService{
    public int callClient() throws IOException, InterruptedException;
    public int callUser() throws IOException, InterruptedException;


}