package org.sbertest.exeptions;

public class InvalidFullNameException extends RuntimeException {

    public InvalidFullNameException(String massage) {
        super(massage);
    }
}
