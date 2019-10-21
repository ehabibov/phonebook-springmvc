package com.ehabibov.springmvc.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object resourceId) {
        super(resourceId != null ? resourceId.toString() : null);
    }
}
