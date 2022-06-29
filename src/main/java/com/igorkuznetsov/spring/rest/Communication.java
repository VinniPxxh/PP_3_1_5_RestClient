package com.igorkuznetsov.spring.rest;


import com.igorkuznetsov.spring.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://94.198.50.185:7081/api/users";

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        responseEntity.getHeaders().get("Set-Cookie");
        String set_cookie = headers.getFirst(headers.SET_COOKIE);
        System.out.println("Response: " + responseEntity.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        List<User> allUsers = responseEntity.getBody();
        return allUsers;
//        [Set-Cookie:"JSESSIONID=E672E9FF44B42771CF68D42FCE7B2594;
    }

    public User getUser(long id) {
        User user = restTemplate.getForObject(URL + "/" + id, User.class);
        return user;
    }

    public String addUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);
        return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();

    }

    public void updateUser(User user) {
    }
}
