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
    public static final String[] NOT_SQUARE_MATRIX_DNA_SEQUENCE = {"ATGCGA", "CAGTGC", "", "AGAAGG", "CCCCTA", "TCACTG"};
    public static final String[] INCORRECT_CHARACTERS_DNA_SEQUENCE = {"ATGEGA", "CAGTGC", "CAGTGC", "AGAAGG", "CCHCTA", "TCACTG"};
    public static final String[] SHORT_DNA_SEQUENCE = {"ATG", "CAG", "CAG"};
    private IsMutant isMutant;
    private Throwable thrownException;
    private boolean isMutantResult;
    private String[] dnaSequence;

    @Test
    public void shouldGiveAnErrorWhenEmptyDNASequence() {
        givenIsMutantAction();
        givenDNASequence(EMPTY_DNA_SEQUENCE);

        whenExecuteIsMutantAction();

        thenShouldGiveAnEmptySequenceException();
    }

    @Test
    public void shouldGiveAnErrorWhenHasNullDNASequence() {
        givenIsMutantAction();
        givenDNASequence(NULL_DNA_SEQUENCE);

        whenExecuteIsMutantAction();

        thenShouldGiveAnNullDNASequenceException();
    }

    @Test
    public void shouldGiveAnErrorWhenDNASequenceIsNotASquareMatrix() {
        givenIsMutantAction();
        givenDNASequence(NOT_SQUARE_MATRIX_DNA_SEQUENCE);

        whenExecuteIsMutantAction();

        thenShouldGiveAnNotSquareMatrixException();

    }

    @Test
    public void shouldGiveAnErrorWhenDNASequenceHaveIncorrectCharacters() {
        givenIsMutantAction();
        givenDNASequence(INCORRECT_CHARACTERS_DNA_SEQUENCE);

        whenExecuteIsMutantAction();

        thenShouldGiveAnIncorrectCharacterException();
    }

    @Test
    public void shouldGiveAnErrorWhenDNASequenceIsTooShort() {
        givenIsMutantAction();
        givenDNASequence(SHORT_DNA_SEQUENCE);

        whenExecuteIsMutantAction();

        thenShouldGiveAnShortDNAException();
    }

    @Test
    public void shouldGiveMutantWhenHaveTwoOrMoreHorizontalEqualSequence() {
        givenIsMutantAction();
        givenDNASequence(new String[]{"CCCCTA","CAGTGC","TTATGT","AGAAGG","ATGCGA","TTTTTG"});

        whenExecuteIsMutantAction();

        thenShouldBeAMutant();
    }

    private void givenDNASequence(String[] dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    private void thenShouldBeAMutant() {
        assertThat(isMutantResult).isEqualTo(true);
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

    private void whenExecuteIsMutantAction() {
        thrownException = catchThrowable(() -> isMutantResult = isMutant.execute(dnaSequence));
    }

    private void givenIsMutantAction() {
        isMutant = new IsMutant();
    }
}
