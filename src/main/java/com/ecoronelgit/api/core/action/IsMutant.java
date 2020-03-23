package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.action.util.DNASequenceUtil;
import com.ecoronelgit.api.core.action.validator.*;

import java.util.Arrays;
import java.util.List;

public class IsMutant {
    private List<DNAValidator> dnaValidators =  Arrays.asList(  new EmptyDNAValidator(),
                                                                new NullDNAValidator(),
                                                                new SquareMatrixDNAValidator(),
                                                                new IncorrectCharacterDNAValidator(),
                                                                new ShortDNAValidator());

    public boolean execute(String[] dnaSequence) {
        dnaValidators.forEach(dnaRule -> dnaRule.apply(dnaSequence));
        if(DNASequenceUtil.checkDNASequenceIsMutant(dnaSequence))
            return true;
        throw new UnsupportedOperationException();
    }

}
