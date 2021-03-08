package com.example.client.demo;

import com.example.client.demo.user.UserGetClient;
//import com.example.client.demo.user.UserPostClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        UserGetClient userGetClient = new UserGetClient();
        userGetClient.getClient();

//        UserPostClient userPostClient = new UserPostClient():
//        userPostClient.PostClient();

    }

}
