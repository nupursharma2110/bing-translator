package bing;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.io.BufferedWriter;

import org.json.JSONException;
import org.json.JSONObject;


public class bing {

	public static void main(String[] args) throws IOException, JSONException {
		String line_code = null;
		URL url1 = new URL("https://datamarket.accesscontrol.windows.net/v2/OAuth2-13");

		HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String id=URLEncoder.encode("nups-123", "UTF-8");
		String pass=URLEncoder.encode("I5cmEBW/qUJuXdtDmB0uqNyRI+mOfbeSRA/ZADP1Mr4=", "UTF-8");


		String urlParameters = "grant_type=client_credentials&client_id="+id+"&client_secret="+pass+"&scope=http://api.microsofttranslator.com";
		conn.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		File file_code = new File("/home/nupursharma/Code.csv");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file_code)));

		File file = new File("/home/nupursharma/Desktop/bing3.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
		String ssss=new String(response);

		JSONObject obj=new JSONObject(ssss);
		String access_token=(String) obj.get("access_token");


		System.out.println(access_token);
		String line = null;


		HashMap map=new HashMap();
	    while( (line_code = br1.readLine())!= null )
		{
			String[]ss = line_code.split(",");
			map.put(ss[0], ss[1]);
		}
		br1.close();

		while( (line = br.readLine())!= null )
		{
			String [] tokens = line.split(",");
			String var_1 = tokens[0];
			String var_3 = tokens[2];
			String var_2 = tokens[1];


			String from=(String) map.get(var_1);
			String to=(String) map.get(var_2);
			String text=var_3;

			String uri = "http://api.microsofttranslator.com/v2/Http.svc/Translate?text=" + URLEncoder.encode(text, "UTF-8") + "&from=" + from + "&to=" + to;
			String authToken = "Bearer" + " " + access_token;
			URL urll=new URL(uri);
			HttpURLConnection conn1 = (HttpURLConnection) urll.openConnection();
			conn1.setRequestMethod("GET");
			conn1.setRequestProperty("Authorization",authToken);
			BufferedReader in1 = new BufferedReader(
					new InputStreamReader(conn1.getInputStream()));
			String inputLine1;
			StringBuffer response1 = new StringBuffer();

			while ((inputLine1 = in1.readLine()) != null) {
				response1.append(inputLine1);
			}
			in1.close();
			System.out.println(response1.toString());

			String sss=response1.toString();
			sss = sss.substring(sss.indexOf(">") + 1);
			sss = sss.substring(0, sss.indexOf("<"));
			System.out.println(sss);
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/nupursharma/Desktop/api1.txt", true)));
			out.println(sss);
			out.close();


		 }



	}

}
