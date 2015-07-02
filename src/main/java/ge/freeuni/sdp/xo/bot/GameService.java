package ge.freeuni.sdp.xo.bot;
import javax.ws.rs.core.Response;
//unfinished class which is responsible for calling xo-game API methods
public class GameService {
	private static String mocked="http://private-anon-373a38faf-xogame.apiary-mock.com/webapi/";
	private static String heroku="http://xo-game-sdp.herokuapp.com/webapi";
	private static String type="application/json";
	private String url;
	private static ServiceProxy proxy;
	public GameService(boolean isReal){
		if(isReal) url=heroku;
		else url=mocked;
		proxy=new ServiceProxy(url);
	}
	public GameState registerAsPlayer(String gameId,String userId){
		Response r=proxy.create(gameId, "{ userId : "+userId+" }", type);
		return null;
		
	}
	
}
