package com.KafuuChino0722.coreextensions.network;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BilibiliAPI {
        public static void main(String[] args) {
            try {
                String url = "https://api.bilibili.com/x/web-interface/nav";

                URL obj = new URL(url);

                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();

                    con.disconnect();

                    String jsonResponse = content.toString();

                    int unameStartIndex = jsonResponse.indexOf("\"uname\":") + 8;
                    int unameEndIndex = jsonResponse.indexOf("\"", unameStartIndex);
                    String uname = jsonResponse.substring(unameStartIndex, unameEndIndex);

                } else {
                    System.out.println("请求失败，响应代码: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }