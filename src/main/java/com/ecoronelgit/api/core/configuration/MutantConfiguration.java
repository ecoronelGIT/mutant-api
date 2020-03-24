package com.ecoronelgit.api.core.configuration;

import com.ecoronelgit.api.core.action.IsMutant;
import com.ecoronelgit.api.core.domain.DNASequenceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MutantConfiguration {

    @Bean
    public IsMutant isMutant(DNASequenceService dnaSequenceService) {
        return new IsMutant(dnaSequenceService);
    }

    @Bean
    public DNASequenceService dnaSequenceService(){
        return new DNASequenceService();
    }
}
