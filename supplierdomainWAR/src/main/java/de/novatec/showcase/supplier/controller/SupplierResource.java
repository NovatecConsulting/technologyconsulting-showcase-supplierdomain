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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

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
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "201",
	                description = "The new created supplier.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(implementation = Supplier.class))) })
		@RequestBody(
            name="supplier",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = Supplier.class)),
            required = true,
            description = "example of a supplier"
        )
	@Operation(
			summary = "Create an new supplier",
			description = "Create an new suplier for from the given suppier object.")
	public Response createSupplier(Supplier supplier, @Context UriInfo uriInfo) {
		return Response.created(uriInfo.getAbsolutePathBuilder().build())
				.entity(DtoMapper.mapToSupplierDto(bean.createSupplier(DtoMapper.mapToSupplierEntity(supplier))))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "404",
	                description = "No Supplier found",
	                content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	                responseCode = "200",
	                description = "The available Suppliers.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(type = SchemaType.ARRAY, implementation = Supplier.class))) })
	    @Operation(
	        summary = "Get the suppliers",
	        description = "Get the available suppliers.")
	public Response getSuppliers() {
		Collection<Supplier> supplier = DtoMapper.mapToSupplierDto(bean.getAllSuppliers());
		if (supplier == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No Supplier found!").type(MediaType.TEXT_PLAIN_TYPE).build();
		}
		return Response.ok().entity(supplier).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "{id}")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "404",
	                description = "Supplier not found",
	                content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	            		responseCode = "500",
	            		description = "Supplier id is less than 1",
	            		content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	                responseCode = "200",
	                description = "The supplier with the given id.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(implementation = Supplier.class))) })
	    @Operation(
	        summary = "Get the supplier by id",
	        description = "Get the supplier by id where the id has to be higher than 0.")
	public Response getSupplier(
			@Parameter(
		            description = "The id of the supplier which should be retrieved.",
		            required = true,
		            example = "1",
		            schema = @Schema(type = SchemaType.INTEGER)) 
			@PathParam("id") Integer supplierId) {
		if (supplierId.intValue() <= 0) {
			return Response.serverError().entity("Id cannot be less than 1!").type(MediaType.TEXT_PLAIN_TYPE).build();
		}
		Supplier supplier = DtoMapper.mapToSupplierDto(bean.getSupplier(supplierId));
		if (supplier == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No Supplier with id " + supplierId + " found!")
					.type(MediaType.TEXT_PLAIN).build();
		}
		return Response.ok().entity(supplier).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME})
	@Path(value = "supplier_component")
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "201",
	                description = "The new created supplier component.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(implementation = SupplierComponent.class))) })
		@RequestBody(
            name="supplierComponent",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = SupplierComponent.class)),
            required = true,
            description = "example of a supplier component"
        )
	@Operation(
			summary = "Create an new supplier component",
			description = "Create an new suplier component for from the given suppier object.")
	public Response createSupplierComponent(SupplierComponent supplierComponent, @Context UriInfo uriInfo) {
		return Response.created(uriInfo.getAbsolutePathBuilder().build())
				.entity(DtoMapper.mapToSupplierComponentDto(
						bean.createSupplierComponent(DtoMapper.mapToSupplierComponentEntity(supplierComponent))))
						.type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.PURCHASE_ROLE_NAME})
	@Path(value = "purchase")
	@APIResponses(
	        value = {
		        @APIResponse(
			        responseCode = "404",
			        description = "No valid supplier found",
			        content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	                responseCode = "201",
	                description = "New purchase orders are created.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(type = SchemaType.ARRAY, implementation = PurchaseOrder.class))) })
		@RequestBody(
            name="componentDemands",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = ComponentDemands.class)),
            required = true,
            description = "example of a list of component demands"
        )
	@Operation(
			summary = "Do a purchase",
			description = "Do a purchase with a list of component demands.")
	public Response purchase(ComponentDemands componentDemands, @Context UriInfo uriInfo) {
		Collection<PurchaseOrder> purchaseOrders;
		try {
			purchaseOrders = DtoMapper.mapToPurchaseOrderDto(bean.purchase(componentDemands));
		} catch (NoValidSupplierFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
		}
		return Response.created(uriInfo.getAbsolutePathBuilder().build()).type(MediaType.APPLICATION_JSON_TYPE).entity(purchaseOrders).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.PROCESS_DELIVERY_ROLE_NAME})
	@Path(value = "process_delivery/{poNumber}")
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "404",
	                description = "PurchaseOrder not found",
	                content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	            		responseCode = "500",
	            		description = "Rest call to order domain is failing",
	            		content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	                responseCode = "200",
	                description = "The purchase order is delivered.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(implementation = Supplier.class))) })
	    @Operation(
	        summary = "Get the supplier by id",
	        description = "Get the supplier by id where the id has to be higher than 0.")
	public Response processDelivery(
			@Parameter(
		            description = "The purchase order number of the PurchaseOrder.",
		            required = true,
		            example = "1",
		            schema = @Schema(type = SchemaType.INTEGER)) 
			@PathParam("poNumber") Integer poNumber, @Context UriInfo uriInfo) {
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
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "404",
	                description = "No PurchaseOrder not found",
	                content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	                responseCode = "200",
	                description = "The available PurchaseOrders.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(implementation = Supplier.class))) })
	    @Operation(
	        summary = "Get the purchase orders",
	        description = "Get the available purchase orders.")
	public Response getPurchaseOrders() {
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
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "404",
	                description = "PurchaseOrder not found",
	                content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	            		responseCode = "500",
	            		description = "PurchaseOrder id is less than 1",
	            		content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	                responseCode = "200",
	                description = "The PurchaseOrder with the given id.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(implementation = Supplier.class))) })
	    @Operation(
	        summary = "Get the PurchaseOrder by id",
	        description = "Get the PurchaseOrder by id where the id has to be higher than 0.")
	public Response getPurchaseOrder(
			@Parameter(
		            description = "The poNumber of the PurchaseOrder which should be retrieved.",
		            required = true,
		            example = "1",
		            schema = @Schema(type = SchemaType.INTEGER)) 
			@PathParam("poNumber") Integer poNumber) {
		PurchaseOrder purchaseOrder = DtoMapper.mapToPurchaseOrderDto(bean.getPurchaseOrder(poNumber));
		if (purchaseOrder == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("PurchaseOrder with poNumber " + poNumber + " not found!").type(MediaType.TEXT_PLAIN_TYPE).build();
		}
		return Response.ok().entity(purchaseOrder).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "purchase_order_line/{poNumber}/{polNumber}")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "404",
	                description = "PurchaseOrderLine not found",
	                content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	            		responseCode = "500",
	            		description = "PurchaseOrderLine polNumber is less than 1 and/or polNumber is less than 1",
	            		content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	                responseCode = "200",
	                description = "The PurchaseOrderline with the given polNumber and poNumber.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(implementation = Supplier.class))) })
	    @Operation(
	        summary = "Get the PurchaseOrderLine by poNumber and polNumber",
	        description = "Get the PurchaseOrderLine by poNumber/polNumber where the poNumber/polNumber has to be higher than 0.")
	public Response getPurchaseOrderLine(
			@Parameter(
		            description = "The poNumber of the PurchaseOrderLine which should be retrieved.",
		            required = true,
		            example = "1",
		            schema = @Schema(type = SchemaType.INTEGER)) 
			@PathParam("poNumber") Integer poNumber,
			@Parameter(
		            description = "The polNumber of the PurchaseOrderLIne which should be retrieved.",
		            required = true,
		            example = "1",
		            schema = @Schema(type = SchemaType.INTEGER)) 
			@PathParam("polNumber") Integer polNumber) {
		if (poNumber.intValue() <= 0 || polNumber.intValue() <= 0) {
			return Response.serverError().entity("poNumber/polNumber cannot be less than 1!").type(MediaType.TEXT_PLAIN_TYPE).build();
		}
		PurchaseOrderLinePK purchaseorderLinePk = new PurchaseOrderLinePK(polNumber, poNumber);
		PurchaseOrderLine purchaseOrderLine = DtoMapper.mapToPurchaseOrderLineDto(
				bean.getPurchaseOrderLine(DtoMapper.mapToPurchaseOrderLinePKEntity(purchaseorderLinePk)));
		if (purchaseOrderLine == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(
					"PurchaseOrderLine with poNumber " + poNumber + " and polNumber " + polNumber + " not found!")
					.type(MediaType.TEXT_PLAIN_TYPE).build();
		}
		return Response.ok().entity(purchaseOrderLine).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "supplier_component")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "404",
	                description = "No SupplierComponent found",
	                content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	                responseCode = "200",
	                description = "The SupplierComponents.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(type = SchemaType.ARRAY, implementation = Supplier.class))) })
	    @Operation(
	        summary = "Get the SupplierComponent",
	        description = "Get the available SupplierComponents.")
	public Response getSupplierComponents() {
		Collection<SupplierComponent> supplierComponents = DtoMapper
				.mapToSupplierComponentDto(bean.getAllSupplierComponents());
		if (supplierComponents == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No SupplierComponent found!").type(MediaType.TEXT_PLAIN_TYPE).build();
		}
		return Response.ok().entity(supplierComponents).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "supplier_component/{supplierId}/{componentId}")
	@RolesAllowed({GlobalConstants.ADMIN_ROLE_NAME, GlobalConstants.SUPPLIER_READ_ROLE_NAME})
	@APIResponses(
	        value = {
	            @APIResponse(
	                responseCode = "404",
	                description = "SupplierComponent not found",
	                content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	            		responseCode = "500",
	            		description = "SupplierComponent supplierId is less than 1 and/or componentId is less than 1",
	            		content = @Content(mediaType = MediaType.TEXT_PLAIN)),
	            @APIResponse(
	                responseCode = "200",
	                description = "The SupplierComponent with the given supplierId and componentId.",
	                content = @Content(mediaType = MediaType.APPLICATION_JSON,
	                schema = @Schema(implementation = Supplier.class))) })
	    @Operation(
	        summary = "Get the SupplierComponent by supplierId and polNumber",
	        description = "Get the SupplierComponent by supplierId/componentId where the supplierId/componentId has to be higher than 0.")
	public Response getSupplierComponent(
			@Parameter(
		            description = "The supplierId of the SupplierComponent which should be retrieved.",
		            required = true,
		            example = "1",
		            schema = @Schema(type = SchemaType.INTEGER)) 
			@PathParam("supplierId") Integer supplierId,
			@Parameter(
		            description = "The componentId of the SupplierComponent which should be retrieved.",
		            required = true,
		            example = "1",
		            schema = @Schema(type = SchemaType.INTEGER)) 
			@PathParam("componentId") String componentId) {
		if (supplierId.intValue() <= 0 || Integer.valueOf(supplierId).intValue() <= 0) {
			return Response.serverError().entity("supplierId/componentId cannot be less than 1!").type(MediaType.TEXT_PLAIN_TYPE).build();
		}
		SupplierComponentPK supplierComponentPK = new SupplierComponentPK();
		supplierComponentPK.setComponentId(componentId);
		supplierComponentPK.setSupplierId(supplierId);
		SupplierComponent supplierComponent = DtoMapper.mapToSupplierComponentDto(
				bean.getSupplierComponent(DtoMapper.mapToSupplierComponentPKEntity(supplierComponentPK)));
		if (supplierComponent == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No SupplierComponent found!").type(MediaType.TEXT_PLAIN_TYPE).build();
		}
		return Response.ok().entity(supplierComponent).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}