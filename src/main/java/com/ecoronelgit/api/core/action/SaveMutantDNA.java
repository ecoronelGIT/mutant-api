package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.domain.MutantDNARepository;

public class SaveMutantDNA {

    private final MutantDNARepository mutantDNARepository;

    public SaveMutantDNA(MutantDNARepository mutantDNARepository) {
        this.mutantDNARepository = mutantDNARepository;
    }

    public void execute(String[] dnaSequence) {
        this.mutantDNARepository.save(dnaSequence);
    }
}
