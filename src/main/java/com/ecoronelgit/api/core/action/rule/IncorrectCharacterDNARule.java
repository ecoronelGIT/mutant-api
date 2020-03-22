package com.ecoronelgit.api.core.action.rule;

import com.ecoronelgit.api.core.exception.IncorrectDNASequenceException;

public class IncorrectCharacterDNARule implements DNARule {
    private static final String REGEX_FOR_DNA = "[acgtACGT]+\\.?";

    @Override
    public void apply(String[] dnaSequence) {
        for(String row : dnaSequence)
            if(dnaSequenceNotHaveCorrectCharacters(row))
                throw new IncorrectDNASequenceException();
    }

    private boolean dnaSequenceNotHaveCorrectCharacters(String row) {
        return !row.matches(REGEX_FOR_DNA);
    }

}
