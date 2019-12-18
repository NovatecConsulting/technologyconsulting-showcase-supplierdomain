package de.novatec.showcase.supplier.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import de.novatec.showcase.manufacture.dto.ComponentDemand;
import de.novatec.showcase.manufacture.dto.ComponentDemands;
import de.novatec.showcase.supplier.dto.PurchaseOrder;
import de.novatec.showcase.supplier.dto.PurchaseOrderLine;
import de.novatec.showcase.supplier.dto.Supplier;
import de.novatec.showcase.supplier.dto.SupplierComponent;
import de.novatec.showcase.supplier.dto.SupplierComponentPK;

public class SupplierResourceIT extends ResourceItBase {

	@Test
	public void testGetSupplier() {
		for (Entry<String, Supplier> entry : dbSuppliers.entrySet()) {
			Supplier supplier = entry.getValue();
			WebTarget target = client.target(SUPPLIER_URL).path(supplier.getId().toString());
			Response response = asTestUser(target.request(MediaType.APPLICATION_JSON_TYPE)).get();
			assertResponse200(SUPPLIER_URL, response);
			assertEquals(supplier, response.readEntity(Supplier.class));
		}
	}

	@Test
	public void testGetSuppliers() {
		WebTarget target = client.target(SUPPLIER_URL);
		Response response = asTestUser(target.request(MediaType.APPLICATION_JSON_TYPE)).get();
		assertResponse200(SUPPLIER_URL, response);
		assertTrue("There should be " + dbSuppliers.size() + " Suppliers at a minimum!",
				response.readEntity(new GenericType<List<?>>() {
				}).size() >= dbSuppliers.size());
	}

	@Test
	public void testGetSupplierComponents() {
		WebTarget target = client.target(SUPPLIER_COMPONENT_URL);
		Response response = asTestUser(target.request(MediaType.APPLICATION_JSON_TYPE)).get();
		assertResponse200(SUPPLIER_COMPONENT_URL, response);
		assertTrue("There should be " + dbSupplierComponents.size() + " SupplierComponents at a minimum!",
				response.readEntity(new GenericType<List<?>>() {
				}).size() >= dbSupplierComponents.size());
	}

	@Test
	public void testGetSupplierComponent() {
		for (Entry<SupplierComponentPK, SupplierComponent> entry : dbSupplierComponents.entrySet()) {
			SupplierComponent supplierComponent = entry.getValue();
			SupplierComponentPK pk = entry.getKey();
			WebTarget target = client.target(SUPPLIER_COMPONENT_URL)
					.path(pk.getSupplierId() + "/" + pk.getComponentId());
			Response response = asTestUser(target.request(MediaType.APPLICATION_JSON_TYPE)).get();
			assertResponse200(SUPPLIER_COMPONENT_URL, response);
			assertEquals(supplierComponent, response.readEntity(SupplierComponent.class));
		}
	}

	@Test
	public void testPurchaseAndProcessDelivery() {
		ComponentDemands componentDemands = new ComponentDemands().setComponentDemands(
				Arrays.asList(new ComponentDemand(COMPONENT_ID_1, 10, 1), new ComponentDemand(COMPONENT_ID_2, 5, 1)));
		WebTarget target = client.target(SUPPLIER_URL).path("purchase");
		Response response = asOrderer(target.request(MediaType.APPLICATION_JSON_TYPE))
				.post(Entity.json(componentDemands));
		assertResponse201(SUPPLIER_URL, response);
		List<PurchaseOrder> purchaseOrders = response.readEntity(new GenericType<List<PurchaseOrder>>() {});
		assertEquals("There should be one PurchaseOrder!", 1, purchaseOrders.size());
		assertEquals("There should be tw PurchaseOrderLines!", 2, purchaseOrders.get(0).getPurchaseOrderlines().size());

		target = client.target(PURCHASE_ORDER_URL)
				.path(Integer.valueOf(purchaseOrders.get(0).getPoNumber()).toString());
		response = asTestUser(target.request(MediaType.APPLICATION_JSON_TYPE)).get();
		assertResponse200(PURCHASE_ORDER_URL, response);
		PurchaseOrder purchaseOrder = response.readEntity(PurchaseOrder.class);
		assertEquals("PurchaseOrders should be the same!", purchaseOrders.get(0), purchaseOrder);
		assertEquals("There should be tw PurchaseOrderLines!", 2, purchaseOrder.getPurchaseOrderlines().size());
		assertEquals("PurchaseOrderLines should be the same!", purchaseOrders.get(0).getPurchaseOrderlines(),
				purchaseOrder.getPurchaseOrderlines());
		
		for (PurchaseOrderLine purchaseOrderLine : purchaseOrders.get(0).getPurchaseOrderlines()) {
			target = client.target(PURCHASE_ORDER_LINE_URL).path(purchaseOrderLine.getPk().getPoNumber()+"/"+purchaseOrderLine.getPk().getPolNumber());
			response = asTestUser(target.request(MediaType.APPLICATION_JSON_TYPE)).get();
			assertResponse200(PURCHASE_ORDER_LINE_URL, response);
			assertEquals("PurchaseOrderLines should be the same!", purchaseOrderLine, response.readEntity(PurchaseOrderLine.class));
		}
		
		target = client.target(SUPPLIER_URL).path("process_delivery/" + purchaseOrder.getPoNumber());
		response = asTestUser(target.request(MediaType.APPLICATION_JSON_TYPE)).method(HttpMethod.POST);
		
		target = client.target(PURCHASE_ORDER_URL)
				.path(Integer.valueOf(purchaseOrders.get(0).getPoNumber()).toString());
		response = asTestUser(target.request(MediaType.APPLICATION_JSON_TYPE)).get();
		assertResponse200(PURCHASE_ORDER_URL, response);
		purchaseOrder = response.readEntity(PurchaseOrder.class);
		assertNotEquals("PurchaseOrders should NOT be the same!", purchaseOrders.get(0), purchaseOrder);
		assertNotNull("PurchaseOrder sent Data should NOT be null!", purchaseOrder.getSentDate());
		
	}
}
