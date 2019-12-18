package de.novatec.showcase.supplier.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import de.novatec.showcase.supplier.dto.Address;
import de.novatec.showcase.supplier.dto.Supplier;
import de.novatec.showcase.supplier.dto.SupplierComponent;
import de.novatec.showcase.supplier.dto.SupplierComponentPK;

public abstract class ResourceItBase {
	protected static final String PORT = System.getProperty("http.port");
	protected static final String BASE_URL = "http://localhost:" + PORT + "/supplierdomain/";

	protected static final String SUPPLIER_URL = BASE_URL + "supplier/";
	protected static final String SUPPLIER_COMPONENT_URL = SUPPLIER_URL + "supplier_component/";
	protected static final String PURCHASE_ORDER_URL = SUPPLIER_URL + "purchase_order/";
	protected static final String PURCHASE_ORDER_LINE_URL = SUPPLIER_URL + "purchase_order_line/";

	protected static Client client;
	
	protected static String SUPPLIER_1_KEY = "Supplier1";
	protected static String SUPPLIER_2_KEY = "Supplier2";
	protected static String SUPPLIER_3_KEY = "Supplier3";
	
	protected static String COMPONENT_ID_1 = "1";
	protected static String COMPONENT_ID_2 = "2";
	protected static String COMPONENT_ID_3 = "3";
	
	// @formatter:off
	private static List<Supplier> setupSuppliers = Arrays.asList(
			new Supplier(SUPPLIER_1_KEY, "Supplier 1 contact", "http://locahost:" + PORT, "http://localhost:" + PORT, new Address("street1", "street2", "city", "state", "country", "zip", "phone"), 0),
			new Supplier(SUPPLIER_2_KEY, "Supplier 2 contact", "http://locahost:" + PORT, "http://localhost:" + PORT, new Address("street1", "street2", "city", "state", "country", "zip", "phone"), 0),
			new Supplier(SUPPLIER_3_KEY, "Supplier 3 contact", "http://locahost:" + PORT, "http://localhost:" + PORT, new Address("street1", "street2", "city", "state", "country", "zip", "phone"), 0)
			);
	@SuppressWarnings("serial")
	private static Map<String, List<SupplierComponent>> setupSupplierComponents = new HashMap<String, List<SupplierComponent>>() {
		{
		put(SUPPLIER_1_KEY, Arrays.asList(
				new SupplierComponent(new SupplierComponentPK(COMPONENT_ID_1, 1), 7, new BigDecimal(0.0), new BigDecimal(5.0), 50, 0),
				new SupplierComponent(new SupplierComponentPK(COMPONENT_ID_2, 1), 7, new BigDecimal(0.0), new BigDecimal(5.0), 50, 0),
				new SupplierComponent(new SupplierComponentPK(COMPONENT_ID_3, 1), 7, new BigDecimal(0.0), new BigDecimal(5.0), 50, 0)));
		put(SUPPLIER_2_KEY, Arrays.asList(
				new SupplierComponent(new SupplierComponentPK(COMPONENT_ID_1, 2), 14, new BigDecimal(5.0), new BigDecimal(50.0), 100, 0),
				new SupplierComponent(new SupplierComponentPK(COMPONENT_ID_2, 2), 14, new BigDecimal(5.0), new BigDecimal(50.0), 100, 0),
				new SupplierComponent(new SupplierComponentPK(COMPONENT_ID_3, 2), 14, new BigDecimal(5.0), new BigDecimal(50.0), 100, 0)));
		put(SUPPLIER_3_KEY, Arrays.asList(
				new SupplierComponent(new SupplierComponentPK(COMPONENT_ID_1, 3), 0, new BigDecimal(10.0), new BigDecimal(100.0), 1000, 0),
				new SupplierComponent(new SupplierComponentPK(COMPONENT_ID_2, 3), 0, new BigDecimal(10.0), new BigDecimal(100.0), 1000, 0),
				new SupplierComponent(new SupplierComponentPK(COMPONENT_ID_3, 3), 0, new BigDecimal(10.0), new BigDecimal(100.0), 1000, 0)));
		}
	};
	// @formatter:on

	protected static Map<String, Supplier> dbSuppliers = new HashMap<String, Supplier>();
	protected static Map<SupplierComponentPK, SupplierComponent> dbSupplierComponents = new HashMap<SupplierComponentPK, SupplierComponent>();

	@BeforeClass
	public static void beforeClass() {
		client = ClientBuilder.newClient();
		client.register(JacksonJsonProvider.class);
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().build();
		client.register(feature);
		setup();
	}

	@AfterClass
	public static void teardown() {
		client.close();
	}

	private static void setup() {
		for (Supplier supplier : setupSuppliers) {
			Supplier dbSupplier = createSupplier(supplier);
			dbSuppliers.put(dbSupplier.getName(), dbSupplier);
			for (SupplierComponent supplierComponent : setupSupplierComponents.get(dbSupplier.getName())) {
				supplierComponent.getPk().setSupplierId(dbSupplier.getId());
				SupplierComponent dbSupplierComponent = createSupplierComponent(supplierComponent);
				dbSupplierComponents.put(dbSupplierComponent.getPk(), dbSupplierComponent);
			}
		}
	}
	
	protected static Supplier createSupplier(Supplier supplier) {
		WebTarget target = client.target(SUPPLIER_URL);
		Builder builder = target.request(MediaType.APPLICATION_JSON);
		Response response = asAdmin(builder.accept(MediaType.APPLICATION_JSON_TYPE)).post(Entity.json(supplier));
		assertResponse201(SUPPLIER_URL, response);
		return response.readEntity(Supplier.class);
	}

	protected static SupplierComponent createSupplierComponent(SupplierComponent supplierComponent) {
		WebTarget target = client.target(SUPPLIER_COMPONENT_URL);
		Builder builder = target.request(MediaType.APPLICATION_JSON);
		Response response = asAdmin(builder.accept(MediaType.APPLICATION_JSON_TYPE)).post(Entity.json(supplierComponent));
		assertResponse201(SUPPLIER_COMPONENT_URL, response);
		return response.readEntity(SupplierComponent.class);
	}
	

	public static void assertResponse200(String url, Response response) {
		assertResponse(url, response, Response.Status.OK);
	}

	public static void assertResponse201(String url, Response response) {
		assertResponse(url, response, Response.Status.CREATED);
	}

	public static void assertResponse404(String url, Response response) {
		assertResponse(url, response, Response.Status.NOT_FOUND);
	}

	public static void assertResponse500(String url, Response response) {
		assertResponse(url, response, Response.Status.INTERNAL_SERVER_ERROR);
	}

	public static void assertResponse(String url, Response response, Response.Status status) {
		assertEquals("Incorrect response code from " + url, status.getStatusCode(), response.getStatus());
	}

	protected static Builder asAdmin(Builder builder) {
		return asUser(builder, "admin", "adminpwd");
	}

	protected static Builder asTestUser(Builder builder) {
		return asUser(builder, "testuser", "pwd");
	}

	protected static Builder asOrderer(Builder builder) {
		return asUser(builder, "orderer", "pwd");
	}
	
	protected static Builder asUser(Builder builder, String userName, String password) {
		return builder.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME, userName)
				.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD, password);
	}

	protected static Calendar constantDate() {
		Calendar calendar = Calendar.getInstance(Locale.GERMAN);
		calendar.set(Calendar.YEAR, 2019);
		calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		return calendar;
	}
}
