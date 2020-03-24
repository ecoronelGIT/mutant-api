package com.ecoronelgit.api.core.action.validation;

import com.ecoronelgit.api.core.domain.exception.EmptyDNASequenceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class EmptyDNAValidatorTest {
    private static final String DNA_SEQUENCE_IS_EMPTY_MESSAGE = "DNA Sequence is empty";
    private static final String[] EMPTY_DNA_SEQUENCE = new String[0];
    private static final String[] NOT_EMPTY_DNA_SEQUENCE = {"AGTA"};
    private Throwable thrownException;
    private EmptyDNAValidator emptyDNAValidator;

    @Test
    public void shouldGiveAnEmptyExceptionWhenDNASequenceIsEmpty() {
        givenEmptyDNAValidator();

        whenEmptyDNAValidatorIsCall(EMPTY_DNA_SEQUENCE);

        thenShouldGiveAnEmptySequenceException();
    }

    @Test
    public void shouldNotGiveAnEmptyExceptionWhenDNASequenceIsEmpty() {
        givenEmptyDNAValidator();

        whenEmptyDNAValidatorIsCall(NOT_EMPTY_DNA_SEQUENCE);

        thenShouldNotGiveAnEmptySequenceException();
    }

    private void thenShouldNotGiveAnEmptySequenceException() {
        assertThat(thrownException).isNull();
    }

    private void givenEmptyDNAValidator() {
        emptyDNAValidator = new EmptyDNAValidator();
    }

    private void whenEmptyDNAValidatorIsCall(String[] dnaSequence) {
        thrownException = catchThrowable(() -> emptyDNAValidator.apply(dnaSequence));
    }

    private void thenShouldGiveAnEmptySequenceException() {
        assertThat(thrownException).isExactlyInstanceOf(EmptyDNASequenceException.class)
                .hasMessage(DNA_SEQUENCE_IS_EMPTY_MESSAGE);
    }
}
