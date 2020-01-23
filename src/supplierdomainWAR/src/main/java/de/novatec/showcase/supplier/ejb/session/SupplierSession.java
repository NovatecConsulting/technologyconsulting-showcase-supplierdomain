package de.novatec.showcase.supplier.ejb.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.novatec.showcase.supplier.dto.ComponentDemand;
import de.novatec.showcase.supplier.dto.ComponentDemands;
import de.novatec.showcase.supplier.client.manufacture.ComponentDemandDeliverer;
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
import de.novatec.showcase.supplier.util.RandomTypes;;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
public class SupplierSession implements SupplierSessionLocal {

	private static Logger log = LoggerFactory.getLogger(SupplierSession.class);

	@PersistenceContext
	private EntityManager em;
	
	private ComponentDemandDeliverer componentDemandDeliverer = new ComponentDemandDeliverer();

	private Supplier findSupplier(ComponentDemand componentDemand) throws NoValidSupplierFoundException, SupplierNotFoundException {
		TypedQuery<SupplierComponent> query = em.createNamedQuery(SupplierComponent.FIND_SUPPCOMPONENT_BY_COMPONENT_ID,
				SupplierComponent.class);
		query.setParameter("id", componentDemand.getComponentId());
		List<SupplierComponent> supplierComponents = query.getResultList();
		
		if(supplierComponents.isEmpty())
		{
			throw new NoValidSupplierFoundException("No valid Supplier found for " + componentDemand + "!");
		}
		else
		{
			if (supplierComponents.size() == 1) {
				return this.getSupplier(supplierComponents.get(0).getSupplierId());
			}

			// Sort by price
			supplierComponents.sort(new Comparator<SupplierComponent>() {

				@Override
				public int compare(SupplierComponent o1, SupplierComponent o2) {
					return o1.getPrice().compareTo(o2.getPrice());
				}
			});

			// init with lowest price
			SupplierComponent supplierComponent = supplierComponents.get(0);
			// check if we can get an discount
			for (SupplierComponent currentSupplierComponent : supplierComponents) {
				if (componentDemand.getQuantity() > currentSupplierComponent.getQuantityForDiscount()) {
					if (supplierComponent == null
							|| currentSupplierComponent.getDiscount().compareTo(supplierComponent.getDiscount()) > 0) {
						supplierComponent = currentSupplierComponent;
					}
				}
			}
			return em.find(Supplier.class, supplierComponent.getSupplierId());
		}
	}

	@SuppressWarnings("unused")
	private SComponent findComponent(Integer id) {
		return em.find(SComponent.class, id);
	}

	@Override
	public PurchaseOrder getPurchaseOrder(Integer poNumber) throws PurchaseOrderNotFoundException {
		PurchaseOrder purchaseOrder = em.find(PurchaseOrder.class, poNumber);
		if(purchaseOrder == null)
		{
			throw new PurchaseOrderNotFoundException("The PurchaseOrder with id "+poNumber+"was not found!");
		}
		return purchaseOrder;
	}
	

	@Override
	public PurchaseOrderLine getPurchaseOrderLine(PurchaseOrderLinePK purchaseOrderLinePk) throws PurchaseOrderLineNotFoundException {
		PurchaseOrderLine purchaseOrderLine = em.find(PurchaseOrderLine.class, purchaseOrderLinePk);
		if (purchaseOrderLine == null) {
			throw new PurchaseOrderLineNotFoundException(
					"The PurchaseOrderLine with PurchaseOrderLinePK " + purchaseOrderLinePk + "was not found!");
		}
		return purchaseOrderLine;
	}

	public Collection<SComponent> getAllSComponent() {
		return em.createNamedQuery(SComponent.ALL_SCOMPONENT, SComponent.class).getResultList();
	}

	@Override
	public Collection<PurchaseOrder> getAllPurchaseOrders() {
		return em.createNamedQuery(PurchaseOrder.FIND_ALL_PURCHASEORDER, PurchaseOrder.class).getResultList();
	}

	private PurchaseOrderLine createPurchaseOrderLine(PurchaseOrder purchaseOrder, SupplierComponent supplierComponent,
			ComponentDemand componentDemand) {
		Calendar since = Calendar.getInstance();
		since.set(2002, Calendar.JANUARY, 12);

		Calendar to = Calendar.getInstance();
		to.setTimeInMillis(System.currentTimeMillis());

		PurchaseOrderLine popurchaseOrderLine = new PurchaseOrderLine(getNextPolNumber(), purchaseOrder.getPoNumber(),
				componentDemand.getLocation(), supplierComponent.getComponentId(),
				componentDemand.getQuantity(), BigDecimal.valueOf(RandomTypes.getDouble(1, 10000)),
				new java.sql.Date((RandomTypes.getDate(since, to)).getTimeInMillis()),
				RandomTypes.getString("COMMENT_", 20, 100), RandomTypes.getInt(1, 100), purchaseOrder);

		return popurchaseOrderLine;
	}

	private int getNextPolNumber() {
		TypedQuery<Long> query = em.createNamedQuery(PurchaseOrderLine.COUNT_PURCHASEORDERLINE, Long.class);
		int nextPolNumber = query.getSingleResult().intValue();
		return ++nextPolNumber;
	}

	@Override
	public Collection<Supplier> getAllSuppliers() {
		return em.createNamedQuery(Supplier.ALL_SUPPLIERS, Supplier.class).getResultList();
	}

	@Override
	public Supplier getSupplier(Integer supplierId) throws SupplierNotFoundException {
		Supplier supplier = em.find(Supplier.class, supplierId);
		if( supplier == null )
		{
			throw new SupplierNotFoundException("The Supplier with id " + supplierId + " was not found!");
		}
		return supplier;
	}

	@Override
	public Collection<SupplierComponent> getAllSupplierComponents() {
		return em.createNamedQuery(SupplierComponent.FIND_ALL_SUPPCOMPONENT, SupplierComponent.class).getResultList();
	}

	@Override
	public SupplierComponent getSupplierComponent(SupplierComponentPK supplierComponentPK) throws SupplierComponentNotFoundException {
		SupplierComponent supplierComponent = em.find(SupplierComponent.class, supplierComponentPK);
		if(supplierComponent == null )
		{
			throw new SupplierComponentNotFoundException("The Supplier with SupplierComponentPK " + supplierComponentPK + " was not found!");
		}
		return supplierComponent;
	}

	@Override
	public boolean setSupplierURLs() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAllSupplierURLs() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Supplier createSupplier(Supplier supplier) {
		em.persist(supplier);
		return supplier;
	}

	/**
	 * Throws NoValidSupplierFoundException if for one of the ComponentDemands no valid Supplier is found
	 * TODO: refactor, so that there will be a return object which shows the processed ComponentDemands with Supplier and the
	 * not processed ComponentDemands without Supplier, so that the caller can decide what to do with the not processed
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Collection<PurchaseOrder> purchase(ComponentDemands componentDemands) throws NoValidSupplierFoundException, SupplierNotFoundException {

		Map<Integer, PurchaseOrder> purchaseOrders = new HashMap<Integer, PurchaseOrder>();

		for (ComponentDemand componentDemand : componentDemands.getComponentDemands()) {
			Supplier supplier = findSupplier(componentDemand);
			PurchaseOrder purchaseOrder = null;
			if (!purchaseOrders.containsKey(supplier.getId())) {
				purchaseOrder = createPurchaseOrder(supplier);
				purchaseOrders.put(supplier.getId(), purchaseOrder);
				em.persist(purchaseOrder);
			}
			purchaseOrder = purchaseOrders.get(supplier.getId());
			SupplierComponent supplierComponent = em.find(SupplierComponent.class, new SupplierComponentPK(componentDemand.getComponentId(), supplier.getId()));
			PurchaseOrderLine purchaseOrderLine = createPurchaseOrderLine(purchaseOrder, supplierComponent,
					componentDemand);
			purchaseOrder.addPurchaseOrderLine(purchaseOrderLine);
			em.persist(purchaseOrderLine);
		}
		return purchaseOrders.values();
	}

	private PurchaseOrder createPurchaseOrder(Supplier supplier) {
		Calendar from = Calendar.getInstance();
		from.set(2002, 0, 1);

		Calendar to = Calendar.getInstance();
		to.setTimeInMillis(System.currentTimeMillis());

		Calendar date = RandomTypes.getDate(from, to);
		date.add(Calendar.DAY_OF_MONTH, RandomTypes.getInt(1, 1000));

		PurchaseOrder purchaseOrder = new PurchaseOrder(RandomTypes.getInt(1, 100), supplier.getId(),
				new java.sql.Timestamp((RandomTypes.getDate(from, to)).getTimeInMillis()));
		return purchaseOrder;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SupplierComponent createSupplierComponent(SupplierComponent supplierComponent) {
		em.persist(supplierComponent);
		return supplierComponent;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PurchaseOrder processDelivery(PurchaseOrder purchaseOrder) throws RestcallException {
		List<ComponentDemand> componentDemands = new ArrayList<ComponentDemand>();
		for (PurchaseOrderLine purchaseOrderLine : purchaseOrder.getPurchaseOrderlines()) {
			componentDemands.add(new ComponentDemand(purchaseOrderLine.getPartNumber(),
					purchaseOrderLine.getOrderedQuantity(), purchaseOrderLine.getDeliveryLocation()));
		}
		purchaseOrder.setSentDate(Calendar.getInstance().getTime());
		purchaseOrder = em.merge(purchaseOrder);
		try {
			componentDemandDeliverer.deliver(componentDemands);
		} catch (RestcallException e) {
			log.error(e.getMessage());
			throw e;
		}
		
		return purchaseOrder;
	}
}