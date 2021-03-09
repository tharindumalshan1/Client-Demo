package com.example.client.demo;

import com.example.client.demo.user.UserClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        UserClient userClient = new UserClient();
       // userClient.PostClient();
        //userClient.DeleteClient();
      //  userClient.UpdateClient();
        userClient.getClient();

    }

}
