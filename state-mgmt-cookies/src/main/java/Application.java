package main.java;

import main.java.http.HttpRequest;
import main.java.http.HttpRequestImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Application {

    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
//        List<String> validUrls = getValidUrls();
        String request = getRequest();

        HttpRequest httpRequest = new HttpRequestImpl(request);
        httpRequest.getCookies()
                .forEach(c -> {
                    System.out.println(String.format("%s <-> %s", c.getKey(), c.getValue()));
                });
    }

    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    private static String getRequest() throws IOException {
        StringBuilder request = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            request.append(line).append(System.lineSeparator());
        }

        request.append(System.lineSeparator());

        if ((line = reader.readLine()) != null && !line.isEmpty()) {
            request.append(line).append(System.lineSeparator());
        }

        return request.toString().trim();
    }

    private static boolean isUrlValid(List<String> validUrls, HttpRequest httpRequest) {
        return validUrls.contains(httpRequest.getRequestUrl());
    }
}
