package com.example.client.demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class UserDeleteClient {

    public void DeleteClient(){

        Logger logger = LoggerFactory.getLogger(UserDeleteClient.class);

        try {
            URL url = new URL(
                    "http://localhost:9091/delete/15");
            HttpURLConnection connectionDelete = (HttpURLConnection) url.openConnection();
            connectionDelete.setRequestMethod("DELETE");
            connectionDelete.setRequestProperty("Accept", "application/json");


            UserPostClient.getResponse(connectionDelete, logger);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}