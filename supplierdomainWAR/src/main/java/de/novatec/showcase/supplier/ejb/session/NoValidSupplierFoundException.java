package de.novatec.showcase.supplier.ejb.session;

public class NoValidSupplierFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoValidSupplierFoundException() {
		super();
	}

	public NoValidSupplierFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoValidSupplierFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoValidSupplierFoundException(String message) {
		super(message);
	}

	public NoValidSupplierFoundException(Throwable cause) {
		super(cause);
	}

}
