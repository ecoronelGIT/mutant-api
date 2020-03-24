package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.domain.MutantDNARepository;
import com.ecoronelgit.api.core.domain.MutantStats;

public class GetStats {

    private final MutantDNARepository mutantDNARepository;

    public GetStats(MutantDNARepository mutantDNARepository) {
        this.mutantDNARepository = mutantDNARepository;
    }

    public MutantStats execute() {
        int mutants = mutantDNARepository.getDNASequences(true).size();
        int humans = mutantDNARepository.getDNASequences(false).size();
        return new MutantStats(mutants, humans, mutants/humans);
    }
}
