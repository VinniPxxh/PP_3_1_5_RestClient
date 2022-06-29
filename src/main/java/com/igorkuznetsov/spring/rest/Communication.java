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
    private HttpHeaders headers = new HttpHeaders();

    private final String URL = "http://94.198.50.185:7081/api/users";

    private StringBuilder code = new StringBuilder();

    public StringBuilder getCode() {
        return code;
    }

    public List<User> getAllUsers() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {
                });
        responseEntity.getHeaders().get("Set-Cookie");
        String set_cookie = headers.getFirst(headers.SET_COOKIE);
        System.out.println("Response: " + responseEntity + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        List<User> allUsers = responseEntity.getBody();
        return allUsers;
    }

    public User getUser(long id) {
        User user = restTemplate.getForObject(URL + "/" + id, User.class);
        return user;
    }

    public void addUser(User user) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        responseEntity.getHeaders();
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        code.append(responseEntity.getBody());
    }

    public void updateUser(User user) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        responseEntity.getHeaders();
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        code.append(responseEntity.getBody());
    }

    public void deleteUser(User user, long id) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id,
                HttpMethod.DELETE, entity, String.class);
        responseEntity.getHeaders();
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        code.append(responseEntity.getBody());
    }
}
