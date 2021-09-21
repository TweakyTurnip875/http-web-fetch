import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.InputStreamReader;
import java.io.*;

class Untitled {

public static void main(String[] args) {
		HttpURLConnection c = null;
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/posts/1/");
			c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.setUseCaches(false);
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
				System.out.println();
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

}