package de.novatec.showcase.supplier.mapper;

import java.util.Collection;

import de.novatec.showcase.supplier.dto.Address;
import de.novatec.showcase.supplier.dto.PurchaseOrder;
import de.novatec.showcase.supplier.dto.PurchaseOrderLine;
import de.novatec.showcase.supplier.dto.PurchaseOrderLinePK;
import de.novatec.showcase.supplier.dto.Supplier;
import de.novatec.showcase.supplier.dto.SupplierComponent;
import de.novatec.showcase.supplier.dto.SupplierComponentPK;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

abstract public class DtoMapper {
	private static MapperFactory mapperFactory;
	private static MapperFacade mapper;

	static {
		mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Supplier.class, de.novatec.showcase.supplier.ejb.entity.Supplier.class ).byDefault().register();
		mapperFactory.classMap(Address.class, de.novatec.showcase.supplier.ejb.entity.Address.class ).byDefault().register();
		mapperFactory.classMap(SupplierComponent.class, de.novatec.showcase.supplier.ejb.entity.SupplierComponent.class ).byDefault().register();
		mapperFactory.classMap(SupplierComponentPK.class, de.novatec.showcase.supplier.ejb.entity.SupplierComponentPK.class ).byDefault().register();
		mapperFactory.classMap(PurchaseOrder.class, de.novatec.showcase.supplier.ejb.entity.PurchaseOrder.class ).byDefault().register();
		mapperFactory.classMap(PurchaseOrderLine.class, de.novatec.showcase.supplier.ejb.entity.PurchaseOrderLine.class ).byDefault().register();
		mapperFactory.classMap(PurchaseOrderLinePK.class, de.novatec.showcase.supplier.ejb.entity.PurchaseOrderLinePK.class ).byDefault().register();
		mapper = mapperFactory.getMapperFacade();
	}

	public static Collection<Supplier> mapToSupplierDto(
			Collection<de.novatec.showcase.supplier.ejb.entity.Supplier> supplier) {
		return mapper.mapAsList(supplier, Supplier.class);
	}

	public static Collection<SupplierComponent> mapToSupplierComponentDto(
			Collection<de.novatec.showcase.supplier.ejb.entity.SupplierComponent> supplierComponent) {
		return mapper.mapAsList(supplierComponent, SupplierComponent.class);
	}
	
	public static Collection<PurchaseOrder> mapToPurchaseOrderDto(
			Collection<de.novatec.showcase.supplier.ejb.entity.PurchaseOrder> purchaseOrder) {
		return mapper.mapAsList(purchaseOrder, PurchaseOrder.class);
	}
	
	public static PurchaseOrder mapToPurchaseOrderDto(de.novatec.showcase.supplier.ejb.entity.PurchaseOrder purchaseOrder) {
		return mapper.map(purchaseOrder, PurchaseOrder.class);
	}
	
	public static PurchaseOrderLine mapToPurchaseOrderLineDto(de.novatec.showcase.supplier.ejb.entity.PurchaseOrderLine purchaseOrderLine) {
		return mapper.map(purchaseOrderLine, PurchaseOrderLine.class);
	}
	
	public static Supplier mapToSupplierDto(de.novatec.showcase.supplier.ejb.entity.Supplier supplier) {
		return mapper.map(supplier, Supplier.class);
	}
	

	public static SupplierComponent mapToSupplierComponentDto(de.novatec.showcase.supplier.ejb.entity.SupplierComponent supplierComponent) {
		return mapper.map(supplierComponent, SupplierComponent.class);
	}
	
	public static de.novatec.showcase.supplier.ejb.entity.Supplier mapToSupplierEntity(Supplier supplier) {
		return mapper.map(supplier, de.novatec.showcase.supplier.ejb.entity.Supplier.class);
	}

	public static de.novatec.showcase.supplier.ejb.entity.SupplierComponent mapToSupplierComponentEntity(SupplierComponent supplierComponent) {
		return mapper.map(supplierComponent, de.novatec.showcase.supplier.ejb.entity.SupplierComponent.class);
	}
	
	public static de.novatec.showcase.supplier.ejb.entity.SupplierComponentPK mapToSupplierComponentPKEntity(SupplierComponentPK supplierComponentPk) {
		return mapper.map(supplierComponentPk, de.novatec.showcase.supplier.ejb.entity.SupplierComponentPK.class);
	}
	
	public static de.novatec.showcase.supplier.ejb.entity.PurchaseOrder mapToPurchaseOrderEntity(PurchaseOrder purchaseOrder) {
		return mapper.map(purchaseOrder, de.novatec.showcase.supplier.ejb.entity.PurchaseOrder.class);
	}

	public static de.novatec.showcase.supplier.ejb.entity.PurchaseOrderLinePK mapToPurchaseOrderLinePKEntity(PurchaseOrderLinePK purchaseOrderLinePk) {
		return mapper.map(purchaseOrderLinePk, de.novatec.showcase.supplier.ejb.entity.PurchaseOrderLinePK.class);
	}
}
