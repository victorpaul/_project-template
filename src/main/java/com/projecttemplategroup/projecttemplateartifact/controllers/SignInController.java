package com.projecttemplategroup.projecttemplateartifact.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//todo move to scala
@RestController
public class SignInController extends SecurityController{

    @GetMapping("/v1/signin")
    public String signIn(HttpServletRequest request, HttpServletResponse response) {
        return getSignedInProfile(request,response).toString();
    }
}
