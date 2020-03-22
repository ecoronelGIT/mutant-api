package com.ecoronelgit.api.core.exception;

public class EmptyDNASequenceException extends RuntimeException {
    private static final String DNA_SEQUENCE_IS_EMPTY_MESSAGE = "DNA Sequence is empty";

    public EmptyDNASequenceException() {
        super(DNA_SEQUENCE_IS_EMPTY_MESSAGE);
    }

}
