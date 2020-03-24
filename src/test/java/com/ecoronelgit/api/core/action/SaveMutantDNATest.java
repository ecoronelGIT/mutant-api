package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.infrastructure.MemoryMutantDNARepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SaveMutantDNATest {

    private SaveMutantDNA saveMutantDNA;
    private MemoryMutantDNARepository mutantDNARepository;
    private String[] dnaSequence;

    @Test
    public void shouldSaveDNAMutantWhenActionIsCall() {
        givenMutantDNARepository();
        givenSaveMutantDNA();
        givenDNASequence();

        whenSaveMutantDNA();

        thenMutantDNAShouldExist();
    }

    private void thenMutantDNAShouldExist() {
        assertThat(mutantDNARepository.exist(dnaSequence)).isEqualTo(true);
    }

    private void whenSaveMutantDNA() {
        saveMutantDNA.execute(dnaSequence);
    }

    private void givenDNASequence() {
        dnaSequence = new String[]{"CCCCTA", "CAGTGC", "TTATGT", "AGAAGG", "ATGCGA", "TTTTTG"};
    }

    private void givenSaveMutantDNA() {
        saveMutantDNA = new SaveMutantDNA(mutantDNARepository);
    }

    private void givenMutantDNARepository() {
        mutantDNARepository = new MemoryMutantDNARepository();
    }

}
