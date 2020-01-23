package de.novatec.showcase.supplier.client.manufacture;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import de.novatec.showcase.supplier.dto.ComponentDemand;
import de.novatec.showcase.supplier.dto.ComponentDemands;

public class ComponentDemandDeliverer {

	private static final String JNDI_PROPERTY_MANUFACTUREDOMAIN_DELIVER_URL = "manufacturedomain.deliver.url";
	private static final String JNDI_PROPERTY_MANUFACTUREDOMAIN_USERNAME = "manufacturedomain.username";
	private static final String JNDI_PROPERTY_MANUFACTUREDOMAIN_PASSWORD = "manufacturedomain.password";
	private static final Logger log = LoggerFactory.getLogger(ComponentDemandDeliverer.class);
	private static final String USERNAME = System.getProperty("username.manufacture");
	private static final String PASSWORD = System.getProperty("password.manufacture");
	private static final String PORT = System.getProperty("http.port.manufacture");
	private static final String BASE_URL = "http://localhost:" + PORT + "/manufacturedomain/";

	private static final String COMPONENT_URL = BASE_URL + "component/";
	private static final String DELIVER_URL = COMPONENT_URL + "deliver/";
	private String deliverUrl = DELIVER_URL;
	private String username = USERNAME;
	private String password = PASSWORD;
	private Client client;

	public ComponentDemandDeliverer() {
		client = ClientBuilder.newClient();
		client.register(JacksonJsonProvider.class);
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().build();
		client.register(feature);

		try {
			deliverUrl = (String)new InitialContext().lookup(JNDI_PROPERTY_MANUFACTUREDOMAIN_DELIVER_URL);
			username = (String)new InitialContext().lookup(JNDI_PROPERTY_MANUFACTUREDOMAIN_USERNAME);
			password = (String)new InitialContext().lookup(JNDI_PROPERTY_MANUFACTUREDOMAIN_PASSWORD);
		} catch (NamingException e) {
			log.warn("JNDI properties " + JNDI_PROPERTY_MANUFACTUREDOMAIN_DELIVER_URL + " or " +
					JNDI_PROPERTY_MANUFACTUREDOMAIN_USERNAME + " or " +
					JNDI_PROPERTY_MANUFACTUREDOMAIN_PASSWORD + " not found! Using system properties where possible!", e);
		}
}

	public void deliver(List<ComponentDemand> componentDemands) throws RestcallException {
		WebTarget target = client.target(deliverUrl);
		Response response = asAdmin(target.request(MediaType.APPLICATION_JSON_TYPE))
				.post(Entity.json(new ComponentDemands().setComponentDemands(componentDemands)));
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			return;
		}
		throw new RestcallException(
				"Http Status " + Response.Status.fromStatusCode(response.getStatus()) + " while calling " + DELIVER_URL + " with " + componentDemands + ". " + response.readEntity(String.class));
	}

	private Builder asAdmin(Builder builder) {
		return asUser(builder, username, password);
	}

	private static Builder asUser(Builder builder, String userName, String password) {
		return builder.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME, userName)
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD, password);
	}
}