package com.ecoronelgit.api.infrastructure;

import com.ecoronelgit.api.core.domain.MutantDNARepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryMutantDNARepository implements MutantDNARepository {

    private List<Sequence> sequences = new ArrayList<>();

    @Override
    public boolean exist(String[] dnaSequence) {
        return this.sequences.stream().filter(sequence -> Arrays.equals(sequence.getDna(), dnaSequence)).count() > 0;
    }

    @Override
    public void save(String[] dnaSequence, boolean isMutant) {
        this.sequences.add(new Sequence(dnaSequence, isMutant));
    }

    @Override
    public List<String[]> getDNASequences(boolean isMutant) {
        return this.sequences.stream().filter(sequence -> sequence.isMutant() == isMutant)
                .map(sequence -> sequence.getDna()).collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        this.sequences = new ArrayList<>();
    }
}
