package com.ecoronelgit.api.core.exception;

public class DNASequenceExistException extends RuntimeException {
    private static final String DNA_SEQUENCE_EXIST_ERROR_MESSAGE = "Given DNA Sequence already exist";

    public DNASequenceExistException() {
        super(DNA_SEQUENCE_EXIST_ERROR_MESSAGE);
    }
}
