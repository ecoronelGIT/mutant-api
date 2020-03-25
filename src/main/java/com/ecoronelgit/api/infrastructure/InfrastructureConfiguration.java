package com.ecoronelgit.api.infrastructure;

import com.ecoronelgit.api.core.domain.MutantDNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfiguration {

    final SequenceRepository sequenceRepository;

    public InfrastructureConfiguration(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    @Bean
    public MutantDNARepository mutantDNARepository() {
        return new MongoDBMutantDNARepository(sequenceRepository);
    }

}
