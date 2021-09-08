package com.arirhel.thisorthat.service.impl;

import com.arirhel.thisorthat.model.Candidate;
import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.repository.DilemmaRepository;
import com.arirhel.thisorthat.service.DilemmaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DilemmaServiceImplTest {

    @Mock
    DilemmaRepository dilemmaRepository;

    @InjectMocks
    DilemmaServiceImpl dilemmaService;

    @Mock
    Page<Dilemma> mockPage;

    @Test
    void save_doesNotAlterIdIfNotBlank() {
        //given
        Dilemma dilemma = new Dilemma("a", null, null);

        //mock
        when(dilemmaRepository.save(dilemma)).thenReturn(dilemma);

        //when
        Dilemma result = dilemmaService.save(dilemma);

        //then
        assertEquals("a", result.getId());
    }

    @Test
    void save_setsIdToNullIfBlank() {
        //given
        Dilemma dilemma = new Dilemma("", null, null);

        //mock
        when(dilemmaRepository.save(dilemma)).thenReturn(dilemma);

        //when
        Dilemma result = dilemmaService.save(dilemma);

        //then
        assertNull(result.getId());
    }

    @Test
    void save_filtersOutCandidatesWithBlankOrNullValue() {
        //given
        Dilemma dilemma = new Dilemma();
        Candidate candidateWithValue = new Candidate("Some value");
        Candidate candidateWithNoValue = new Candidate();
        Candidate candidateWithBlankValue = new Candidate(" ");
        dilemma.setCandidates(Arrays.asList(candidateWithValue, candidateWithNoValue, candidateWithBlankValue));

        //mock
        when(dilemmaRepository.save(dilemma)).thenReturn(dilemma);

        //when
        Dilemma result = dilemmaService.save(dilemma);

        //then
        assertFalse(result.getCandidates().contains(candidateWithNoValue));
    }

    @Test
    void findAll() {
        //mock
        when(dilemmaRepository.findAll(PageRequest.of(0, 10))).thenReturn(mockPage);

        //when
        Page<Dilemma> result = dilemmaService.findAll(0, 10);

        //then
        assertEquals(mockPage, result);
    }

    @Test
    void findById() {
        //given
        Dilemma dilemma = new Dilemma("abc", null, null);

        //mock
        when(dilemmaRepository.findById("abc")).thenReturn(Optional.of(dilemma));

        //when
        Optional<Dilemma> result = dilemmaService.findById("abc");

        //then
        assertTrue(result.isPresent());
        assertEquals(dilemma, result.get());
    }

}
