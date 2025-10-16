package ru.javawebinar.topjava.exception;

public class EntityNotFountException extends RuntimeException{
    public EntityNotFountException() {
        super("Entity not found");
    }
}
