package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.exception.EmptyDNASequenceException;
import com.ecoronelgit.api.core.exception.NullDNASequenceException;
import com.ecoronelgit.api.core.exception.SquareMatrixDNASequenceException;

public class IsMutant {

    public boolean execute(String[] dnaSequence) {
        if(dnaSequence.length == 0)
            throw new EmptyDNASequenceException();
        if(dnaSequenceHasNull(dnaSequence))
            throw new NullDNASequenceException();
        if(dnaSequenceIsNotSquareMatrix(dnaSequence)) {
            throw new SquareMatrixDNASequenceException();
        }
        throw new UnsupportedOperationException();
    }

    private boolean dnaSequenceHasNull(String[] dnaSequence) {
        for(String row : dnaSequence) {
            if(row == null)
                return true;
        }
        return false;
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
