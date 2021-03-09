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

public class UserClient {

    Logger logger = LoggerFactory.getLogger(UserClient.class);

    public void PostClient(){
        try {

            URL url = new URL(
                    "http://localhost:9091/add-user");
            HttpURLConnection connectionPost = (HttpURLConnection) url.openConnection();
            connectionPost.setDoOutput(true);
            connectionPost.setRequestMethod("POST");
            connectionPost.setRequestProperty("Content-Type", "application/json");

            String input = "{\"name\":\"SamanKumara\",\"email\":\"saman@gmail.com\"}";

            CheckOutput(connectionPost, input, logger);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

        public void DeleteClient(){

            try {
                URL url = new URL(
                        "http://localhost:9091/delete/13");
                HttpURLConnection connectionDelete = (HttpURLConnection) url.openConnection();
                connectionDelete.setRequestMethod("DELETE");
                connectionDelete.setRequestProperty("Accept", "application/json");


                getResponse(connectionDelete, logger);

            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    public void UpdateClient(){
        try {

            URL url = new URL(
                    "http://localhost:9091/update");
            HttpURLConnection connectionUpdate = (HttpURLConnection) url.openConnection();
            connectionUpdate.setDoOutput(true);
            connectionUpdate.setRequestMethod("PUT");
            connectionUpdate.setRequestProperty("Content-Type", "application/json");

            String input = "{\"id\":\"16\",\"name\":\"SamanA\",\"email\":\"saman@gmail.com\"}";

            CheckOutput(connectionUpdate, input, logger);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void  getClient(){
        try {
            URL url = new URL("http://localhost:9091/users");
            HttpURLConnection connectionGet = (HttpURLConnection) url.openConnection();
            connectionGet.setRequestMethod("GET");
            connectionGet.setRequestProperty("Accept", "application/json");

            getResponse(connectionGet, logger);

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