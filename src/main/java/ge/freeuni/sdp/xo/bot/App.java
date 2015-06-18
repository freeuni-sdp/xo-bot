package ge.freeuni.sdp.xo.bot;

import ge.freeuni.sdp.xo.bot.OptionList;

import java.io.IOException;



import java.util.Scanner;

import javax.ws.rs.client.Client;

import javax.ws.rs.core.Response;

public class App {
	private static ServiceProxy proxy;
	public static Scanner scanner;

	private static String[] names={"signin","achiev","game","chat","login","rooms","history"};
	private static String[] mocks={
		"http://private-770c3-xosignin.apiary-mock.com",
		"http://private-cee85-xoachiev.apiary-mock.com",
		"http://private-8ed96-xogame.apiary-mock.com/webapi",
		"http://private-60b4a-xochat1.apiary-mock.com",
		"http://private-72df70-xologinapi.apiary-mock.com",
		"http://private-5192e-xorooms.apiary-mock.com",
		"http://private-95cbf-xohistory.apiary-mock.com"
	};
	
	private static String before="http://xo-";
	private static String after=".herokuapp.com/";
	//returns response code for ping
	public static int getStatusForServiceName(String serviceName,Client c) throws IOException{
		String url=before+serviceName+after;
		Response r=c.target(url).request().get();
		return r.getStatus();
		
	}
	private class Strings{
		
	}
	public static void main(String[] args) throws IOException {
//		HashMap<String, String> mocked=new HashMap<String, String>();
//		HashMap<String, String> real=new HashMap<String, String>();
//		for(int i=0;i<names.length;i++){
//			mocked.put(blueprints[i], mocks[i]);
//			real.put(blueprints[i], before+names[i]+after);
//		}
//		ClientConfig config = new ClientConfig().register(JacksonFeature.class);
//		Client client = ClientBuilder.newClient(config);
//		for(int i=0;i<names.length;i++){
//	//		int responseCode = getStatusForServiceName(names[i],client);
//		//	System.out.println("Host: " + before+names[i]+after);
//			//System.out.println("Response Code : " + responseCode);
//		}
		scanner = new Scanner(System.in);
		int id = new OptionList<Integer>()
				.title("Select service URI:")
				.add("xo-signin",
						1)
				.add("xo-achiev",
						2)
				.add("xo-game",
						3)
				.add("xo-chat",
						4)
				.add("xo-login",
						5)
				.add("xo-rooms",
						6)
				.add("xo-history",
						7)
				.read(scanner);
		switch(id){
			case 1:
				SigninService.start(scanner);
				break;
		}

	}
		
	
}
