package com.example.client.demo.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


@Component
public class UserClientService {

    private Logger logger = LoggerFactory.getLogger(UserClientService.class);

    public static String postUrl;
    public static String getUrl;
    public static String updateUrl;
    public static String deleteUrl;
    public static String addUserInput;
    public static String updateUserInput;


    @Value("${user.post.url}")
    public void setPostUrl(String url) {
        postUrl = url;
    }

    @Value("${user.get.url}")
    public void setGetUrl(String url) {
        getUrl = url;
    }

    @Value("${user.update.url}")
    public void setUpdateUrl(String url) {
        updateUrl = url;
    }

    @Value("${user.delete.url}")
    public void setDeleteUrl(String url) {
        deleteUrl = url;
    }

    @Value("${user.post.input}")
    public void setAddUserInput(String url) {
        addUserInput = url;
    }

    @Value("${user.update.input}")
    public void setUpdateUserInput(String url) {
        updateUserInput = url;
    }

    public void addNewUserClient() {
        System.out.println(postUrl);
        try {
            URL url = new URL(postUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");


            checkOutput(connection, addUserInput, logger);

        } catch (IOException e) {
            logger.error("Error when Add new user");
            e.printStackTrace();
        }

    }

    public void deleteUserClient() {

        try {
            URL url = new URL(deleteUrl);
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

            URL url = new URL(updateUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");

            checkOutput(connection, updateUserInput, logger);

        } catch (IOException e) {
            logger.error("Error when Update a new user ");
            e.printStackTrace();
        }

    }


    public void getUserListClient() {

        try {

            URL url = new URL(getUrl);
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

