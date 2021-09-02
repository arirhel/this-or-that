package com.arirhel.thisorthat.service.impl;

import com.arirhel.thisorthat.model.Candidate;
import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.repository.DilemmaRepository;
import com.arirhel.thisorthat.service.DilemmaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DilemmaServiceImplTest {

    @Mock
    DilemmaRepository dilemmaRepository = mock(DilemmaRepository.class);

    DilemmaService dilemmaService = new DilemmaServiceImpl(dilemmaRepository);

    @Test
    void save_filtersOutCandidatesWithNullValue() {
        // given
        Dilemma dilemma = new Dilemma();
        Candidate candidateWithValue = new Candidate();
        candidateWithValue.setValue("Some value");
        Candidate candidateWithNoValue = new Candidate();
        dilemma.setCandidates(Arrays.asList(candidateWithValue, candidateWithNoValue));

        // mock
        when(dilemmaRepository.save(dilemma)).thenReturn(dilemma);

        // when
        Dilemma result = dilemmaService.save(dilemma);

        // then
        assertFalse(result.getCandidates().contains(candidateWithNoValue));
    }

}
