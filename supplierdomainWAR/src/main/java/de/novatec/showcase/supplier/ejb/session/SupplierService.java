package de.novatec.showcase.supplier.ejb.session;

import java.util.Collection;

import de.novatec.showcase.manufacture.dto.ComponentDemands;
import de.novatec.showcase.supplier.ejb.entity.PurchaseOrder;
import de.novatec.showcase.supplier.ejb.entity.PurchaseOrderLine;
import de.novatec.showcase.supplier.ejb.entity.PurchaseOrderLinePK;
import de.novatec.showcase.supplier.ejb.entity.SComponent;
import de.novatec.showcase.supplier.ejb.entity.Supplier;
import de.novatec.showcase.supplier.ejb.entity.SupplierComponent;
import de.novatec.showcase.supplier.ejb.entity.SupplierComponentPK;

public interface SupplierService {

	public Collection<SComponent> getAllSComponent();

	public boolean setSupplierURLs();

	public boolean setAllSupplierURLs();

	//--------
	
	Supplier createSupplier(Supplier supplier);

	SupplierComponent createSupplierComponent(SupplierComponent supplierComponent);

	public Collection<Supplier> getAllSuppliers();

	Supplier getSupplier(Integer supplierId);
	
	public Collection<SupplierComponent> getAllSupplierComponents();

	public SupplierComponent getSupplierComponent(SupplierComponentPK supplierComponentPK);

	public Collection<PurchaseOrder> getAllPurchaseOrders();

	PurchaseOrder getPurchaseOrder(Integer poNumber);

	PurchaseOrderLine getPurchaseOrderLine(PurchaseOrderLinePK purchaseOrderLinePk);

	void purchase(ComponentDemands componentDemands) throws NoValidSupplierFoundException;

	void processDelivery(PurchaseOrder purchaseOrder);
}
