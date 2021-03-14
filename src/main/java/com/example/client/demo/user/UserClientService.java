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


    /**
     * Check the add new user end point client
     * @param addUserInput the new User to be created
     * @param postUrl end point for the create new user
     * @return the response from the  server
     */
    public void addNewUserClient() {
        System.out.println(postUrl);
        try {
            URL url = new URL(postUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");


            checkResponseFromServer(connection, addUserInput, logger);

        } catch (IOException e) {
            logger.error("Error when Add new user");
            e.printStackTrace();
        }

    }

    /**
     * Check the delete a user endpoint
     * @param deleteUrl is the endpoint url for delete user by id
     * @return the response from the  server
     */
    public void deleteUserClient() {

        try {
            URL url = new URL(deleteUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Accept", "application/json");


            checkResponseFromServer(connection, logger);

        } catch (IOException e) {
            logger.error("Error when Delete a user");
            e.printStackTrace();
        }

    }

    /**
     * Check the update existing users endpoint
     * @param updateUserInput the  User to be update
     * @param updateUrl end point for the create new user
     * @return the response from the  server
     */
    public void updateUserClient() {
        try {

            URL url = new URL(updateUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");

            checkResponseFromServer(connection, updateUserInput, logger);

        } catch (IOException e) {
            logger.error("Error when Update a new user ");
            e.printStackTrace();
        }

    }

    /**
     * Check the get all  users endpoint
     * @param getUrl end point for the get all the users
     * @return the response from the  server
     */
    public void getUserListClient() {

        try {

            URL url = new URL(getUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            checkResponseFromServer(connection, logger);
        } catch (IOException e) {
            logger.error("Error when get Users from database" , e);
            e.printStackTrace();
        }

    }

    /**
     * Check the response from the server
     * @param input for the create and update the users
     * @param connection endpoint to be checked
     * @return the response from the  server
     */
    static void checkResponseFromServer(HttpURLConnection connection, String input, Logger logger) throws IOException {
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(input.getBytes());
        outputStream.flush();

        checkResponseFromServer(connection, logger);
    }


    static void checkResponseFromServer(HttpURLConnection connection, Logger logger) throws IOException {
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode());
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));

        String output;
        logger.info("Output from Server .... \n");
        while ((output = bufferedReader.readLine()) != null) {

            logger.info(output);
        }

        connection.disconnect();
    }
}

