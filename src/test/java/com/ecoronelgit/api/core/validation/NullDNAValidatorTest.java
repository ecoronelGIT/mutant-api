package com.ecoronelgit.api.core.validation;

import com.ecoronelgit.api.core.exception.NullDNASequenceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class NullDNAValidatorTest {
    private static final String NULL_DNA_SEQUENCE_ERROR_MESSAGE = "DNA Sequence has a null sequence";
    private static final String[] NULL_DNA_SEQUENCE = new String[4];
    private static final String[] NOT_NULL_DNA_SEQUENCE = {"AGTA"};
    private Throwable thrownException;
    private NullDNAValidator nullDNAValidator;

    @Test
    public void shouldGiveNullExceptionWhenDNASequenceIsNull() {
        givenNullDNAValidator();

        whenNullDNAValidatorIsCall(NULL_DNA_SEQUENCE);

        thenShouldGiveNullSequenceException();
    }

    @Test
    public void shouldNotGiveAnNullExceptionWhenDNASequenceIsNull() {
        givenNullDNAValidator();

        whenNullDNAValidatorIsCall(NOT_NULL_DNA_SEQUENCE);

        thenShouldNotGiveNullSequenceException();
    }

    private void thenShouldNotGiveNullSequenceException() {
        assertThat(thrownException).isNull();
    }

    private void givenNullDNAValidator() {
        nullDNAValidator = new NullDNAValidator();
    }

    private void whenNullDNAValidatorIsCall(String[] dnaSequence) {
        thrownException = catchThrowable(() -> nullDNAValidator.apply(dnaSequence));
    }
    
    private void thenShouldGiveNullSequenceException() {
        assertThat(thrownException).isExactlyInstanceOf(NullDNASequenceException.class)
                .hasMessage(NULL_DNA_SEQUENCE_ERROR_MESSAGE);
    }
}
