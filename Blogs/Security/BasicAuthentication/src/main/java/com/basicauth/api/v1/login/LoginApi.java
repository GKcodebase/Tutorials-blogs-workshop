package com.basicauth.api.v1.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface LoginApi {

    @GetMapping("/user/login")
    @ResponseBody
    String userLogin();
}
