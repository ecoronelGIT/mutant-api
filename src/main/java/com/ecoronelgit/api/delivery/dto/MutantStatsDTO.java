package com.ecoronelgit.api.delivery.dto;

import com.ecoronelgit.api.core.domain.MutantStats;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Mutant Stats")
public class MutantStatsDTO {
    @ApiModelProperty(notes = "Count of mutant verifications")
    @JsonProperty("count_mutant_dna")
    private int countMutantDNA;
    @ApiModelProperty(notes = "Count of human verifications")
    @JsonProperty("count_human_dna")
    private int countHumanDNA;
    @ApiModelProperty(notes = "Average between mutants over humans (mutants/humans)")
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
