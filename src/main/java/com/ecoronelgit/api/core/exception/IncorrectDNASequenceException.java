package com.ecoronelgit.api.core.exception;

public class IncorrectDNASequenceException extends RuntimeException {
    private static final String DNA_SEQUENCE_INCORRECT_CHARACTER_ERROR_MESSAGE = "DNA Sequence has an incorrect character";

    public IncorrectDNASequenceException() {
        super(DNA_SEQUENCE_INCORRECT_CHARACTER_ERROR_MESSAGE);
    }
}
