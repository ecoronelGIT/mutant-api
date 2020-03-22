package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.action.rule.*;

import java.util.Arrays;
import java.util.List;

public class IsMutant {
    private List<DNAValidator> dnaValidators =  Arrays.asList(  new EmptyDNAValidator(),
                                                                new NullDNAValidator(),
                                                                new SquareMatrixDNAValidator(),
                                                                new IncorrectCharacterDNAValidator());

    public boolean execute(String[] dnaSequence) {
        dnaValidators.forEach(dnaRule -> dnaRule.apply(dnaSequence));
        throw new UnsupportedOperationException();
    }
}
