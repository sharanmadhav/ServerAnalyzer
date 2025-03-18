package com.serveranalyzer.services;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

public class FirebaseService {
    private static final Logger logger = Logger.getLogger(FirebaseService.class.getName());
    private final String firebaseUrl;

    public FirebaseService(String firebaseUrl) {
        this.firebaseUrl = firebaseUrl;
    }

    public void sendData(List<String> data) {
//        if(true){
//            System.out.println("response data:::"+data);
//            return;
//        }
        if (data.isEmpty()) {
            return;
        }

        try {
            URL url = new URL(firebaseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            StringBuilder jsonArray = new StringBuilder();
            jsonArray.append("[");
            for (int i = 0; i < data.size(); i++) {
                jsonArray.append(data.get(i));
                if (i < data.size() - 1) {
                    jsonArray.append(",");
                }
            }
            jsonArray.append("]");
            System.out.println("jsonArray::::::::"+jsonArray);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonArray.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_CREATED) {
                logger.severe("firebaseUrl:::::"+firebaseUrl);
                logger.warning("Failed to send data to Firebase. Response code: " + responseCode);
            }
            conn.disconnect();
        } catch (IOException e) {
            logger.severe("firebaseUrl:::::"+firebaseUrl);
            logger.severe("Error sending data to Firebase: " + e.getMessage());
        }
    }
} 