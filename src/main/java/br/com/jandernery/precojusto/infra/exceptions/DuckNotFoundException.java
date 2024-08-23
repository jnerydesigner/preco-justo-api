package br.com.jandernery.precojusto.infra.exceptions;

import org.springframework.http.HttpStatus;

public class DuckNotFoundException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String originatingClass;

    public DuckNotFoundException(HttpStatus statusCode, String message, String originatingClass) {
        super(message);
        this.statusCode = statusCode;
        this.originatingClass = originatingClass;
    }

    public DuckNotFoundException(String originatingClass) {
        this(HttpStatus.NOT_FOUND, "Duck Not Found", originatingClass);
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getOriginatingClass() {
        return originatingClass;
    }
}