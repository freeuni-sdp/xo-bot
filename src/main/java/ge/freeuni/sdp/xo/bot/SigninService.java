package ge.freeuni.sdp.xo.bot;

import java.util.Scanner;

import javax.ws.rs.core.Response;
/*
 * This class is responsible for communicating user with signin microservice. All methods that 
 * xo-sign in has can be called by the user from here
 */
public class SigninService {
	private static String mocked="http://private-770c3-xosignin.apiary-mock.com";
	private static String heroku="http://xo-signin.herokuapp.com/";
	private static ServiceProxy proxy;
	public static void start(Scanner scanner){
		String url=new OptionList<String>()
				.title("Select server type:")
					.add("Mock", mocked)
					.add("Heroku", heroku).read(scanner);
		proxy = new ServiceProxy(url);
		int id=new OptionList<Integer>()
				.title("Select method:")
					.add("Register User", 1)
					.add("Confirm Email", 2)
					.add("Recover Password", 3)
					.add("Recover Username", 4).read(scanner);
		switch(id){
			case 1:
				RegisterUser("signup",scanner);
				break;
			case 2:
				ConfirmEmail("confirm_email/0b3211d3-76c6-456d-8971-20aa277bfc76");
				break;
			case 3:
				RecoverPass("recover_password",scanner);
				break;
			case 4:
				RecoverUsername("recover_username", scanner);
		}
		
	}
	private static void RegisterUser(String res,Scanner scan){
		System.out.println("Enter name");
		String name=scan.nextLine();
		System.out.println("Enter email");
		String email=scan.nextLine();
		System.out.println("Enter pass");
		String pass=scan.nextLine();
		String toSend="{"+"email: "+email+","+"username:"+name+","+"password:"+pass+"}";
		Response r=proxy.create(res,toSend);
		System.out.println(r.getStatus());
		System.out.println(r.getHeaders());
		System.out.println(r.readEntity(String.class));
	}
	private static void ConfirmEmail(String res){
		Response r=proxy.get(res);
		System.out.println(r.getStatus());
		System.out.println(r.getHeaders());
		System.out.println(r.readEntity(String.class));
	}
	private static void RecoverPass(String res,Scanner scan){
		System.out.println("Enter name");
		String name=scan.nextLine();
		System.out.println("Enter email");
		String email=scan.nextLine();
		String toSend="{"+"email: "+email+","+"username:"+name+"}";
		Response r=proxy.create(res,toSend);
		System.out.println(r.getStatus());
		System.out.println(r.getHeaders());
		System.out.println(r.readEntity(String.class));
	}
	private static void RecoverUsername(String res,Scanner scan){
		System.out.println("Enter email");
		String email=scan.nextLine();
		String toSend="{"+"email: "+email+"}";
		Response r=proxy.create(res,toSend);
		System.out.println(r.getStatus());
		System.out.println(r.getHeaders());
		System.out.println(r.readEntity(String.class));
	}
}
