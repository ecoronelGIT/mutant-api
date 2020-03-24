package com.ecoronelgit.api.core.domain.exception;

public class ShortDNASequenceException extends RuntimeException {
    private static final String SHORT_DNA_SEQUENCE_ERROR_MESSAGE = "DNA Sequence is too short";

    public ShortDNASequenceException() {
        super(SHORT_DNA_SEQUENCE_ERROR_MESSAGE);
    }
}
