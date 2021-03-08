package com.example.client.demo.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserUpdateClient {

    public void UpdateClient(){

        Logger logger = LoggerFactory.getLogger(UserUpdateClient.class);

        try {

            URL url = new URL(
                    "http://localhost:9091/update");
            HttpURLConnection connectionUpdate = (HttpURLConnection) url.openConnection();
            connectionUpdate.setDoOutput(true);
            connectionUpdate.setRequestMethod("PUT");
            connectionUpdate.setRequestProperty("Content-Type", "application/json");

            String input = "{\"id\":\"13\",\"name\":\"SamanA\",\"email\":\"saman@gmail.com\"}";

            UserPostClient.CheckOutput(connectionUpdate, input, logger);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
