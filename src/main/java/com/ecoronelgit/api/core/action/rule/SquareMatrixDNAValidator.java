package com.ecoronelgit.api.core.action.rule;

import com.ecoronelgit.api.core.exception.SquareMatrixDNASequenceException;

public class SquareMatrixDNAValidator implements DNAValidator {
    @Override
    public void apply(String[] dnaSequence) {
        if(dnaSequenceIsNotSquareMatrix(dnaSequence)) {
            throw new SquareMatrixDNASequenceException();
        }
    }

    private boolean dnaSequenceIsNotSquareMatrix(String[] dnaSequence) {
        for(String row : dnaSequence) {
            if(columnLengthIsDifferentThatRow(dnaSequence, row))
                return true;
        }
        return false;
    }

    private boolean columnLengthIsDifferentThatRow(String[] dnaSequence, String row) {
        return row.length() != dnaSequence.length;
    }
}
