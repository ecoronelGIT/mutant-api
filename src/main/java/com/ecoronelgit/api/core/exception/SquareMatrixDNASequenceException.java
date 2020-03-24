package com.ecoronelgit.api.core.exception;

public class SquareMatrixDNASequenceException extends RuntimeException{
    private static final String DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE = "DNA Sequence is not a square matrix, should have same columns and rows";

    public SquareMatrixDNASequenceException() {
        super(DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE);
    }
}
