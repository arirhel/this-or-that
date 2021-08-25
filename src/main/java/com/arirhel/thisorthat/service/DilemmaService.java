package com.arirhel.thisorthat.service;

import com.arirhel.thisorthat.model.Dilemma;
import org.springframework.data.domain.Page;

public interface DilemmaService {

    Dilemma save(Dilemma dilemma);

    Page<Dilemma> findAll(int page, int size);

}
