package com.arirhel.thisorthat.controller;

import com.arirhel.thisorthat.model.Candidate;
import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.service.DilemmaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = DilemmaController.class)
class DilemmaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DilemmaService mockDilemmaService;

    @Mock
    Page<Dilemma> mockPage;

    @Mock
    Pageable mockPageable;

    @Mock
    Dilemma mockDilemma;

    @Test
    void verifyDilemmaSave() throws Exception {
        //given mockDilemmaService saves a mockDilemma and generates id "abc"
        when(mockDilemmaService.save(any())).thenReturn(mockDilemma);
        when(mockDilemma.getId()).thenReturn("abc");

        //when
        MvcResult result = mockMvc
          .perform(post("/dilemma/save")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "")
            .param("question", "Sample question?")
            .param("candidates[0]", "Candidate Zero")
            .param("candidates[1]", "Candidate One"))
          .andExpect(status().is3xxRedirection())
          .andReturn();

        //then
        String redirectUrl = result.getResponse().getRedirectedUrl();
        assertEquals("/dilemma/decide?id=abc", redirectUrl);
    }

    @Test
    void verifyDilemmaList() throws Exception {
        //given mockDilemmaService successfully returns zero+ dilemmas (we don't care how many)
        when(mockDilemmaService.findAll(anyInt(), anyInt())).thenReturn(mockPage);
        when(mockPage.getContent()).thenReturn(Collections.emptyList());
        when(mockPage.getPageable()).thenReturn(mockPageable);
        when(mockPageable.getPageNumber()).thenReturn(0);
        when(mockPage.getSize()).thenReturn(10);
        when(mockPage.getTotalPages()).thenReturn(1);

        //when
        MvcResult result = mockMvc
          .perform(get("/dilemma/list")
            .param("page", "0")
            .param("size", "10"))
          .andExpect(status().isOk())
          .andReturn();

        //then
        ModelAndView mav = result.getModelAndView();
        assertNotNull(mav);
        assertEquals("dilemma/list", mav.getViewName());
        assertEquals(Collections.emptyList(), mav.getModel().get("dilemmas"));
        assertEquals(0, mav.getModel().get("currentPage"));
        assertEquals(10, mav.getModel().get("size"));
        assertEquals(0, mav.getModel().get("ofPages"));
    }

    @Test
    void verifyDilemmaCreate() throws Exception {
        //when
        MvcResult result = mockMvc
          .perform(get("/dilemma/create"))
          .andExpect(status().isOk())
          .andReturn();

        //then
        ModelAndView mav = result.getModelAndView();
        assertNotNull(mav);
        assertEquals("dilemma/detail", mav.getViewName());
        assertEquals(new Dilemma(), mav.getModel().get("form"));
        assertEquals(Collections.singletonList(new Candidate()), mav.getModel().get("candidates"),
          "Form generation depends on a list of at lease one candidate.");
    }

    @Test
    void verifyDilemmaDetail_DilemmaFound() throws Exception {
        //given
        when(mockDilemmaService.findById("abc")).thenReturn(Optional.of(mockDilemma));

        //when
        MvcResult result = mockMvc
          .perform(get("/dilemma/detail")
            .param("id", "abc"))
          .andExpect(status().isOk())
          .andReturn();

        //then
        ModelAndView mav = result.getModelAndView();
        assertNotNull(mav);
        assertEquals("dilemma/detail", mav.getViewName());
        assertEquals(mockDilemma, mav.getModel().get("form"));
    }

    @Test
    void verifyDilemmaDetail_DilemmaNotFound() throws Exception {
        //given
        when(mockDilemmaService.findById("abc")).thenReturn(Optional.empty());

        //when
        MvcResult result = mockMvc
          .perform(get("/dilemma/detail")
            .param("id", "abc"))
          .andExpect(status().isOk())
          .andReturn();

        //then
        ModelAndView mav = result.getModelAndView();
        assertNotNull(mav);
        assertEquals("dilemma/notfound", mav.getViewName());
        assertEquals("abc", mav.getModel().get("id"));
    }

    @Test
    void verifyDilemmaDecide_DilemmaFound() throws Exception {
        //given
        List<Candidate> candidates = Arrays.asList(new Candidate("Candidate One"), new Candidate("Candidate Two"));
        Dilemma dilemma = new Dilemma("abc", "A question?", candidates);
        when(mockDilemmaService.findById("abc")).thenReturn(Optional.of(dilemma));

        //when
        MvcResult result = mockMvc
          .perform(get("/dilemma/decide")
            .param("id", "abc"))
          .andExpect(status().isOk())
          .andReturn();

        //then
        ModelAndView mav = result.getModelAndView();
        assertNotNull(mav);
        assertEquals("dilemma/decide", mav.getViewName());
        assertEquals("A question?", mav.getModel().get("question"));
        assertEquals(candidates, mav.getModel().get("candidates"));
    }

    @Test
    void verifyDilemmaDecide_DilemmaNotFound() throws Exception {
        //given
        when(mockDilemmaService.findById("abc")).thenReturn(Optional.empty());

        //when
        MvcResult result = mockMvc
          .perform(get("/dilemma/decide")
            .param("id", "abc"))
          .andExpect(status().isOk())
          .andReturn();

        //then
        ModelAndView mav = result.getModelAndView();
        assertNotNull(mav);
        assertEquals("dilemma/notfound", mav.getViewName());
        assertEquals("abc", mav.getModel().get("id"));
    }

    @Test
    void verifyDilemmaDelete() throws Exception {
        //when
        MvcResult result = mockMvc
          .perform(post("/dilemma/delete")
            .param("id", "abc")
            .param("page", "0")
            .param("size", "10"))
          .andExpect(status().is3xxRedirection())
          .andReturn();

        //then interactions
        verify(mockDilemmaService, times(1)).delete("abc");

        //and response
        String redirectUrl = result.getResponse().getRedirectedUrl();
        assertEquals("/dilemma/list?page=0&size=10", redirectUrl);
    }
}
