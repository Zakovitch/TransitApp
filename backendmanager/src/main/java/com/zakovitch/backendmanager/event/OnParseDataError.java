package com.zakovitch.backendmanager.event;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class OnParseDataError {

    String errorMessage;

    public OnParseDataError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
