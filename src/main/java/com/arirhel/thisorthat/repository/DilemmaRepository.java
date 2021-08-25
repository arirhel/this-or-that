package com.arirhel.thisorthat.repository;

import com.arirhel.thisorthat.model.Dilemma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DilemmaRepository extends MongoRepository<Dilemma, String> {

    Page<Dilemma> findAll(Pageable pageable);

}
