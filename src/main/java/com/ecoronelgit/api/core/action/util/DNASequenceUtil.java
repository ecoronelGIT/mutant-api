package com.ecoronelgit.api.core.action.util;

import java.util.HashMap;
import java.util.Map;

public class DNASequenceUtil {
    private static final char EMPTY_DNA = ' ';
    private static final int MUTANT_SEQUENCE_AMOUNT = 4;
    private static final int MINIMAL_AMOUNT_SEQUENCE_FOR_MUTANT = 2;
    private static final int INITIAL_SEQUENCE = 0;
    private static int sequenceFound = INITIAL_SEQUENCE;
    private static Map<Integer, DNACounter> dnaVerticalCounterMap;
    private static Map<Integer, DNACounter> dnaObliqueCounterMap;
    private static DNACounter dnaHorizontalCounter;

    public static boolean checkDNASequenceIsMutant(String[] dnaSequence) {
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

    private static void obliqueCheckDNA(String sequence, int column, int row, int dnaLength) {
        DNACounter dnaCounter = dnaObliqueCounterMap.get(column);
        if(dnaNotFound(dnaCounter)) {
            int actualColumn = getActualDNAColumn(column, row, dnaLength);
            checkDNA(dnaObliqueCounterMap.get(actualColumn), sequence.charAt(column));
        } else {
            dnaObliqueCounterMap.put(column, new DNACounter(sequence.charAt(column)));
        }

    }

    private static int getActualDNAColumn(int column, int row, int dnaLength) {
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

    private static boolean isObliqueColumn(int column, int row) {
        return (column - row) == 0;
    }

    private static boolean isInitialObliqueColumn(int column, int row) {
        return (column - row) < 0;
    }

    private static void verticalCheckDNA(String sequence, int column) {
        DNACounter dnaCounter = dnaVerticalCounterMap.get(column);
        if(dnaNotFound(dnaCounter)) {
            checkDNA(dnaCounter, sequence.charAt(column));
        } else {
            dnaVerticalCounterMap.put(column, new DNACounter(sequence.charAt(column)));
        }
    }

    private static void checkDNA(DNACounter dnaCounter, char dna) {
        if(dnaCounter.isSameDNA(dna)) {
            dnaCounter.addOne();
            checkIsMutantSequence(dnaCounter);
        } else {
            dnaCounter.setDna(dna);
        }
    }

    private static void checkIsMutantSequence(DNACounter dnaCounter) {
        if(dnaCounter.getCounter() == MUTANT_SEQUENCE_AMOUNT) {
            sequenceFound++;
            dnaCounter.setDna(EMPTY_DNA);
        }
    }

    private static boolean dnaNotFound(DNACounter dnaCounter) {
        return dnaCounter != null;
    }

    private static boolean isMutant() {
        return sequenceFound >= MINIMAL_AMOUNT_SEQUENCE_FOR_MUTANT;
    }

    private static void setInitialData(int dnaSequenceLength) {
        sequenceFound = INITIAL_SEQUENCE;
        dnaObliqueCounterMap = new HashMap();
        dnaVerticalCounterMap = new HashMap();
        dnaHorizontalCounter = new DNACounter(' ');
    }

}
