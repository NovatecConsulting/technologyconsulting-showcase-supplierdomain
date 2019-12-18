package de.novatec.showcase.supplier.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="S_PURCHASE_ORDERLINE")
	@NamedQueries(value ={@NamedQuery(name = PurchaseOrderLine.COUNT_PURCHASEORDERLINE, query = PurchaseOrderLine.COUNT_PURCHASEORDERLINE_QUERY),
			@NamedQuery(name = PurchaseOrderLine.FIND_PURCHASEORDERLINE_BY_COMPONENT_ID, query = PurchaseOrderLine.FIND_PURCHASEORDERLINE_BY_COMPONENT_ID_QUERY)
	}
)
public class PurchaseOrderLine implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	public static final String COUNT_PURCHASEORDERLINE = "COUNT_PURCHASEORDERLINE";
	
	public static final String FIND_PURCHASEORDERLINE_BY_COMPONENT_ID = "FIND_PURCHASEORDERLINE_BY_COMPONENT_ID";

	public static final String COUNT_PURCHASEORDERLINE_QUERY = "SELECT COUNT(po) FROM PurchaseOrderLine po";
	
	public static final String FIND_PURCHASEORDERLINE_BY_COMPONENT_ID_QUERY = "SELECT pol FROM PurchaseOrderLine pol WHERE pol.partNumber = :id";

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "poNumber", column = @Column(name = "POL_PO_ID")),
		@AttributeOverride(name = "polNumber", column = @Column(name = "POL_NUMBER")) })
	private PurchaseOrderLinePK pk;

	@Column(name="POL_BALANCE")
	private BigDecimal outstandingBalance;

    @Temporal( TemporalType.DATE)
	@Column(name="POL_DELDATE")
	private Date requestedDeliveryDate;

	@Column(name="POL_LEADTIME")
	private int leadtime;

	@Column(name="POL_LOCATION")
	private int deliveryLocation;

	@Column(name="POL_MESSAGE")
	private String optionalComment;

	@Column(name="POL_P_ID")
	private String partNumber;

	@Column(name="POL_QTY")
	private int orderedQuantity;

	@Column(name="POL_VERSION")
	private int version;

    @ManyToOne
	@JoinColumn(name="POL_PO_ID", insertable = false, updatable = false)
	private PurchaseOrder purchaseOrder;

    
    public PurchaseOrderLine()
    {
    	super();
    }
    
	public PurchaseOrderLine(Integer polNumber, Integer poNumber,
			int deliveryLocation,String partNumber, int orderedQuantity,
			BigDecimal outstandingBalance, Date requestedDeliveryDate,
			String optionalComment,int leadtime,int version,
			PurchaseOrder purchaseOrder)
	{
		super();
		this.pk = new PurchaseOrderLinePK();
		this.pk.setPolNumber(polNumber);
		this.pk.setPoNumber(poNumber);
		this.outstandingBalance = outstandingBalance;
		this.requestedDeliveryDate = requestedDeliveryDate;
		this.leadtime = leadtime;
		this.deliveryLocation = deliveryLocation;
		this.optionalComment = optionalComment;
		this.partNumber = partNumber;
		this.orderedQuantity = orderedQuantity;
		this.version = version;
		this.purchaseOrder = purchaseOrder;
	}


	public PurchaseOrderLinePK getPk() {
		return this.pk;
	}

	public void setPk(PurchaseOrderLinePK pk) {
		this.pk = pk;
	}
	
	public BigDecimal getOutstandingBalance() {
		return this.outstandingBalance;
	}

	public void setOutstandingBalance(BigDecimal outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	public Date getRequestedDeliveryDate() {
		return this.requestedDeliveryDate;
	}

	public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
		this.requestedDeliveryDate = requestedDeliveryDate;
	}

	public int getLeadtime() {
		return this.leadtime;
	}

	public void setLeadtime(int leadtime) {
		this.leadtime = leadtime;
	}

	public int getDeliveryLocation() {
		return this.deliveryLocation;
	}

	public void setDeliveryLocation(int deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}

	public String getOptionalComment() {
		return this.optionalComment;
	}

	public void setOptionalComment(String optionalComment) {
		this.optionalComment = optionalComment;
	}

	public String getPartNumber() {
		return this.partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public int getOrderedQuantity() {
		return this.orderedQuantity;
	}

	public void setOrderesQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Override
	public String toString() {
		return "PurchaseOrderLine [pk=" + pk + ", outstandingBalance=" + outstandingBalance + ", requestedDeliveryDate="
				+ requestedDeliveryDate + ", leadtime=" + leadtime + ", deliveryLocation=" + deliveryLocation
				+ ", optionalComment=" + optionalComment + ", partNumber=" + partNumber + ", orderedQuantity="
				+ orderedQuantity + ", version=" + version + ", purchaseOrder=" + purchaseOrder + "]";
	}
	
}