package com.igorkuznetsov.spring.rest;

import com.igorkuznetsov.spring.rest.configuration.MyConfig;
import com.igorkuznetsov.spring.rest.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
        User user = new User(3L, "Thomas", "Shelby", (byte) 25);
        /* ** Получение списка пользователей **  */
//        List<User> allUsers = communication.getAllUsers();
//        System.out.println(allUsers);
        /* ** Получение одного пользователя(Не работает) **  */
//        User getUsrById = communication.getUser(1L);
//        System.out.println(getUsrById);
        /* ** Добавление пользователя **  */
//        communication.addUser(user);
        /* ** Изменение пользователя **  */
        communication.updateUser(user);
        /* ** Удаление пользователя ** */
//        communication.deleteUser(user,3L);
//        System.out.println(communication.getCode());
    }
}
