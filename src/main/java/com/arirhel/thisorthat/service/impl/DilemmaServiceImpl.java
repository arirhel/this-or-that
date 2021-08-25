package com.arirhel.thisorthat.service.impl;

import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.repository.DilemmaRepository;
import com.arirhel.thisorthat.service.DilemmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DilemmaServiceImpl implements DilemmaService {

    private final DilemmaRepository dilemmaRepository;

    @Autowired
    public DilemmaServiceImpl(DilemmaRepository dilemmaRepository) {
        this.dilemmaRepository = dilemmaRepository;
    }

    @Override
    public Dilemma save(Dilemma dilemma) {
        return dilemmaRepository.save(dilemma);
    }

    @Override
    public Page<Dilemma> findAll(int page, int size) {
        return dilemmaRepository.findAll(PageRequest.of(page, size));
    }

}
