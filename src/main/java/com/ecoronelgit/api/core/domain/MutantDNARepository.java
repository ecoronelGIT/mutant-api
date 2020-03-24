package com.ecoronelgit.api.core.domain;

public interface MutantDNARepository {

    boolean exist(String[] dnaSequence);

    void save(String[] dnaSequence);
}
