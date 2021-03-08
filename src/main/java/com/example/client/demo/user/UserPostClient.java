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

public class UserPostClient {

    Logger logger = LoggerFactory.getLogger(UserPostClient.class);

    public void PostClient(){
        try {

            URL url = new URL(
                    "http://localhost:9091/add-user");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"name\":\"SamanKumara\",\"email\":\"saman@gmail.com\"}";

            CheckOutput(connection, input, logger);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    static void CheckOutput(HttpURLConnection connection, String input, Logger logger) throws IOException {
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(input.getBytes());
        outputStream.flush();

        getResponse(connection, logger);
    }

    static void getResponse(HttpURLConnection connection, Logger logger) throws IOException {
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));

        String output;
        logger.info("Output from Server .... \n");
        while ((output = br.readLine()) != null) {

            logger.info(output);
        }

        connection.disconnect();
    }

}