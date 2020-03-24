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
        double ratio = humans > 0 ? Double.valueOf(mutants)/Double.valueOf(humans) : mutants;
        return new MutantStats(mutants, humans, ratio);
    }
}
