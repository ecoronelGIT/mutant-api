package com.ecoronelgit.api.infrastructure;

import org.springframework.data.annotation.Id;

public class Sequence {
    @Id
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
