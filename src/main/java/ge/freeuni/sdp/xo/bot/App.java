package ge.freeuni.sdp.xo.bot;

import java.io.IOException;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public class App {
	private static String[] names={"login","signin","chat","rooms","game-sdp","achiev","history"};
	private static String before="http://xo-";
	private static String after=".herokuapp.com/webapi/ping";
	//returns response code for ping
	public static int getStatusForServiceName(String serviceName,Client c) throws IOException{
		String url=before+serviceName+after;
		Response r=c.target(url).request().get();
		return r.getStatus();

	}

	public static void main(String[] args) throws IOException {
		ClientConfig config = new ClientConfig().register(JacksonFeature.class);
		Client client = ClientBuilder.newClient(config);
		for(int i=0;i<names.length;i++){
			int responseCode = getStatusForServiceName(names[i],client);
			System.out.println("Host: " + before+names[i]+after);
			System.out.println("Response Code : " + responseCode);
		}


	}
}
