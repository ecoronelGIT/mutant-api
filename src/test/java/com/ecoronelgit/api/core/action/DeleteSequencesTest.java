package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.infrastructure.MemoryMutantDNARepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteSequencesTest {

    private MemoryMutantDNARepository memoryMutantDNARepository;
    private DeleteSequences deleteSequences;
    private String[] dnaSequence;

    @Test
    public void shouldGiveEmptySequencesWhenIsReset() {
        givenMemoryMutanDNARepository();
        givenDeleteSequences();
        givenDNASequence();
        givenSaveDNASequenceInRepository();

        whenDeleteSequence();

        thenSequenceShouldNotExist();
    }

    private void thenSequenceShouldNotExist() {
        assertThat(memoryMutantDNARepository.exist(dnaSequence)).isEqualTo(false);
    }

    private void whenDeleteSequence() {
        deleteSequences.execute();
    }

    private void givenSaveDNASequenceInRepository() {
        memoryMutantDNARepository.save(dnaSequence, true);
    }

    private void givenDNASequence() {
        dnaSequence = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    }

    private void givenDeleteSequences() {
        deleteSequences = new DeleteSequences(memoryMutantDNARepository);
    }

    private void givenMemoryMutanDNARepository() {
        memoryMutantDNARepository = new MemoryMutantDNARepository();
    }
}
