
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
//import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;



import org.json.JSONObject;  
import org.json.JSONArray;  

public class Gui {

	private static HttpURLConnection connection;
	public static void main(String[] args) {
		
		/*BufferedReader reader;
		String line;
		StringBuffer res = new StringBuffer();
		Map <Integer, Integer> mp = new HashMap<Integer, Integer>();
		
		try {
			URL url = new URL("https://codeforces.com/api/problemset.problems?tags=binary+search");
			//URL url = new URL("https://codeforces.com/api/contest.status?contestId=1537&handle=Arnob");
			
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			//connection.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImRlZTQ0MmQ3LWMxMTctNGM3Ni1iZDdkLWEwNTFkZjVlNmM5ZSIsImlhdCI6MTYyMTUyNjQ3Nywic3ViIjoiZGV2ZWxvcGVyL2NhOTJlMGQ2LTIwN2MtNDUyYy00MTYwLTZjZTJmMjliMzFkMSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjEwMy4xNDguNzQuODIiXSwidHlwZSI6ImNsaWVudCJ9XX0.o7TiXBdD6RPTOlwOJ8Z44cSV4up_v0vzXSczEsL0jHQoxRHCcubmCJbBg3HeJF86Ny32nU9og1Gzv3qESDlhYw");
			int st = connection.getResponseCode();
			//System.out.print(st);
			
			if(st > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while((line = reader.readLine()) != null) {
					res.append(line);
				}
				reader.close();
			}
			else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
					res.append(line);
				}
				reader.close();
			}
			//BufferedWriter bw = new BufferedWriter(new FileWriter("string.txt"));
			//bw.write(res.toString());
			//bw.close();
			System.out.println(res.toString());
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.disconnect();
		}*/
		
		Vector<Integer> v = new Vector();
	}

}
