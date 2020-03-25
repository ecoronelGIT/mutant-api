package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.domain.MutantDNARepository;

public class DeleteSequences {

    private final MutantDNARepository mutantDNARepository;

    public DeleteSequences(MutantDNARepository mutantDNARepository) {
        this.mutantDNARepository = mutantDNARepository;
    }

    public void execute() {
        this.mutantDNARepository.deleteAll();
    }
}
