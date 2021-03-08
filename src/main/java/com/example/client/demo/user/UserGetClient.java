package com.example.client.demo.user;

import org.apache.commons.httpclient.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@PropertySource("classpath:url.properties")
public class UserGetClient {



    Logger logger = LoggerFactory.getLogger(UserGetClient.class);

    @Value("${users.find-all}")
    private String findAll;

       public void getClient(){
        try {
            logger.info(findAll);
            URL url;
            url = new URL("http://localhost:9091/users");
            HttpURLConnection connectionGet = (HttpURLConnection) url.openConnection();
            connectionGet.setRequestMethod("GET");
            connectionGet.setRequestProperty("Accept", "application/json");


            if (connectionGet.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + connectionGet.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    (connectionGet.getInputStream())));

            logger.info("Output from Server .... \n");
            String output;
            while ((output = bufferedReader.readLine()) != null) {

                logger.info(output);
            }

            connectionGet.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}