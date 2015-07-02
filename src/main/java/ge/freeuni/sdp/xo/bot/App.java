
package ge.freeuni.sdp.xo.bot;


import java.io.IOException;



import java.util.ArrayList;

import javax.ws.rs.WebApplicationException;

public class App {
	//default names for bots
	private String[] botUserNames={
		"Jack","Jill","John","Jenny", "Mixo","Maro"
	};
	//last part of email. first is the name
	private String email="@freeuni.edu.ge";
	//for keeping track which bot should be created next
	private int nextBot;
	private Bot firstBot;
	//password for each bot
	private String botPassword="123456";
	//minimal number of rooms to be free in order for the program to start
	private int min_num_rooms=3;
	private SigninService xoSignin;
	private LoginService xoLogin;
	private RoomsService xoRooms;
	//number of bots to start
	private int numBots;
	//to keep track of free rooms
	private ArrayList<String> freeRooms;
	//boolean for knowing whether use a real server or mocked one
	private boolean isReal;
	public App(boolean isReal,int numBots){
		this.numBots=numBots;
		this.isReal=isReal;
		xoSignin=new SigninService(isReal);
		xoLogin=new LoginService(isReal);
		xoRooms=new RoomsService(isReal);
		freeRooms=new ArrayList<>();
		nextBot=0;
		//first bot is needed to have a token to see all rooms
		firstBot=initBot("-1");
	}
	/*initializes the bot and gives the roomId which he will later occupy,
	 * tries to login with bot, if cant then its either a server error or the bot is not registers.
	 * If its last then registers bot
	*/
	private Bot initBot(String roomId){
		String token=xoLogin.loginUser(botUserNames[nextBot], botPassword);
		if(token==null) {
			int resCode=xoSignin.RegisterUser(botUserNames[nextBot]+email, botUserNames[nextBot], botPassword);
			if(resCode!=200)throw new WebApplicationException(resCode);
			token=xoLogin.loginUser(botUserNames[nextBot], botPassword);
			Bot bot=new Bot(nextBot, token,new RoomsService(isReal),new GameService(isReal),roomId);
			nextBot++;
			return bot;
		}else if (token.equals("")){
			throw new WebApplicationException();
			
		}else{
			
			Bot bot=new Bot(nextBot, token,new RoomsService(isReal),new GameService(isReal),roomId);
			nextBot++;
			return bot;
		}
		
		
	}
	//checking if the number of rooms unoccupied is more than minimum if so then the bots are activated
	public void listen(){
		ArrayList<Room> rooms;
		while(true){
			rooms=xoRooms.getAllRooms(firstBot.getToken());
			int counter=0;
			for(int i=0;i<rooms.size();i++){
				Room cur=rooms.get(i);
				if(cur.geto_user()==null && cur.getx_user()==null){
					counter++;
					freeRooms.add(cur.getId());
				}
			}
			if(counter>min_num_rooms) start();
		}
	}
	//creates bots and starts each bot thread to start occupying rooms and waiting for users
	private void start() {
		
		Bot[] bots=new Bot[numBots-1];
		firstBot.setRoomId(freeRooms.remove(0));
		int counter=Math.min(freeRooms.size(), numBots-1);
		for(int i=nextBot;i<=counter;i++){
			bots[i]=initBot(freeRooms.remove(0));
			new Thread(bots[i]).start();
		}
		new Thread(firstBot).start();
		
	}
	
	
}
