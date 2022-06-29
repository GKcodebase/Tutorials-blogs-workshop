package com.basicauth.api.v1.login;

import com.basicauth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginApiImpl implements LoginApi{

    @Autowired
    private LoginService loginService;
    @Override
    public String userLogin() {
        return "Login Successful";
    }
}
