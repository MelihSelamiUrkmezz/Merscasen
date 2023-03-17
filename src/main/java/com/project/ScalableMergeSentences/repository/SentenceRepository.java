package com.project.ScalableMergeSentences.repository;

import com.project.ScalableMergeSentences.model.entity.Sentence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentenceRepository extends MongoRepository<Sentence, String> {
}
