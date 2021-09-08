package com.arirhel.thisorthat.service.impl;

import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.repository.DilemmaRepository;
import com.arirhel.thisorthat.service.DilemmaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DilemmaServiceImpl implements DilemmaService {

    private final DilemmaRepository dilemmaRepository;

    @Autowired
    public DilemmaServiceImpl(DilemmaRepository dilemmaRepository) {
        this.dilemmaRepository = dilemmaRepository;
    }

    @Override
    public Dilemma save(Dilemma dilemma) {
        // This is important: It determines if MongoRepository generates an id
        if (StringUtils.isBlank(dilemma.getId())) dilemma.setId(null);
        if (dilemma.getCandidates() != null) filterOutBlankCandidates(dilemma);
        return dilemmaRepository.save(dilemma);
    }

    private void filterOutBlankCandidates(Dilemma dilemma) {
        dilemma.setCandidates(dilemma.getCandidates()
          .stream().filter(candidate -> StringUtils.isNotBlank(candidate.getValue()))
          .collect(Collectors.toList()));
    }

    @Override
    public Page<Dilemma> findAll(int page, int size) {
        return dilemmaRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Dilemma> findById(String id) {
        return dilemmaRepository.findById(id);
    }

}
