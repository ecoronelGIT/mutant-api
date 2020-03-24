package com.ecoronelgit.api.core.action.validation;

import com.ecoronelgit.api.core.domain.exception.SquareMatrixDNASequenceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class SquareMatrixDNAValidatorTest {
    private static final String DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE = "DNA Sequence is not a square matrix, should have same columns and rows";
    public static final String[] NOT_SQUARE_MATRIX_DNA_SEQUENCE = {"ATGCGA", "CAGTGC", "CAGTC", "AGAAGG", "CCCCTA", "TCACTG"};
    private static final String[] CORRECT_DNA_SEQUENCE = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    private Throwable thrownException;
    private SquareMatrixDNAValidator squareMatrixDNAValidator;

    @Test
    public void shouldGiveSquareMatrixExceptionWithDNASequence() {
        givenSquareMatrixDNAValidator();

        whenSquareMatrixDNAValidatorIsCall(NOT_SQUARE_MATRIX_DNA_SEQUENCE);

        thenShouldGiveSquareMatrixSequenceException();
    }

    @Test
    public void shouldNotGiveSquareMatrixExceptionWithDNASequence() {
        givenSquareMatrixDNAValidator();

        whenSquareMatrixDNAValidatorIsCall(CORRECT_DNA_SEQUENCE);

        thenShouldNotGiveSquareMatrixSequenceException();
    }

    private void thenShouldNotGiveSquareMatrixSequenceException() {
        assertThat(thrownException).isNull();
    }

    private void givenSquareMatrixDNAValidator() {
        squareMatrixDNAValidator = new SquareMatrixDNAValidator();
    }

    private void whenSquareMatrixDNAValidatorIsCall(String[] dnaSequence) {
        thrownException = catchThrowable(() -> squareMatrixDNAValidator.apply(dnaSequence));
    }

    private void thenShouldGiveSquareMatrixSequenceException() {
        assertThat(thrownException).isExactlyInstanceOf(SquareMatrixDNASequenceException.class)
                .hasMessage(DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE);
    }
}
