package com.ecoronelgit.api.core.domain;

public class MutantStats {
    private int countMutantDNA;
    private int countHumanDNA;
    private double ratio;

    public MutantStats(int mutants, int humans, int ratio) {
        this.countMutantDNA = mutants;
        this.countHumanDNA = humans;
        this.ratio = ratio;
    }

    public int getCountMutantDNA() {
        return countMutantDNA;
    }

    public void setCountMutantDNA(int countMutantDNA) {
        this.countMutantDNA = countMutantDNA;
    }

    public int getCountHumanDNA() {
        return countHumanDNA;
    }

    public void setCountHumanDNA(int countHumanDNA) {
        this.countHumanDNA = countHumanDNA;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
