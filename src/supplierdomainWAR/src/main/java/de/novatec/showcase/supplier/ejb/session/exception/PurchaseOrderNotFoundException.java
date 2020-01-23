package de.novatec.showcase.supplier.ejb.session.exception;

public class PurchaseOrderNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public PurchaseOrderNotFoundException() {
		super();
	}

	public PurchaseOrderNotFoundException(String message) {
		super(message);
	}

}
