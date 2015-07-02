package ge.freeuni.sdp.xo.bot;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
/*
 * Rooms API method calls
 */
public class RoomsService {
	private static String mocked="http://private-anon-eacf23b47-xorooms.apiary-mock.com";
	private static String heroku="http://xo-rooms.herokuapp.com/";
	private String url;
	private static ServiceProxy proxy;
	public RoomsService(boolean isReal){
		if(isReal) url=heroku;
		else url=mocked;
		proxy=new ServiceProxy(url);
	}
	public ArrayList<Room>  getAllRooms(String token){
		Response response=proxy.get(token);
		ArrayList<Room> rooms=new ArrayList<Room>();
		String a=response.readEntity(String.class);
		JSONObject obj= new JSONObject(a);
		JSONArray ar=obj.getJSONArray("rooms");
		for(int i=0;i<ar.length();i++){
			JSONObject cur=ar.getJSONObject(i);
			rooms.add(getRoomFromString(cur.toString()));
		}
		return rooms;
	}
	private Room getRoomFromString(String json){
		JSONObject obj= new JSONObject(json);
		int id=(Integer)obj.get("id");
		int xUser=(Integer)obj.get("x_user");
		int oUser=(Integer)obj.get("o_user");
		Room r=new Room(Integer.toString(id),Integer.toString(xUser), Integer.toString(oUser));
		return r;
	}
	public Room getRoomById(String id,String token){
		Response response=proxy.get(id+token);
		String a=response.readEntity(String.class);
		return getRoomFromString(a);
	}
	public Room joinRoom(String id,String token){
		Response response=proxy.create(id+token, "","");
		String a=response.readEntity(String.class);
		return getRoomFromString(a);
	}
	public int leaveRoom(String roomId,String userId,String token){
		Response resp=proxy.delete(roomId+"/"+userId+token);
		return resp.getStatus();
	}
	

	public static void main(String[] args) {
		
		
	}
}
