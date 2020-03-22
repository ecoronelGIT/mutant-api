package com.ecoronelgit.api.core.exception;

public class MatrixDNASequenceException extends RuntimeException{
    private static final String DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE = "DNA sequence is not a square matrix, should have same columns and rows";

    public MatrixDNASequenceException() {
        super(DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE);
    }
}