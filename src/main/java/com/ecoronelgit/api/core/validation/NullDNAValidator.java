package com.ecoronelgit.api.core.validation;

import com.ecoronelgit.api.core.exception.NullDNASequenceException;

public class NullDNAValidator implements DNAValidator {
    @Override
    public void apply(String[] dnaSequence) {
        for(String row : dnaSequence)
            if(isRowNull(row))
                throw new NullDNASequenceException();
    }

    private boolean isRowNull(String row) {
        return row == null;
    }
}
