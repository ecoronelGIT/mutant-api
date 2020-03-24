package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.domain.DNASequenceService;
import com.ecoronelgit.api.core.action.validation.*;

import java.util.Arrays;
import java.util.List;

public class IsMutant {
    private final DNASequenceService dnaSequenceService;
    private List<DNAValidator> dnaValidators =  Arrays.asList(  new EmptyDNAValidator(),
                                                                new NullDNAValidator(),
                                                                new SquareMatrixDNAValidator(),
                                                                new IncorrectCharacterDNAValidator(),
                                                                new ShortDNAValidator());
    public IsMutant(DNASequenceService dnaSequenceService) {
        this.dnaSequenceService = dnaSequenceService;
    }

    public boolean execute(String[] dnaSequence) {
        dnaValidators.forEach(dnaRule -> dnaRule.apply(dnaSequence));
        if(dnaSequenceService.dnaSequenceContainsMutant(dnaSequence))
            return true;
        return false;
    }

}
