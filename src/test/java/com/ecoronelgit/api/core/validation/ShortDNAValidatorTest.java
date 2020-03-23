package com.ecoronelgit.api.core.validation;

import com.ecoronelgit.api.core.exception.ShortDNASequenceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ShortDNAValidatorTest {
    private static final String SHORT_DNA_SEQUENCE_ERROR_MESSAGE = "DNA Sequence is too short";
    public static final String[] SHORT_DNA_SEQUENCE = {"ATG", "CAG", "CAG"};
    private static final String[] CORRECT_DNA_SEQUENCE = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    private Throwable thrownException;
    private ShortDNAValidator shortDNAValidator;

    @Test
    public void shouldGiveShortExceptionWithDNASequence() {
        givenShortDNAValidator();

        whenShortDNAValidatorIsCall(SHORT_DNA_SEQUENCE);

        thenShouldGiveShortSequenceException();
    }

    @Test
    public void shouldNotGiveShortExceptionWithDNASequence() {
        givenShortDNAValidator();

        whenShortDNAValidatorIsCall(CORRECT_DNA_SEQUENCE);

        thenShouldNotGiveShortSequenceException();
    }

    private void thenShouldNotGiveShortSequenceException() {
        assertThat(thrownException).isNull();
    }

    private void givenShortDNAValidator() {
        shortDNAValidator = new ShortDNAValidator();
    }

    private void whenShortDNAValidatorIsCall(String[] dnaSequence) {
        thrownException = catchThrowable(() -> shortDNAValidator.apply(dnaSequence));
    }

    private void thenShouldGiveShortSequenceException() {
        assertThat(thrownException).isExactlyInstanceOf(ShortDNASequenceException.class)
                .hasMessage(SHORT_DNA_SEQUENCE_ERROR_MESSAGE);
    }
}
