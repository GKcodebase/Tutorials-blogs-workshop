package com.basicauth.api.v1.login;

import org.springframework.stereotype.Component;

/**
 * The Login api.
 */
@Component
public class LoginApiImpl implements LoginApi{

    /**
     * User login string.
     *
     * @return the string
     */
    @Override
    public String userLogin() {
        return "Login Successful";
    }
}
