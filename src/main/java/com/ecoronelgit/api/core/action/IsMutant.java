package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.exception.EmptyDNASequenceException;
import com.ecoronelgit.api.core.exception.MatrixDNASequenceException;

public class IsMutant {

    public boolean execute(String[] dnaSequence) {
        if(dnaSequence.length == 0)
            throw new EmptyDNASequenceException();
        if(dnaSequenceIsNotSquareMatrix(dnaSequence)) {
            throw new MatrixDNASequenceException();
        }
        throw new UnsupportedOperationException();
    }

    private boolean dnaSequenceIsNotSquareMatrix(String[] dnaSequence) {
        for(String row : dnaSequence) {
            if(isRowEmpty(row) || columnLengthIsDifferentThatRow(dnaSequence, row))
                return true;
        }
        return false;
    }

    private boolean columnLengthIsDifferentThatRow(String[] dnaSequence, String row) {
        return row.length() != dnaSequence.length;
    }

    private boolean isRowEmpty(String row) {
        return row == null;
    }
}
