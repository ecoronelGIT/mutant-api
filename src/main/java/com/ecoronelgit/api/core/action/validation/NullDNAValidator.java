package com.ecoronelgit.api.core.action.validation;

import com.ecoronelgit.api.core.domain.exception.NullDNASequenceException;

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
