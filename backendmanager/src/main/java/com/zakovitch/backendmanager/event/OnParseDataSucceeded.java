package com.zakovitch.backendmanager.event;

import com.zakovitch.backendmanager.model.Response;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class OnParseDataSucceeded {

    Response response;

    public OnParseDataSucceeded(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
