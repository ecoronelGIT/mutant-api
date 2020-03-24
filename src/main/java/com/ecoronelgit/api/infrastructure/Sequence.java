package com.ecoronelgit.api.infrastructure;

public class Sequence {
    private String[] dna;
    private boolean isMutant;

    public Sequence(String[] dna, boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
