package com.ecoronelgit.api.infrastructure;

import com.ecoronelgit.api.core.domain.MutantDNARepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MongoDBMutantDNARepository implements MutantDNARepository {

    private final SequenceRepository sequenceRepository;

    public MongoDBMutantDNARepository(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public boolean exist(String[] dnaSequence) {
        return sequenceRepository.findAll().stream().filter(sequence -> Arrays.equals(sequence.getDna(), dnaSequence)).count() > 0;
    }

    @Override
    public void save(String[] dnaSequence, boolean isMutant) {
        sequenceRepository.save(new Sequence(dnaSequence, isMutant));
    }

    @Override
    public List<String[]> getDNASequences(boolean isMutant) {
        return sequenceRepository.findAll().stream().filter(sequence -> sequence.isMutant() == isMutant)
                .map(sequence -> sequence.getDna()).collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        sequenceRepository.deleteAll();
    }
}
