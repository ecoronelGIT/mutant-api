package com.ecoronelgit.api.delivery.configuration;

import com.ecoronelgit.api.core.action.IsMutant;
import com.ecoronelgit.api.delivery.rest.MutantController;
import org.springframework.context.annotation.Bean;

public class RestConfiguration {

    @Bean
    public MutantController mutantController(IsMutant isMutant){
        return new MutantController(isMutant);
    }
}
