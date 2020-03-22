package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.action.rule.*;

import java.util.Arrays;
import java.util.List;

public class IsMutant {
    private List<DNARule> dnaRules;

    public IsMutant(){
        this.dnaRules = Arrays.asList(  new EmptyDNARule(),
                                        new NullDNARule(),
                                        new SquareMatrixDNARule(),
                                        new IncorrectCharacterDNARule());
    }

    public boolean execute(String[] dnaSequence) {
        dnaRules.stream().forEach(dnaRule -> dnaRule.apply(dnaSequence));
        throw new UnsupportedOperationException();
    }
}
