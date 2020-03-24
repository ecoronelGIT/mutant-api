package com.ecoronelgit.api.core.domain;

import java.util.Collection;
import java.util.List;

public interface MutantDNARepository {

    boolean exist(String[] dnaSequence);

    void save(String[] dnaSequence, boolean isMutant);

    List<String[]> getDNASequences(boolean isMutant);

}
