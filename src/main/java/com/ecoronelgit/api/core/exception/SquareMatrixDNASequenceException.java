package com.ecoronelgit.api.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SquareMatrixDNASequenceException extends RuntimeException{
    private static final String DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE = "DNA Sequence is not a square matrix, should have same columns and rows";

    public SquareMatrixDNASequenceException() {
        super(DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE);
    }
}
