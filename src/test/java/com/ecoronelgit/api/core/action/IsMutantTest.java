package com.ecoronelgit.api.core.action;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class IsMutantTest {

    private IsMutant isMutant;
    private String[] dnaSequence;
    private boolean isMutantResult;

    @Test
    public void shouldGiveNotMutantWhenEmptyDNAArray() {
        givenIsMutantAction();
        givenDNASequence();

        whenExecuteIsMutantAction();

        thenShouldNotBeMutant();
    }

    private void whenExecuteIsMutantAction() {
        isMutantResult = isMutant.execute(dnaSequence);
    }

    private void thenShouldNotBeMutant() {
        assertThat(isMutantResult).isEqualTo(false);
    }

    private void givenDNASequence() {
        dnaSequence = new String[0];
    }

    private void givenIsMutantAction() {
        isMutant = new IsMutant();
    }
}
