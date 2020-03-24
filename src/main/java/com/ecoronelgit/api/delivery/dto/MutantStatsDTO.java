package com.ecoronelgit.api.delivery.dto;

import com.ecoronelgit.api.core.domain.MutantStats;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MutantStatsDTO {
    @JsonProperty("count_mutant_dna")
    private int countMutantDNA;
    @JsonProperty("count_human_dna")
    private int countHumanDNA;
    private double ratio;

    public MutantStatsDTO(MutantStats mutantStats) {
        this.countHumanDNA = mutantStats.getCountHumanDNA();
        this.countMutantDNA = mutantStats.getCountMutantDNA();
        this.ratio = mutantStats.getRatio();
    }

    public int getCountMutantDNA() {
        return countMutantDNA;
    }

    public int getCountHumanDNA() {
        return countHumanDNA;
    }

    public double getRatio() {
        return ratio;
    }

    public void setCountMutantDNA(int countMutantDNA) {
        this.countMutantDNA = countMutantDNA;
    }

    public void setCountHumanDNA(int countHumanDNA) {
        this.countHumanDNA = countHumanDNA;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
