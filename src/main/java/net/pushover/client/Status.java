package net.pushover.client;

import java.util.List;

/**
 * API response object
 */
public class Status {

    private Integer status;

    private String request;

    private String user;

    private List<String> errors;

    private String requestParameters;

    private String rawJsonResponse;

    public List<String> getErrors() {
        return errors;
    }

    public String getRawJsonResponse() {
        return rawJsonResponse;
    }

    public String getRequest() {
        return request;
    }

    public String getRequestParameters() {
        return requestParameters;
    }

    public int getStatus() {
        return status;
    }

    public String getUser() {
        return user;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setRawJsonResponse(String rawJsonResponse) {
        this.rawJsonResponse = rawJsonResponse;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setRequestParameters(String requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("status: %s, requestId: %s", status != null ? String.valueOf(status) : "??", request);
    }
}
