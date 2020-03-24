package com.ecoronelgit.api.infrastructure;

import com.ecoronelgit.api.core.domain.MutantDNARepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryMutantDNARepository implements MutantDNARepository {

    private List<String[]> dnaSequences = new ArrayList<>();

    @Override
    public boolean exist(String[] dnaSequence) {
        return this.dnaSequences.stream().filter(sequence -> Arrays.equals(sequence, dnaSequence)).count() > 0;
    }

    @Override
    public void save(String[] dnaSequence) {
        this.dnaSequences.add(dnaSequence);
    }
}
