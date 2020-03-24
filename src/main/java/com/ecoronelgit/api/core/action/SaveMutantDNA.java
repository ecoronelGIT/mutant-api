package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.domain.MutantDNARepository;
import com.ecoronelgit.api.core.exception.DNASequenceExistException;

public class SaveMutantDNA {

    private final MutantDNARepository mutantDNARepository;

    public SaveMutantDNA(MutantDNARepository mutantDNARepository) {
        this.mutantDNARepository = mutantDNARepository;
    }

    public void execute(String[] dnaSequence) {
        if(mutantDNARepository.exist(dnaSequence))
            throw new DNASequenceExistException();
        this.mutantDNARepository.save(dnaSequence);
    }
}
