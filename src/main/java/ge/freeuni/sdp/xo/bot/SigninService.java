package ge.freeuni.sdp.xo.bot;

import java.util.Scanner;

import javax.ws.rs.core.Response;
/*
 * This class is responsible for communicating user with signin microservice. All methods that 
 * xo-sign in has can be called by the user from here
 */
public class SigninService{
	private static String mocked="http://private-770c3-xosignin.apiary-mock.com";
	private static String heroku="http://xo-signin.herokuapp.com";
	private String url;
	private static ServiceProxy proxy;
	public SigninService(boolean isReal){
		if(isReal) url=heroku;
		else url=mocked;
		proxy=new ServiceProxy(url);
	}

	public int RegisterUser(String email,String name, String pass){
		
		String toSend="{"+"email: "+email+","+"username:"+name+","+"password:"+pass+"}";
		Response r=proxy.create("signup",toSend,"");
		return r.getStatus();
	}
	public int ConfirmEmail(String res){
		Response r=proxy.get("confirm_email"+res);
		return r.getStatus();
	}
	public int RecoverPass(String email,String name){
		
		String toSend="{"+"email: "+email+","+"username:"+name+"}";
		Response r=proxy.create("recover_password",toSend,"");
		return r.getStatus();
	}
	public int RecoverUsername(String email){
		
		String toSend="{"+"email: "+email+"}";
		Response r=proxy.create("recover_username",toSend,"");
		return r.getStatus();
	}
	public static void main(String[] args) {
		SigninService s=new SigninService(false);
		System.out.print(s.RegisterUser("a", "b", "c"));
	}
}
