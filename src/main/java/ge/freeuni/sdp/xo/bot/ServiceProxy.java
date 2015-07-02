package ge.freeuni.sdp.xo.bot;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.*;
import org.glassfish.jersey.jackson.JacksonFeature;

public class ServiceProxy {

	private final String uri;
	private final Client client;

	public ServiceProxy(String uri) {
		this.uri = uri;
		ClientConfig config = new ClientConfig().register(JacksonFeature.class);
		client = ClientBuilder.newClient(config);
	}
	
	public String[] getAll() {
		return client.
				target(uri)
				.request()
				.get(String[].class);
	}

	public Response get(String id) {
		Response response = 
				client
					.target(uri + "/" + id)
					.request(MediaType.TEXT_PLAIN_TYPE)
					.get();
		
		if (is404(response)) {
			return null;
		}
		
		return response;
	}

	public Response create(String res ,String task,String type) {
		Response response = client 
				.target(uri+"/"+res)
				.request(type)
				.post(Entity.entity(task, MediaType.APPLICATION_JSON_TYPE));

		
		return response;
	}
	
	public String update(String id, String task) {
		Response response = client
				.target(uri + "/" + id)
				.request()
				.put(Entity.entity(task, MediaType.APPLICATION_JSON_TYPE));

		if (is404(response)) {
			return null;
		}
		
		return response.readEntity(String.class);
	}
	
	public Response delete(String id) {
		Response response = client
				.target(uri + "/" + id)
				.request()
				.delete();
		return response;
	}
	
	private boolean is404(Response response) {
		return isStatus(response, Response.Status.NOT_FOUND);
	}
	
	private boolean is201(Response response) {
		return isStatus(response, Response.Status.CREATED);
	}
	
	private boolean isStatus(Response response, Status status ) {
		return response.getStatus() ==	status.getStatusCode();
	}
}