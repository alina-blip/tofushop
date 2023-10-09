package com.example.webshopbackend.controller;
import org.springframework.security.core.GrantedAuthority;

import com.example.webshopbackend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HelloController {


    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal){

        List<String> authorities = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return "if you see this, then you logged in as " +authorities.get(0)+ " with the Mail: " +principal.getEmail()
                + " User Id: " + principal.getUserId();
    }


    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal){
        return "if you see this, then you logged in as " +principal.getEmail()
                + " User Id: " + principal.getUserId();
    }
}
