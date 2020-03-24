package com.ecoronelgit.api.infrastructure;

import com.ecoronelgit.api.core.domain.MutantDNARepository;

import java.util.Arrays;

public class MemoryMutantDNARepository implements MutantDNARepository {

    private String[] dnaSequence;

    @Override
    public boolean exist(String[] dnaSequence) {
        return Arrays.equals(dnaSequence, this.dnaSequence);
    }

    @Override
    public void save(String[] dnaSequence) {
        this.dnaSequence = dnaSequence;
    }
}
