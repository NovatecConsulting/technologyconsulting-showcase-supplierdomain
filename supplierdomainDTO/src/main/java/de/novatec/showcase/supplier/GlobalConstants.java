package de.novatec.showcase.supplier;

public interface GlobalConstants {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String ADMIN_ROLE_NAME = "SUPPLIERDOMAIN.ADMIN";
	public static final String SUPPLIER_READ_ROLE_NAME = "SUPPLIERDOMAIN.SUPPLIER";
	public static final String PURCHASE_ROLE_NAME = "SUPPLIERDOMAIN.PURCHASE";
	public static final String PROCESS_DELIVERY_ROLE_NAME = "SUPPLIERDOMAIN.PROCESS.DELIVERY";

	public static final Boolean IS_SINGLE_EAR_DEPLOYMENT = Boolean
			.valueOf(System.getProperty("isSingleEarDeployment", Boolean.FALSE.toString()));
}
