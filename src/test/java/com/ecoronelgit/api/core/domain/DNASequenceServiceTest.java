package com.ecoronelgit.api.core.domain;

import com.ecoronelgit.api.core.domain.DNASequenceService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DNASequenceServiceTest {

    private DNASequenceService dnaSequenceService;
    private String[] dnaSequence;
    private boolean isMutant;

    @Test
    public void shouldGiveMutantWhenHaveTwoOrMoreHorizontalEqualSequence() {
        givenDNASequenceService();
        givenDNASequence(new String[]{"CCCCTA","CAGTGC","TTATGT","AGAAGG","ATGCGA","TTTTTG"});

        whenCallDNASequenceService();

        thenShouldBeAMutant();
    }

    @Test
    public void shouldGiveMutantWhenHaveTwoOrMoreVerticalEqualSequence() {
        givenDNASequenceService();

        givenDNASequence(new String[]{"ATGCGA","CAGTGC","CTATGT","CGAAGG","CCGCTA","CCACTG"});

        whenCallDNASequenceService();

        thenShouldBeAMutant();
    }

    @Test
    public void shouldGiveMutantWhenHaveTwoOrMoreObliqueEqualSequence() {
        givenDNASequenceService();

        givenDNASequence(new String[]{"ATGCGA","TAGGCA","GAAGCC","TAAAGC","AGTAAG","CCACTG"});

        whenCallDNASequenceService();

        thenShouldBeAMutant();
    }

    @Test
    public void shouldGiveMutantWhenHaveTwoOrMoreDifferentEqualSequence() {
        givenDNASequenceService();

        givenDNASequence(new String[]{  "ATGCGA", "GAGGCA", "GAATCC", "CAAAGC", "GGTAAG", "CCCCTG"});

        whenCallDNASequenceService();

        thenShouldBeAMutant();
    }

    @Test
    public void shouldNotGiveMutantWhenHaveTwoOrMoreDifferentEqualSequence() {
        givenDNASequenceService();

        givenDNASequence(new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"});

        whenCallDNASequenceService();

        thenShouldNotBeAMutant();
    }

    private void whenCallDNASequenceService() {
        isMutant = dnaSequenceService.dnaSequenceContainsMutant(dnaSequence);
    }

    private void givenDNASequence(String[] dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    private void givenDNASequenceService() {
        dnaSequenceService = new DNASequenceService();
    }

    private void thenShouldBeAMutant() {
        assertThat(isMutant).isEqualTo(true);
    }

    private void thenShouldNotBeAMutant() {
        assertThat(isMutant).isEqualTo(false);
    }
}
