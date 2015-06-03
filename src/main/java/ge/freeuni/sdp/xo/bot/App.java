package ge.freeuni.sdp.xo.bot;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class App {
	private static String[] names={"login","signin","chat","rooms","game","achiev","history"};
	private static String before="http://xo-";
	private static String after=".herokuapp.com/webapi/ping";
	//returns response code for ping
	public static int getStatusForServiceName(String serviceName) throws IOException{
		
		String url=before+serviceName+after;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 		con.setRequestMethod("GET");
		return con.getResponseCode();
		
	}
	
	public static void main(String[] args) throws IOException {
		for(int i=0;i<names.length;i++){
			int responseCode = getStatusForServiceName(names[i]);
			System.out.println("Host: " + before+names[i]+after);
			System.out.println("Response Code : " + responseCode);
		}
		
 
	}
}
