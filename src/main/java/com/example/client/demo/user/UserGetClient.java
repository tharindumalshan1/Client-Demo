//package com.example.client.demo.user;
//
//import org.apache.commons.httpclient.URI;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
//import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//@Qualifier
//@Configuration
//@PropertySource("classpath:url.properties")
////@ConfigurationProperties(prefix="user")
//public class UserGetClient {
//
//   private final Logger logger = LoggerFactory.getLogger(UserGetClient.class);
//
// @Value("${user.find}")
//   String find;
//
//    @PostConstruct
//    public void initIt()  {
//        logger.debug("url in post conturuct is --------->:"+ find);
//    }
//
//       public void  getClient(){
//        try {
//           // logger.debug(find);
//            URL url = new URL(find);
//            HttpURLConnection connectionGet = (HttpURLConnection) url.openConnection();
//            connectionGet.setRequestMethod("GET");
//            connectionGet.setRequestProperty("Accept", "application/json");
//
//
//            if (connectionGet.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : " + connectionGet.getResponseCode());
//            }
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
//                    (connectionGet.getInputStream())));
//
//            logger.info("Output from Server .... \n");
//            String output;
//            while ((output = bufferedReader.readLine()) != null) {
//
//                logger.info(output);
//            }
//
//            connectionGet.disconnect();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//       }
//
//}