package br.com.jandernery.precojusto.infra.exceptions;

import lombok.*;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String originatingClass;
    private String path;

    public ErrorResponse(int statusCode, String message, String originatingClass, String path) {
        this.statusCode = statusCode;
        this.message = message;
        this.originatingClass = originatingClass;
        this.path = path;
    }
}
