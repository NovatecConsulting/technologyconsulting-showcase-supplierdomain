package de.novatec.showcase.supplier.ejb.session.exception;

public class SupplierNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public SupplierNotFoundException() {
		super();
	}

	public SupplierNotFoundException(String message) {
		super(message);
	}

}
