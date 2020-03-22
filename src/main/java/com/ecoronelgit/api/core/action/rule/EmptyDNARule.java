package com.ecoronelgit.api.core.action.rule;

import com.ecoronelgit.api.core.exception.EmptyDNASequenceException;

public class EmptyDNARule implements DNARule {
    @Override
    public void apply(String[] dnaSequence) {
        if(dnaSequence.length == 0)
            throw new EmptyDNASequenceException();
    }
}
