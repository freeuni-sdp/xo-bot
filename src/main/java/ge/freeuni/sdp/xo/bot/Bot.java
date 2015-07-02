package ge.freeuni.sdp.xo.bot;
/*
 * Bot class for bot playing stategy. Missing play algorith, where we will use GameService class
 */
public class Bot implements Runnable{
	private int num;
	private RoomsService xoRoom;
	private GameService xoGame;
	private String roomId;
	private String token;
	public Bot(int num,String token,RoomsService rService,GameService gService,String roomId){
		this.num=num;
		this.xoRoom=rService;
		this.xoGame=gService;
		this.token=token;
		this.roomId=roomId;
				
	}
	public void setRoomId(String id){
		roomId=id;
	}
	public int getIndex(){
		return num;
	}
	
	public String getToken(){
		return token;
	}

	private void play(){
		//gameLogic
	}
	private Room getRoom(){
		return xoRoom.getRoomById(roomId, token);
	}
	private String occupyRoom(){
		Room r=xoRoom.joinRoom(roomId, token);
		return r.getId();
	}
	@Override
	public void run() {
		
		String roomId=occupyRoom();
		while(true){
			//waits for a player to join
			if(getRoom().getx_user()!=null && getRoom().geto_user()!=null) break;
		}
		play();
		
	}

}
