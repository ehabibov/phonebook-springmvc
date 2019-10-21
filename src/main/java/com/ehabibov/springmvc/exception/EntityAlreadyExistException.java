package com.ehabibov.springmvc.exception;

public class EntityAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistException(Object resourceId) {
        super(resourceId != null ? resourceId.toString() : null);
    }
}
