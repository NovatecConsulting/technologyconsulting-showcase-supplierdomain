package de.novatec.showcase.supplier.ejb.session;

import java.util.Collection;

import de.novatec.showcase.supplier.dto.ComponentDemands;
import de.novatec.showcase.supplier.client.manufacture.RestcallException;
import de.novatec.showcase.supplier.ejb.entity.PurchaseOrder;
import de.novatec.showcase.supplier.ejb.entity.PurchaseOrderLine;
import de.novatec.showcase.supplier.ejb.entity.PurchaseOrderLinePK;
import de.novatec.showcase.supplier.ejb.entity.SComponent;
import de.novatec.showcase.supplier.ejb.entity.Supplier;
import de.novatec.showcase.supplier.ejb.entity.SupplierComponent;
import de.novatec.showcase.supplier.ejb.entity.SupplierComponentPK;
import de.novatec.showcase.supplier.ejb.session.exception.NoValidSupplierFoundException;
import de.novatec.showcase.supplier.ejb.session.exception.PurchaseOrderLineNotFoundException;
import de.novatec.showcase.supplier.ejb.session.exception.PurchaseOrderNotFoundException;
import de.novatec.showcase.supplier.ejb.session.exception.SupplierComponentNotFoundException;
import de.novatec.showcase.supplier.ejb.session.exception.SupplierNotFoundException;

public interface SupplierService {

	public Collection<SComponent> getAllSComponent();

	public boolean setSupplierURLs();

	public boolean setAllSupplierURLs();

	//--------
	
	Supplier createSupplier(Supplier supplier);

	SupplierComponent createSupplierComponent(SupplierComponent supplierComponent);

	public Collection<Supplier> getAllSuppliers();

	Supplier getSupplier(Integer supplierId) throws SupplierNotFoundException;
	
	public Collection<SupplierComponent> getAllSupplierComponents();

	public SupplierComponent getSupplierComponent(SupplierComponentPK supplierComponentPK) throws SupplierComponentNotFoundException;

	public Collection<PurchaseOrder> getAllPurchaseOrders();

	PurchaseOrder getPurchaseOrder(Integer poNumber) throws PurchaseOrderNotFoundException;

	PurchaseOrderLine getPurchaseOrderLine(PurchaseOrderLinePK purchaseOrderLinePk) throws PurchaseOrderLineNotFoundException;

	Collection<PurchaseOrder> purchase(ComponentDemands componentDemands) throws NoValidSupplierFoundException, SupplierNotFoundException;

	PurchaseOrder processDelivery(PurchaseOrder purchaseOrder) throws RestcallException;
}
