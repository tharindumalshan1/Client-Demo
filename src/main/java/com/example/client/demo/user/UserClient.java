package com.example.client.demo.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;



public class UserClient {

    Logger logger = LoggerFactory.getLogger(UserClient.class);


    public void addNewUserClient() {
        try {
            URL url = new URL("http://localhost:9091/add-user");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"name\":\"SamanKumara\",\"email\":\"saman@gmail.com\"}";

            checkOutput(connection, input, logger);

        } catch (IOException e) {
            logger.error("Error when Add new user");
            e.printStackTrace();
        }

    }

    public void deleteUserClient() {

        try {
            URL url = new URL(
                    "http://localhost:9091/delete/13");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Accept", "application/json");


            getResponse(connection, logger);

        } catch (IOException e) {
            logger.error("Error when Delete a user");
            e.printStackTrace();
        }

    }

    public void updateUserClient() {
        try {

            URL url = new URL(
                    "http://localhost:9091/update");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"id\":\"16\",\"name\":\"SamanA\",\"email\":\"saman@gmail.com\"}";

            checkOutput(connection, input, logger);

        } catch (IOException e) {
            logger.error("Error when Update a new user ");
            e.printStackTrace();
        }

    }


    public void getUserListClient() {
        try {
            URL url = new URL("http://localhost:9091/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            getResponse(connection, logger);

        } catch (IOException e) {
            logger.error("Error when get Users from database");
            e.printStackTrace();
        }

    }


    static void checkOutput(HttpURLConnection connection, String input, Logger logger) throws IOException {
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

