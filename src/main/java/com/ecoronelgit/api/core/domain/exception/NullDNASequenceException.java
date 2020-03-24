package com.ecoronelgit.api.core.domain.exception;

public class NullDNASequenceException extends RuntimeException {
    private static final String NULL_DNA_SEQUENCE_ERROR_MESSAGE = "DNA Sequence has a null sequence";

    public NullDNASequenceException() {
        super(NULL_DNA_SEQUENCE_ERROR_MESSAGE);
    }
}
