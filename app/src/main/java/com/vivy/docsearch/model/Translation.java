package com.vivy.docsearch.model;

/** Data model for Translation
 */
public class Translation {
    private String confidentialityKey;
    private String consentKey;
    private String requestSubmittedKey;

    public String getConfidentialityKey() {
        return confidentialityKey;
    }

    public void setConfidentialityKey(String confidentialityKey) {
        this.confidentialityKey = confidentialityKey;
    }

    public String getConsentKey() {
        return consentKey;
    }

    public void setConsentKey(String consentKey) {
        this.consentKey = consentKey;
    }

    public String getRequestSubmittedKey() {
        return requestSubmittedKey;
    }

    public void setRequestSubmittedKey(String requestSubmittedKey) {
        this.requestSubmittedKey = requestSubmittedKey;
    }
}
