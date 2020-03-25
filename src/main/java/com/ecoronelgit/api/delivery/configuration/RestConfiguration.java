package com.ecoronelgit.api.delivery.configuration;

import com.ecoronelgit.api.core.action.DeleteSequences;
import com.ecoronelgit.api.core.action.GetStats;
import com.ecoronelgit.api.core.action.IsMutant;
import com.ecoronelgit.api.core.action.SaveMutantDNA;
import com.ecoronelgit.api.delivery.rest.MutantController;
import org.springframework.context.annotation.Bean;

public class RestConfiguration {

    @Bean
    public MutantController mutantController(IsMutant isMutant, SaveMutantDNA saveMutantDNA,
                                             GetStats getStats, DeleteSequences deleteSequences){
        return new MutantController(isMutant, saveMutantDNA, getStats, deleteSequences);
    }
}
