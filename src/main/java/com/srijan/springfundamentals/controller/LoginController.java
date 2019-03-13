package com.srijan.springfundamentals.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/signin")
public class LoginController {

    @GetMapping()
    public String getLogin() {
        return "Inside Login Controller";
    }

    /**
     * logout page is responsible to logout the page
     * @param request httprequest object that will contain the who http request
     * @param response http response object that will contain response from the api
     * @return returns a string.
     */
    @RequestMapping(value="/logmeout", method = RequestMethod.POST)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
