package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.domain.exception.DNASequenceExistException;
import com.ecoronelgit.api.infrastructure.MemoryMutantDNARepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class SaveMutantDNATest {
    private static final String DNA_SEQUENCE_EXIST_ERROR_MESSAGE = "Given DNA Sequence already exist";
    private SaveMutantDNA saveMutantDNA;
    private MemoryMutantDNARepository mutantDNARepository;
    private String[] dnaSequence;
    private Throwable thrownException;
    private String[] secondDNASequence;

    @Test
    public void shouldSaveDNASequenceWhenActionIsCall() {
        givenMutantDNARepository();
        givenSaveMutantDNA();
        givenDNASequence();

        whenSaveMutantDNA();

        thenMutantDNAShouldExist(dnaSequence);
    }

    @Test
    public void shouldNotSaveSameDNASequenceWhenActionIsCall() {
        givenMutantDNARepository();
        givenSaveMutantDNA();
        givenDNASequence();
        givenDNASequenceIsSaved(dnaSequence);

        whenSaveMutantDNA();

        thenShouldGiveDNASequenceExitException();
    }

    private void thenShouldGiveDNASequenceExitException() {
        assertThat(thrownException).isExactlyInstanceOf(DNASequenceExistException.class)
                .hasMessage(DNA_SEQUENCE_EXIST_ERROR_MESSAGE);
    }

    @Test
    public void shouldSaveTwoDifferentDNASequenceWhenActionIsCall() {
        givenMutantDNARepository();
        givenSaveMutantDNA();
        givenDNASequence();
        givenSecondDNASequence();
        givenDNASequenceIsSaved(secondDNASequence);

        whenSaveMutantDNA();

        thenMutantDNAShouldExist(secondDNASequence);
    }

    private void givenSecondDNASequence() {
        secondDNASequence = new String[]{"CCCCTA", "CAGTGC", "TTATGT", "AGAAGG", "ATGCAG", "TTTTTG"};
    }

    private void givenDNASequenceIsSaved(String[] dnaSequence) {
        saveMutantDNA.execute(dnaSequence, true);
    }

    private void thenMutantDNAShouldExist(String[] dnaSequence) {
        assertThat(mutantDNARepository.exist(dnaSequence)).isEqualTo(true);
    }

    private void whenSaveMutantDNA() {
        thrownException = catchThrowable(() -> saveMutantDNA.execute(dnaSequence, true));
    }

    private void givenDNASequence() {
        this.dnaSequence = new String[]{"CCCCTA", "CAGTGC", "TTATGT", "AGAAGG", "ATGCGA", "TTTTTG"};
    }

    private void givenSaveMutantDNA() {
        saveMutantDNA = new SaveMutantDNA(mutantDNARepository);
    }

    private void givenMutantDNARepository() {
        mutantDNARepository = new MemoryMutantDNARepository();
    }

}
