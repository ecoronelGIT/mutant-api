package com.ecoronelgit.api.core.configuration;

import com.ecoronelgit.api.core.action.GetStats;
import com.ecoronelgit.api.core.action.IsMutant;
import com.ecoronelgit.api.core.action.SaveMutantDNA;
import com.ecoronelgit.api.core.domain.DNASequenceService;
import com.ecoronelgit.api.core.domain.MutantDNARepository;
import com.ecoronelgit.api.infrastructure.MemoryMutantDNARepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MutantConfiguration {

    @Bean
    public IsMutant isMutant(DNASequenceService dnaSequenceService) {
        return new IsMutant(dnaSequenceService);
    }

    @Bean
    public SaveMutantDNA saveMutantDNA(MutantDNARepository mutantDNARepository) {
        return new SaveMutantDNA(mutantDNARepository);
    }

    @Bean
    public GetStats getStats(MutantDNARepository mutantDNARepository) {
        return new GetStats(mutantDNARepository);
    }

    @Bean
    public MutantDNARepository mutantDNARepository() {
        return new MemoryMutantDNARepository();
    }

    @Bean
    public DNASequenceService dnaSequenceService(){
        return new DNASequenceService();
    }
}
