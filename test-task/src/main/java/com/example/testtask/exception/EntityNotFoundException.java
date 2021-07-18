package com.example.testtask.exception;

public class EntityNotFoundException extends RuntimeException {

    private EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException getInstanceByEntityNameAndId(String entityName, Long id) {
        String message = String.format("Entity %s with id=%d not found", entityName, id);
        return new EntityNotFoundException(message);
    }
}
