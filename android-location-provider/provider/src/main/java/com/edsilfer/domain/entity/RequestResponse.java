package com.edsilfer.domain.entity;

/**
 * Created by ferna on 6/4/2017.
 */
public class RequestResponse {

    private String consoleOutput;
    private Object payload;

    public RequestResponse(String consoleOutput, Object payload) {
        this.consoleOutput = consoleOutput;
        this.payload = payload;
    }

    public String getConsoleOutput() {
        return consoleOutput;
    }

    public void setConsoleOutput(String consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

}
