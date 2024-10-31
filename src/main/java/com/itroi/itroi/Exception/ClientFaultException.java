package com.itroi.itroi.Exception;

import jakarta.xml.ws.WebFault;

@WebFault(name = "ClientFault", targetNamespace = "http://itroi.com/itroi/Exception")
public class ClientFaultException extends Exception {
    public ClientFaultException(String message) {
        super(message);
    }
}