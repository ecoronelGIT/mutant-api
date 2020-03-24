package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.domain.MutantStats;
import com.ecoronelgit.api.infrastructure.MemoryMutantDNARepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetStatsTest {

    public static final boolean IS_MUTANT = true;
    public static final boolean IS_HUMAN = false;
    public static final int TEN_AMOUNT_REPETITION = 10;
    public static final int FOUR_AMOUNT_REPETITION = 4;
    private MemoryMutantDNARepository memoryMutantDNARepository;
    private GetStats getStats;
    private String[] dnaSequence;
    private MutantStats mutantStats;

    @Test
    public void shouldHaveStats() {
        givenMemoryMutantDNARepository();
        givenGetStats();
        givenDNASequence();
        givenSaveAmountOfDNASequences(FOUR_AMOUNT_REPETITION, IS_MUTANT);
        givenSaveAmountOfDNASequences(TEN_AMOUNT_REPETITION, IS_HUMAN);

        whenGetStats();

        thenShouldHaveTheSameData();
    }

    private void thenShouldHaveTheSameData() {
        assertThat(mutantStats.getCountMutantDNA()).isEqualTo(FOUR_AMOUNT_REPETITION);
        assertThat(mutantStats.getCountHumanDNA()).isEqualTo(TEN_AMOUNT_REPETITION);
        assertThat(mutantStats.getRatio()).isEqualTo(Double.valueOf(FOUR_AMOUNT_REPETITION)/Double.valueOf(TEN_AMOUNT_REPETITION));
    }

    private void whenGetStats() {
        mutantStats = getStats.execute();
    }

    private void givenSaveAmountOfDNASequences(int amountRepetition, boolean isMutant) {
        for (int i = 0; i < amountRepetition; i++) {
            memoryMutantDNARepository.save(dnaSequence, isMutant);
        }
    }

    private void givenDNASequence() {
        dnaSequence = new String[]{"ATGCGA","TAGGCA","GAAGCC","TAAAGC","AGTAAG","CCACTG"};
    }

    private void givenGetStats() {
        getStats = new GetStats(memoryMutantDNARepository);
    }

    private void givenMemoryMutantDNARepository() {
        memoryMutantDNARepository = new MemoryMutantDNARepository();
    }
}
