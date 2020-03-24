package com.ecoronelgit.api.core.action.validation;

import com.ecoronelgit.api.core.domain.exception.IncorrectDNASequenceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class IncorrectCharacterDNAValidatorTest {
    private static final String DNA_SEQUENCE_INCORRECT_CHARACTER_ERROR_MESSAGE = "DNA Sequence has an incorrect character";
    private static final String[] INCORRECT_CHARACTER_DNA_SEQUENCE = {"ATGEGA", "CAGTGC", "CAGTGC", "AGAAGG", "CCHCTA", "TCACTG"};
    private static final String[] CORRECT_DNA_SEQUENCE = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    private Throwable thrownException;
    private IncorrectCharacterDNAValidator incorrectCharacterDNAValidator;

    @Test
    public void shouldGiveIncorrectCharacterExceptionWithDNASequence() {
        givenIncorrectCharacterDNAValidator();

        whenIncorrectCharacterDNAValidatorIsCall(INCORRECT_CHARACTER_DNA_SEQUENCE);

        thenShouldGiveIncorrectCharacterSequenceException();
    }

    @Test
    public void shouldNotGiveIncorrectCharacterExceptionWithDNASequence() {
        givenIncorrectCharacterDNAValidator();

        whenIncorrectCharacterDNAValidatorIsCall(CORRECT_DNA_SEQUENCE);

        thenShouldNotGiveIncorrectCharacterSequenceException();
    }

    private void thenShouldNotGiveIncorrectCharacterSequenceException() {
        assertThat(thrownException).isNull();
    }

    private void givenIncorrectCharacterDNAValidator() {
        incorrectCharacterDNAValidator = new IncorrectCharacterDNAValidator();
    }

    private void whenIncorrectCharacterDNAValidatorIsCall(String[] dnaSequence) {
        thrownException = catchThrowable(() -> incorrectCharacterDNAValidator.apply(dnaSequence));
    }
    
    private void thenShouldGiveIncorrectCharacterSequenceException() {
        assertThat(thrownException).isExactlyInstanceOf(IncorrectDNASequenceException.class)
                .hasMessage(DNA_SEQUENCE_INCORRECT_CHARACTER_ERROR_MESSAGE);
    }
}
