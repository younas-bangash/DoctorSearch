package com.vivy.docsearch.api;


/**
 * Keep all the service related constants here.
 */
public class ApiConstants {
    public static final String AUTH_URL = "https://auth.staging.vivy.com/";
    public static final String SERVICES_BASE_URL = "https://api.staging.vivy.com/";
    public static final long CONNECT_TIMEOUT = 30000;
    public static final long READ_TIMEOUT = 30000;
    public static final long WRITE_TIMEOUT = 30000;

    private ApiConstants(){
        // Private constructor to hide the implicit one
    }
}
