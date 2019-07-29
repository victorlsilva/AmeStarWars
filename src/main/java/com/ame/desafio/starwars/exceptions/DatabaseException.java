package com.ame.desafio.starwars.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String s) {
        super(s);
    }

    public DatabaseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
