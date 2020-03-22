package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.exception.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class IsMutantTest {

    private static final String[] EMPTY_DNA_SEQUENCE = new String[0];
    private static final String[] NULL_DNA_SEQUENCE = new String[4];
    private static final String DNA_SEQUENCE_IS_EMPTY_MESSAGE = "DNA Sequence is empty";
    private static final String DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE = "DNA Sequence is not a square matrix, should have same columns and rows";
    private static final String NULL_DNA_SEQUENCE_ERROR_MESSAGE = "DNA Sequence has a null sequence";
    private static final String DNA_SEQUENCE_INCORRECT_CHARACTER_ERROR_MESSAGE = "DNA Sequence has an incorrect character";
    private static final String SHORT_DNA_SEQUENCE_ERROR_MESSAGE = "DNA Sequence is too short";
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

        thenShouldGiveAnNullDNASequenceException();
    }

    @Test
    public void shouldGiveAnErrorWhenDNASequenceIsNotASquareMatrix() {
        givenIsMutantAction();

        String[] dnaSequence = {"ATGCGA","CAGTGC","","AGAAGG","CCCCTA","TCACTG"};

        whenExecuteIsMutantAction(dnaSequence);

        thenShouldGiveAnNotSquareMatrixException();

    }

    @Test
    public void shouldGiveAnErrorWhenDNASequenceHaveIncorrectCharacters() {
        givenIsMutantAction();

        String[] dnaSequence = {"ATGEGA","CAGTGC","CAGTGC","AGAAGG","CCHCTA","TCACTG"};

        whenExecuteIsMutantAction(dnaSequence);

        thenShouldGiveAnIncorrectCharacterException();
    }

    @Test
    public void shouldGiveAnErrorWhenDNASequenceIsTooShort() {
        givenIsMutantAction();

        String[] dnaSequence = {"ATG","CAG","CAG"};

        whenExecuteIsMutantAction(dnaSequence);

        thenShouldGiveAnShortDNAException();
    }

    private void thenShouldGiveAnShortDNAException() {
        assertThat(thrownException).isExactlyInstanceOf(ShortDNASequenceException.class)
                .hasMessage(SHORT_DNA_SEQUENCE_ERROR_MESSAGE);
    }

    private void thenShouldGiveAnIncorrectCharacterException() {
        assertThat(thrownException).isExactlyInstanceOf(IncorrectDNASequenceException.class)
                .hasMessage(DNA_SEQUENCE_INCORRECT_CHARACTER_ERROR_MESSAGE);
    }


    private void thenShouldGiveAnNotSquareMatrixException() {
        assertThat(thrownException).isExactlyInstanceOf(SquareMatrixDNASequenceException.class)
                .hasMessage(DNA_SEQUENCE_SQUARE_MATRIX_ERROR_MESSAGE);
    }

    private void thenShouldGiveAnNullDNASequenceException() {
        assertThat(thrownException).isExactlyInstanceOf(NullDNASequenceException.class)
                .hasMessage(NULL_DNA_SEQUENCE_ERROR_MESSAGE);
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
