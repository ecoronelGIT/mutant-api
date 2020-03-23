package com.ecoronelgit.api.core.validation;

import com.ecoronelgit.api.core.exception.EmptyDNASequenceException;

public class EmptyDNAValidator implements DNAValidator {
    @Override
    public void apply(String[] dnaSequence) {
        if(dnaSequence.length == 0)
            throw new EmptyDNASequenceException();
    }
}
