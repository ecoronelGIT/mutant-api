package com.ecoronelgit.api.core.action;

import com.ecoronelgit.api.core.action.validator.*;

import java.util.Arrays;
import java.util.List;

public class IsMutant {
    public static final char EMPTY_DNA = ' ';
    public static final int MUTANT_SEQUENCE_AMOUNT = 4;
    public static final int INITIAL_SEQUENCE_AMOUNT = 1;
    public static final int MINIMAL_AMOUNT_SEQUENCE_FOR_MUTANT = 2;
    public static final int INITIAL_SEQUENCE = 0;
    private List<DNAValidator> dnaValidators =  Arrays.asList(  new EmptyDNAValidator(),
                                                                new NullDNAValidator(),
                                                                new SquareMatrixDNAValidator(),
                                                                new IncorrectCharacterDNAValidator(),
                                                                new ShortDNAValidator());

    public boolean execute(String[] dnaSequence) {
        dnaValidators.forEach(dnaRule -> dnaRule.apply(dnaSequence));
        if(checkDNASequence(dnaSequence))
            return true;
        throw new UnsupportedOperationException();
    }

    private boolean checkDNASequence(String[] dnaSequence) {
        int sequenceFound = INITIAL_SEQUENCE;
        for(String sequence : dnaSequence) {
            char currentDNA = sequence.charAt(0);
            int dnaAmount = INITIAL_SEQUENCE_AMOUNT;
            for(int i = 1; i < sequence.length(); i++) {
                if(currentDNA == sequence.charAt(i)) {
                    dnaAmount++;
                    if(dnaAmount == MUTANT_SEQUENCE_AMOUNT) {
                        sequenceFound++;
                        if(sequenceFound == MINIMAL_AMOUNT_SEQUENCE_FOR_MUTANT)
                            return true;
                        currentDNA = EMPTY_DNA;
                    }
                } else {
                    currentDNA = sequence.charAt(i);
                    dnaAmount = INITIAL_SEQUENCE_AMOUNT;
                }
            }
        }
        return false;
    }

}
