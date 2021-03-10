package com.example.client.demo;

import com.example.client.demo.user.UserClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] arg) {

        UserClient userClient = new UserClient();
        userClient.addNewUserClient();
        userClient.deleteUserClient();
        userClient.updateUserClient();
        userClient.getUserListClient();

    }

}
