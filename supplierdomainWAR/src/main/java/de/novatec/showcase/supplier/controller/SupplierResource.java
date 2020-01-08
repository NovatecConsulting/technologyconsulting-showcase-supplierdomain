package de.novatec.showcase.supplier.controller;

import java.util.Collection;

import javax.annotation.ManagedBean;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.novatec.showcase.manufacture.dto.ComponentDemands;
import de.novatec.showcase.supplier.GlobalConstants;
import de.novatec.showcase.supplier.client.manufacture.RestcallException;
import de.novatec.showcase.supplier.dto.PurchaseOrder;
import de.novatec.showcase.supplier.dto.PurchaseOrderLine;
import de.novatec.showcase.supplier.dto.PurchaseOrderLinePK;
import de.novatec.showcase.supplier.dto.Supplier;
import de.novatec.showcase.supplier.dto.SupplierComponent;
import de.novatec.showcase.supplier.dto.SupplierComponentPK;
import de.novatec.showcase.supplier.ejb.session.NoValidSupplierFoundException;
import de.novatec.showcase.supplier.ejb.session.SupplierSessionLocal;
import de.novatec.showcase.supplier.mapper.DtoMapper;

@ManagedBean
@Path(value = "/supplier")
public class SupplierResource {

	@EJB
	private SupplierSessionLocal bean;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME})
	public Response createSupplier(Supplier supplier, @Context UriInfo uriInfo) {
		return Response.created(uriInfo.getAbsolutePathBuilder().build())
				.entity(DtoMapper.mapToSupplierDto(bean.createSupplier(DtoMapper.mapToSupplierEntity(supplier))))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	public Response getSupplier() {
		Collection<Supplier> supplier = DtoMapper.mapToSupplierDto(bean.getAllSuppliers());
		if (supplier == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No Supplier found!").build();
		}
		return Response.ok().entity(supplier).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "{id}")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	public Response getSupplier(@PathParam("id") Integer supplierId) {
		Supplier supplier = DtoMapper.mapToSupplierDto(bean.getSupplier(supplierId));
		if (supplier == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No Supplier with id " + supplierId + " found!")
					.build();
		}
		return Response.ok().entity(supplier).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME})
	@Path(value = "supplier_component")
	public Response createSupplierComponent(SupplierComponent supplierComponent, @Context UriInfo uriInfo) {
		return Response.created(uriInfo.getAbsolutePathBuilder().build())
				.entity(DtoMapper.mapToSupplierComponentDto(
						bean.createSupplierComponent(DtoMapper.mapToSupplierComponentEntity(supplierComponent))))
				.build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.PURCHASE_ROLE_NAME})
	@Path(value = "purchase")
	public Response purchase(ComponentDemands componentDemands, @Context UriInfo uriInfo) {
		Collection<PurchaseOrder> purchaseOrders;
		try {
			purchaseOrders = DtoMapper.mapToPurchaseOrderDto(bean.purchase(componentDemands));
		} catch (NoValidSupplierFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
		return Response.created(uriInfo.getAbsolutePathBuilder().build()).entity(purchaseOrders).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.PROCESS_DELIVERY_ROLE_NAME})
	@Path(value = "process_delivery/{poNumber}")
	public Response processDelivery(@PathParam("poNumber") Integer poNumber, @Context UriInfo uriInfo) {
		PurchaseOrder purchaseOrder = DtoMapper.mapToPurchaseOrderDto(bean.getPurchaseOrder(poNumber));
		if (purchaseOrder == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("PurchaseOrder with poNumber " + poNumber + " not found!").build();
		}
		try {
			bean.processDelivery(DtoMapper.mapToPurchaseOrderEntity(purchaseOrder));
		} catch (RestcallException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "purchase_order")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	public Response getPurchaseOrder() {
		Collection<PurchaseOrder> purchaseOrders = DtoMapper.mapToPurchaseOrderDto(bean.getAllPurchaseOrders());
		if (purchaseOrders == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No PurchaseOrder found!").build();
		}
		return Response.ok().entity(purchaseOrders).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "purchase_order/{poNumber}")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	public Response getPurchaseOrder(@PathParam("poNumber") Integer poNumber) {
		PurchaseOrder purchaseOrder = DtoMapper.mapToPurchaseOrderDto(bean.getPurchaseOrder(poNumber));
		if (purchaseOrder == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("PurchaseOrder with poNumber " + poNumber + " not found!").build();
		}
		return Response.ok().entity(purchaseOrder).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "purchase_order_line/{poNumber}/{polNumber}")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	public Response getPurchaseOrder(@PathParam("poNumber") Integer poNumber,
			@PathParam("polNumber") Integer polNumber) {
		PurchaseOrderLinePK purchaseorderLinePk = new PurchaseOrderLinePK(polNumber, poNumber);
		PurchaseOrderLine purchaseOrderLine = DtoMapper.mapToPurchaseOrderLineDto(
				bean.getPurchaseOrderLine(DtoMapper.mapToPurchaseOrderLinePKEntity(purchaseorderLinePk)));
		if (purchaseOrderLine == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(
					"PurchaseOrderLine with poNumber " + poNumber + " and polNumber " + polNumber + " not found!")
					.build();
		}
		return Response.ok().entity(purchaseOrderLine).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "supplier_component")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	public Response getSupplierComponent() {
		Collection<SupplierComponent> supplierComponents = DtoMapper
				.mapToSupplierComponentDto(bean.getAllSupplierComponents());
		if (supplierComponents == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No SupplierComponent found!").build();
		}
		return Response.ok().entity(supplierComponents).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "supplier_component/{supplierId}/{componentId}")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	public Response getSupplierComponent(@PathParam("supplierId") Integer supplierId,
			@PathParam("componentId") String componentId) {
		SupplierComponentPK supplierComponentPK = new SupplierComponentPK();
		supplierComponentPK.setComponentId(componentId);
		supplierComponentPK.setSupplierId(supplierId);
		SupplierComponent supplierComponent = DtoMapper.mapToSupplierComponentDto(
				bean.getSupplierComponent(DtoMapper.mapToSupplierComponentPKEntity(supplierComponentPK)));
		if (supplierComponent == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No SupplierComponent found!").build();
		}
		return Response.ok().entity(supplierComponent).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}