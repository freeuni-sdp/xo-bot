package ge.freeuni.sdp.xo.bot;

import javax.ws.rs.core.Response;

import org.json.JSONObject;
/*
 * Login API method calls
 */
public class LoginService  {
	private static String mocked="http://private-anon-fd00370fe-xologinapi.apiary-mock.com/webapi/login";
	private static String heroku="http://xo-login.herokuapp.com/webapi/login";
	private String url;
	private static ServiceProxy proxy;
	public LoginService(boolean isReal){
		if(isReal) url=heroku;
		else url=mocked;
		proxy=new ServiceProxy(url);
	}

	public String loginUser(String user, String password){
		String toSend="{"+"username:"+user+","+"password:"+password+"}";
		Response r=proxy.create("",toSend,"");
		//
		if(r.getStatus()==200|| r.getStatus()==201){
			String a=r.readEntity(String.class);
			JSONObject obj= new JSONObject(a);
			String token=obj.getString("token");
			return token;
			
		}
		//null means user is not registered
		if(r.getStatus()==422){
			return null;
		}else return "";
	}
}
