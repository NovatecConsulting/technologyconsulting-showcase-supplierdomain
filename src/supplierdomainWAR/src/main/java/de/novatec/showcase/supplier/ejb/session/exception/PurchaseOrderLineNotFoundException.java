package de.novatec.showcase.supplier.ejb.session.exception;

public class PurchaseOrderLineNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public PurchaseOrderLineNotFoundException() {
		super();
	}

	public PurchaseOrderLineNotFoundException(String message) {
		super(message);
	}

}
