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
    private static DNACounter dnaHorizontalCounter;

    public static boolean checkDNASequenceIsMutant(String[] dnaSequence) {
        setInitialData();
        for(String sequence : dnaSequence) {
            for(int column = 0; column < sequence.length(); column++) {
                verticalCheckDNA(sequence, column);
                checkDNA(dnaHorizontalCounter, sequence.charAt(column));
                if(isMutant())
                    return true;
            }
        }
        return false;
    }

    private static void verticalCheckDNA(String sequence, int column) {
        DNACounter dnaCounter = dnaVerticalCounterMap.get(column);
        if(dnaNotFoundInVerticalCounter(dnaCounter)) {
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

    private static boolean dnaNotFoundInVerticalCounter(DNACounter dnaCounter) {
        return dnaCounter != null;
    }

    private static boolean isMutant() {
        return sequenceFound >= MINIMAL_AMOUNT_SEQUENCE_FOR_MUTANT;
    }

    private static void setInitialData() {
        sequenceFound = INITIAL_SEQUENCE;
        dnaVerticalCounterMap = new HashMap();
        dnaHorizontalCounter = new DNACounter(' ');
    }

}
