package com.google.plus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Twitter {
	private final static String getTokenURL = "https://api.twitter.com/oauth2/token";
	 private static String bearerToken;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	    // encodeKeys(APIKEY, APISECRET);

	    new Thread(new Runnable() {

	        @Override
	        public void run() {
	            try {

	                bearerToken = requestBearerToken(getTokenURL);
	                fetchTimelineTweet("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=jethro_estrada&count=10");

	            } catch (IOException e) {
	                System.out.println("IOException e");
	                e.printStackTrace();
	            }
	        }
	    }).start();

	}

	// Encodes the consumer key and secret to create the basic authorization key
	private static String encodeKeys(String consumerKey, String consumerSecret) {
	    try {
	        String encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
	        String encodedConsumerSecret = URLEncoder.encode(consumerSecret,
	                "UTF-8");

	        String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
	        byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());

	        return new String(encodedBytes);
	    } catch (UnsupportedEncodingException e) {
	        return new String();
	    }
	}

	// Constructs the request for requesting a bearer token and returns that
	// token as a string
	private static String requestBearerToken(String endPointUrl)
	        throws IOException {
	    HttpsURLConnection connection = null;
	    String encodedCredentials = encodeKeys("HtmUtOml8IMpzFdtE8AfEmzZC", "VPunsRZQrKEnYwktXfPcwvuCQXf81vF1EhFF3EBnqvE87dqElB");

	    System.out.println("encodedCredentials "+encodedCredentials);
	    try {
	        URL url = new URL(endPointUrl);
	        connection = (HttpsURLConnection) url.openConnection();
	        System.out.println(connection);
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Host", "api.twitter.com");
	        connection.setRequestProperty("User-Agent", "anyApplication");
	        connection.setRequestProperty("Authorization", "Basic "
	                + encodedCredentials);
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	        connection.setRequestProperty("Content-Length", "29");
	        connection.setUseCaches(false);

	        writeRequest(connection, "grant_type=client_credentials");

	        // Parse the JSON response into a JSON mapped object to fetch fields
	        // from.
	        JSONObject obj = (JSONObject) JSONValue.parse(readResponse(connection));

	        if (obj != null) {
	            String tokenType = (String) obj.get("token_type");
	            String token = (String) obj.get("access_token");

	            return ((tokenType.equals("bearer")) && (token != null)) ? token
	                    : "";
	        }
	        return new String();
	    } catch (MalformedURLException e) {
	        throw new IOException("Invalid endpoint URL specified.", e);
	    } finally {
	        if (connection != null) {
	            connection.disconnect();
	        }
	    }
	}

	// Fetches the first tweet from a given user's timeline
	private static String fetchTimelineTweet(String endPointUrl)
	        throws IOException {
	    HttpsURLConnection connection = null;

	    try {
	        URL url = new URL(endPointUrl);
	        connection = (HttpsURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("Host", "api.twitter.com");
	        connection.setRequestProperty("User-Agent", "anyApplication");
	         connection.setRequestProperty("Authorization", "Bearer " +  bearerToken);
	        connection.setUseCaches(false);

	        // Parse the JSON response into a JSON mapped object to fetch fields
	        // from.
	        JSONArray obj = (JSONArray) JSONValue.parse(readResponse(connection));
	        System.out.println("JSON obj = "+obj);

	        if (obj != null) {
	            String tweet = ((JSONObject) obj.get(0)).get("text").toString();
	            System.out.println(tweet);
	            return (tweet != null) ? tweet : "";
	        }
	        return new String();
	    } catch (MalformedURLException e) {
	        throw new IOException("Invalid endpoint URL specified.", e);
	    } finally {
	        if (connection != null) {
	            connection.disconnect();
	        }
	    }
	}

	// Writes a request to a connection
	private static boolean writeRequest(HttpURLConnection connection,
	        String textBody) {
	    try {
	        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
	                connection.getOutputStream()));
	        wr.write(textBody);
	        wr.flush();
	        wr.close();

	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}

	// Reads a response for a given connection and returns it as a string.
	private static String readResponse(HttpURLConnection connection) {
	    try {
	        StringBuilder str = new StringBuilder();

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
	        String line = "";
	        while ((line = br.readLine()) != null) {
	            str.append(line + System.getProperty("line.separator"));
	        }
	        return str.toString();
	    } catch (IOException e) {
	        return new String();
	    }
	}
}