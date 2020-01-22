package de.novatec.showcase.supplier.ejb.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "S_PURCHASE_ORDERLINE")
@NamedQueries(value = {
		@NamedQuery(name = PurchaseOrderLine.COUNT_PURCHASEORDERLINE, query = PurchaseOrderLine.COUNT_PURCHASEORDERLINE_QUERY),
		@NamedQuery(name = PurchaseOrderLine.FIND_PURCHASEORDERLINE_BY_COMPONENT_ID, query = PurchaseOrderLine.FIND_PURCHASEORDERLINE_BY_COMPONENT_ID_QUERY) })
@IdClass(PurchaseOrderLinePK.class)
public class PurchaseOrderLine {

	public static final String COUNT_PURCHASEORDERLINE = "COUNT_PURCHASEORDERLINE";

	public static final String FIND_PURCHASEORDERLINE_BY_COMPONENT_ID = "FIND_PURCHASEORDERLINE_BY_COMPONENT_ID";

	public static final String COUNT_PURCHASEORDERLINE_QUERY = "SELECT COUNT(po) FROM PurchaseOrderLine po";

	public static final String FIND_PURCHASEORDERLINE_BY_COMPONENT_ID_QUERY = "SELECT pol FROM PurchaseOrderLine pol WHERE pol.partNumber = :id";

	@Id
	@Column(name = "POL_PO_ID", nullable = false)
	private Integer poNumber;

	@Id
	@Column(name = "POL_NUMBER", nullable = false)
	private Integer polNumber;

	@Column(name = "POL_BALANCE", precision = 12, scale = 2)
	private BigDecimal outstandingBalance;

	@Temporal(TemporalType.DATE)
	@Column(name = "POL_DELDATE")
	private Date requestedDeliveryDate;

	@Column(name = "POL_LEADTIME")
	private int leadtime;

	@Column(name = "POL_LOCATION", nullable = false)
	private int deliveryLocation;

	@Column(name = "POL_MESSAGE",length = 100)
	private String optionalComment;

	@Column(name = "POL_P_ID", length =20)
	private String partNumber;

	@Column(name = "POL_QTY")
	private int orderedQuantity;

	@Version
	@Column(name = "POL_VERSION")
	private Integer version;

	@ManyToOne
	@JoinColumn(name = "POL_PO_ID", insertable = false, updatable = false)
	private PurchaseOrder purchaseOrder;

	public PurchaseOrderLine() {
		super();
	}

	public PurchaseOrderLine(Integer polNumber, Integer poNumber, int deliveryLocation, String partNumber,
			int orderedQuantity, BigDecimal outstandingBalance, Date requestedDeliveryDate, String optionalComment,
			int leadtime, PurchaseOrder purchaseOrder) {
		super();
		this.polNumber = polNumber;
		this.poNumber = poNumber;
		this.outstandingBalance = outstandingBalance;
		this.requestedDeliveryDate = requestedDeliveryDate;
		this.leadtime = leadtime;
		this.deliveryLocation = deliveryLocation;
		this.optionalComment = optionalComment;
		this.partNumber = partNumber;
		this.orderedQuantity = orderedQuantity;
		this.purchaseOrder = purchaseOrder;
	}

	public Integer getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(Integer poNumber) {
		this.poNumber = poNumber;
	}

	public Integer getPolNumber() {
		return polNumber;
	}

	public void setPolNumber(Integer polNumber) {
		this.polNumber = polNumber;
	}

	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
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

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deliveryLocation, leadtime, optionalComment, orderedQuantity, outstandingBalance,
				partNumber, poNumber, polNumber, requestedDeliveryDate, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PurchaseOrderLine)) {
			return false;
		}
		PurchaseOrderLine other = (PurchaseOrderLine) obj;
		return deliveryLocation == other.deliveryLocation && leadtime == other.leadtime
				&& Objects.equals(optionalComment, other.optionalComment) && orderedQuantity == other.orderedQuantity
				&& Objects.equals(outstandingBalance, other.outstandingBalance)
				&& Objects.equals(partNumber, other.partNumber) && Objects.equals(poNumber, other.poNumber)
				&& Objects.equals(polNumber, other.polNumber) 
				&& Objects.equals(requestedDeliveryDate, other.requestedDeliveryDate)
				&& Objects.equals(version, other.version);
	}

	@Override
	public String toString() {
		return "PurchaseOrderLine [poNumber=" + poNumber + ", polNumber=" + polNumber + ", outstandingBalance="
				+ outstandingBalance + ", requestedDeliveryDate=" + requestedDeliveryDate + ", leadtime=" + leadtime
				+ ", deliveryLocation=" + deliveryLocation + ", optionalComment=" + optionalComment + ", partNumber="
				+ partNumber + ", orderedQuantity=" + orderedQuantity + ", version=" + version + ", purchaseOrder="
				+ purchaseOrder + "]";
	}

}