package com.ecoronelgit.api.core.action;

public class IsMutant {

    public boolean execute(String[] dnaSequence) {
        if(dnaSequence.length == 0)
            return false;
        throw new UnsupportedOperationException();
    }
}
