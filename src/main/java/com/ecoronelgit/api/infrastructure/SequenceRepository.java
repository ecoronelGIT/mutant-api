package com.ecoronelgit.api.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<Sequence, String> {

}
