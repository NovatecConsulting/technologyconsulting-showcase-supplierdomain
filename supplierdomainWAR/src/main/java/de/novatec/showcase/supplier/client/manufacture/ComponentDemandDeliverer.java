package de.novatec.showcase.supplier.client.manufacture;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import de.novatec.showcase.manufacture.dto.ComponentDemand;

public class ComponentDemandDeliverer {

	private static final String PORT = System.getProperty("http.port.manufacture");
	private static final String BASE_URL = "http://localhost:" + PORT + "/manufacturedomain/";

	private static final String COMPONENT_URL = BASE_URL + "component/";
	private static final String DELIVER_URL = COMPONENT_URL + "deliver/";
	private Client client;

	public ComponentDemandDeliverer() {
		client = ClientBuilder.newClient();
		client.register(JacksonJsonProvider.class);
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().build();
		client.register(feature);
	}

	public void deliver(Collection<ComponentDemand> componentDemands) throws RestcallException {
		WebTarget target = client.target(DELIVER_URL);
		Builder builder = target.request(MediaType.APPLICATION_JSON);
		Response response = asAdmin(builder.accept(MediaType.APPLICATION_JSON_TYPE))
				.post(Entity.json(componentDemands));
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			return;
		}
		throw new RestcallException(
				"Error " + Response.Status.fromStatusCode(response.getStatus()) + " while calling " + DELIVER_URL + " with " + componentDemands);
	}

	private static Builder asAdmin(Builder builder) {
		return asUser(builder, "admin", "adminpwd");
	}

	private static Builder asUser(Builder builder, String userName, String password) {
		return builder.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME, userName)
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD, password);
	}
}