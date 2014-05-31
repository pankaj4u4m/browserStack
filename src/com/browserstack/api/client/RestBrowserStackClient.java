package com.browserstack.api.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;

import com.browserstack.exception.DependencyException;

public class RestBrowserStackClient implements BroswerStackClient {
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";
    private static final String UTF_8 = "UTF-8";
    private static final String POST = "POST";
    private static final String SEPARATOR = ":";
    private static final String REST_URL = "http://api.browserstack.com/";
    private final String userName;
    private final String accessKey;
    private final String apiVersion;

    public RestBrowserStackClient(String userName, String accessKey, String apiVersion) {
        super();
        this.userName = userName;
        this.accessKey = accessKey;
        this.apiVersion = apiVersion;
    }

    @Override
    public String get(String method) {
        HttpURLConnection connection = null;

        try {
            connection = getConnection(method);

            StringBuilder output = new StringBuilder();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine);
            }
            in.close();
            System.out.println(output.toString());
            return output.toString();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new DependencyException(e);
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private HttpURLConnection getConnection(String method) throws MalformedURLException, IOException {
        HttpURLConnection connection;
        URL url = new URL(REST_URL + apiVersion + method);
        connection = (HttpURLConnection) url.openConnection();
        String authString = userName + SEPARATOR + accessKey;

        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        connection.setRequestProperty("Authorization", "Basic " + authStringEnc);

        return connection;
    }

    @Override
    public String post(String method, Map<String, String> input) {
        HttpURLConnection connection = null;

        try {

            connection = getConnection(method);

            connection.setRequestMethod(POST);
            connection.setDoOutput(true);

            // Send request

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            StringBuilder builder = new StringBuilder();
            for (Entry<String, String> e : input.entrySet()) {
                if (e.getKey() != null && e.getValue() != null) {
                    builder.append(
                            URLEncoder.encode(e.getKey(), UTF_8) + EQUAL + URLEncoder.encode(e.getValue(), UTF_8))
                            .append(AMPERSAND);
                }
            }
            wr.writeBytes(builder.toString());
            wr.flush();
            wr.close();

            StringBuilder output = new StringBuilder();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine);
            }
            in.close();
            System.out.println(output.toString());
            return output.toString();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new DependencyException(e);
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
