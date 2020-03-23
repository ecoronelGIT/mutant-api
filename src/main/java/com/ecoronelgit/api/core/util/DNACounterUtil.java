package com.ecoronelgit.api.core.util;

public class DNACounterUtil {
    public static final int INITIAL_COUNTER = 1;
    private char dna;
    private int counter = INITIAL_COUNTER;

    public DNACounterUtil(char dna) {
        this.dna = dna;
    }

    public void setDna(char dna) {
        this.dna = dna;
        this.counter = INITIAL_COUNTER;
    }

    public void addOne() {
        this.counter++;
    }

    public int getCounter() {
        return counter;
    }

    public boolean isSameDNA(char dna) {
        return this.dna == dna;
    }
}
