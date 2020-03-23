package com.ecoronelgit.api.core.service;

import com.ecoronelgit.api.core.util.DNACounterUtil;

import java.util.HashMap;
import java.util.Map;

public class DNASequenceService {

    private static final char EMPTY_DNA = ' ';
    private static final int MUTANT_SEQUENCE_AMOUNT = 4;
    private static final int MINIMAL_AMOUNT_SEQUENCE_FOR_MUTANT = 2;
    private static final int INITIAL_SEQUENCE = 0;
    private int sequenceFound = INITIAL_SEQUENCE;
    private Map<Integer, DNACounterUtil> dnaVerticalCounterMap;
    private Map<Integer, DNACounterUtil> dnaObliqueCounterMap;
    private DNACounterUtil dnaHorizontalCounter;

    public boolean dnaSequenceContainsMutant(String[] dnaSequence) {
        setInitialData(dnaSequence.length);
        for(int row = 0; row < dnaSequence.length; row++) {
            String sequence = dnaSequence[row];
            for(int column = 0; column < sequence.length(); column++) {
                checkDNA(dnaHorizontalCounter, sequence.charAt(column));
                verticalCheckDNA(sequence, column);
                obliqueCheckDNA(sequence, column, row, dnaSequence.length);
                if(isMutant())
                    return true;
            }
        }
        return false;
    }

    private void obliqueCheckDNA(String sequence, int column, int row, int dnaLength) {
        DNACounterUtil dnaCounterUtil = dnaObliqueCounterMap.get(column);
        if(dnaNotFound(dnaCounterUtil)) {
            int actualColumn = getActualDNAColumn(column, row, dnaLength);
            checkDNA(dnaObliqueCounterMap.get(actualColumn), sequence.charAt(column));
        } else {
            dnaObliqueCounterMap.put(column, new DNACounterUtil(sequence.charAt(column)));
        }

    }

    private int getActualDNAColumn(int column, int row, int dnaLength) {
        if(isInitialObliqueColumn(column, row)) {
            int actualColumn = dnaLength - row;
            dnaObliqueCounterMap.get(actualColumn).setDna(EMPTY_DNA);
            return  actualColumn;
        } else if (isObliqueColumn(column, row)) {
            return  0;
        } else {
            return column - row;
        }
    }

    private boolean isObliqueColumn(int column, int row) {
        return (column - row) == 0;
    }

    private boolean isInitialObliqueColumn(int column, int row) {
        return (column - row) < 0;
    }

    private void verticalCheckDNA(String sequence, int column) {
        DNACounterUtil dnaCounterUtil = dnaVerticalCounterMap.get(column);
        if(dnaNotFound(dnaCounterUtil)) {
            checkDNA(dnaCounterUtil, sequence.charAt(column));
        } else {
            dnaVerticalCounterMap.put(column, new DNACounterUtil(sequence.charAt(column)));
        }
    }

    private void checkDNA(DNACounterUtil dnaCounterUtil, char dna) {
        if(dnaCounterUtil.isSameDNA(dna)) {
            dnaCounterUtil.addOne();
            checkIsMutantSequence(dnaCounterUtil);
        } else {
            dnaCounterUtil.setDna(dna);
        }
    }

    private void checkIsMutantSequence(DNACounterUtil dnaCounterUtil) {
        if(dnaCounterUtil.getCounter() == MUTANT_SEQUENCE_AMOUNT) {
            sequenceFound++;
            dnaCounterUtil.setDna(EMPTY_DNA);
        }
    }

    private boolean dnaNotFound(DNACounterUtil dnaCounterUtil) {
        return dnaCounterUtil != null;
    }

    private boolean isMutant() {
        return sequenceFound >= MINIMAL_AMOUNT_SEQUENCE_FOR_MUTANT;
    }

    private void setInitialData(int dnaSequenceLength) {
        sequenceFound = INITIAL_SEQUENCE;
        dnaObliqueCounterMap = new HashMap();
        dnaVerticalCounterMap = new HashMap();
        dnaHorizontalCounter = new DNACounterUtil(' ');
    }
}
