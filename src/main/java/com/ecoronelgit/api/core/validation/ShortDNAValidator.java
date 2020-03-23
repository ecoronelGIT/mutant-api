package com.ecoronelgit.api.core.validation;

import com.ecoronelgit.api.core.exception.ShortDNASequenceException;

public class ShortDNAValidator implements DNAValidator {
    @Override
    public void apply(String[] dnaSequence) {
        for(String sequence : dnaSequence)
            if(sequence.length() < 4)
                throw new ShortDNASequenceException();
    }
}
