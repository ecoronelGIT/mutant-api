package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.exception.EmptyDNASequenceException;
import com.ecoronelgit.api.core.exception.MatrixDNASequenceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class IsMutantTest {

    private static final String[] EMPTY_DNA_SEQUENCE = new String[0];
    private static final String[] NULL_DNA_SEQUENCE = new String[4];
    private static final String DNA_SEQUENCE_IS_EMPTY_MESSAGE = "DNA Sequence is empty";
    private static final String DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE = "DNA sequence is not a square matrix, should have same columns and rows";
    private IsMutant isMutant;
    private Throwable thrownException;
    private boolean isMutantResult;

    @Test
    public void shouldGiveAnErrorWhenEmptyDNASequence() {
        givenIsMutantAction();

        whenExecuteIsMutantAction(EMPTY_DNA_SEQUENCE);

        thenShouldGiveAnEmptySequenceException();
    }

    @Test
    public void shouldGiveAnErrorWhenHasNullDNASequence() {
        givenIsMutantAction();

        whenExecuteIsMutantAction(NULL_DNA_SEQUENCE);

        thenShouldGiveAnNotSquareMatrixException();
    }

    private void thenShouldGiveAnNotSquareMatrixException() {
        assertThat(thrownException).isExactlyInstanceOf(MatrixDNASequenceException.class)
                .hasMessage(DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE);
    }

    private void thenShouldGiveAnEmptySequenceException() {
        assertThat(thrownException).isExactlyInstanceOf(EmptyDNASequenceException.class)
                .hasMessage(DNA_SEQUENCE_IS_EMPTY_MESSAGE);
    }

    private void whenExecuteIsMutantAction(String[] dnaSequence) {
        thrownException = catchThrowable(() -> isMutantResult = isMutant.execute(dnaSequence));
    }

    private void givenIsMutantAction() {
        isMutant = new IsMutant();
    }
}