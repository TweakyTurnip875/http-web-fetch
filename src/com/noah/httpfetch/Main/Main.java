package com.noah.httpfetch.Main;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.InputStreamReader;
import java.io.*;
import com.google.gson.*;
import com.noah.httpfetch.AuthMsg.AuthMsg;

public class Main {

	public static String getData() {
		HttpURLConnection c = null;
		try {
			URL url = new URL("http://api.plos.org/search?q=title:DNA");
			c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.setUseCaches(false);
			c.setRequestProperty("Content-length", "0");
			c.setAllowUserInteraction(false);
			c.addRequestProperty("Content-length", "0");
			c.connect();

			int status = c.getResponseCode();

			if(status == 200 || status == 201) {
				BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;

				while((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				return sb.toString();
			}

		} catch (Exception MalformedURLException) {
			System.out.println("Error in Main.java file\n" + MalformedURLException);
		} finally {
			if(c != null) {
				try {
					c.disconnect();
				} catch(Exception ex) {
					System.out.println("error");
				}
			}
		}
		return null;
	}
	public static void main(String[] args) {
		String data = getData();
		AuthMsg message = new Gson().fromJson(data, AuthMsg.class);
		
		System.out.println(message);
	}

}