package com.example.client.demo;

import com.example.client.demo.user.UserClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        UserClientService userClient = new UserClientService();
        userClient.addNewUserClient();
        userClient.getUserListClient();
        userClient.updateUserClient();
        userClient.deleteUserClient();
    }
}
