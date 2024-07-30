package com.example.firstcrud.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.firstcrud.dtos.*;
import com.example.firstcrud.exception.ResourceNotFoundException;
import com.example.firstcrud.service.TutorialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest(TutorialController.class)
@DisabledInAotMode
@ActiveProfiles("test")
@DisplayName("Tutorial Controller")
public class TutorialControllerTest {

  @MockBean private TutorialService tutorialService;

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void shouldGetAllTutorials() throws Exception {

    var currentPage = 0;
    var tutorialResponseDto =
        List.of(
            new TutorialResponseDto("1", "title1", "desc1", true, 1, new Date()),
            new TutorialResponseDto("2", "title2", "desc2", false, 2, new Date()));

    var tutorialResponsePagingDto =
        new TutorialResponsePagingDto(tutorialResponseDto, currentPage, 2, 1);
    when(this.tutorialService.getAllTutorials(
            Mockito.any(String.class), Mockito.anyInt(), Mockito.anyInt()))
        .thenReturn(tutorialResponsePagingDto);
    ;
    MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
    paramsMap.add("title", "title");
    paramsMap.add("page", "3");
    paramsMap.add("size", "3");
    this.mockMvc
        .perform(get("/api/tutorials").params(paramsMap).contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.tutorialResponseDto.size()").value(tutorialResponseDto.size()))
        .andExpect(jsonPath("$.currentPage").value(is(currentPage)));
  }

  @Test
  void shouldReturnNotFoundTutorialById() throws Exception {
    var id = "123456";
    when(this.tutorialService.getTutorialById(id))
        .thenThrow(new ResourceNotFoundException("Not found Tutorial with id = " + id));
    mockMvc.perform(get("/api/tutorials/{id}", id)).andExpect(status().isNotFound()).andDo(print());
  }

  @Test
  void shouldGetTutorialById() throws Exception {
    var id = "123456";
    var res = new TutorialResponseDto("123456", "title1", "desc1", true, 1, new Date());
    when(this.tutorialService.getTutorialById(id)).thenReturn(res);
    mockMvc
        .perform(get("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(jsonPath("$.id").value(is(id)))
        .andExpect(jsonPath("$.title").value(res.title()))
        .andExpect(jsonPath("$.description").value(res.description()))
        .andExpect(jsonPath("$.published").value(res.published()));
  }

  @Test
  void shouldCreateTutorial() throws Exception {
    var tutorial = new TutorialRequestDto("title1", "desc1", true, 2);

    mockMvc
        .perform(
            post("/api/tutorials")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tutorial)))
        .andExpect(status().isCreated())
        .andDo(print());
  }

  @Test
  void shouldUpdateTutorial() throws Exception {
    var id = "123456";

    var tutorial = new TutorialRequestDto("title1", "desc1", true, 2);
    var tutorialResponseDto = new TutorialResponseDto(id, "title1", "desc1", false, 1, new Date());

    when(tutorialService.updateTutorial(id, tutorial)).thenReturn(tutorialResponseDto);

    mockMvc
        .perform(
            put("/api/tutorials/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tutorial)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value(tutorialResponseDto.title()))
        .andExpect(jsonPath("$.description").value(tutorialResponseDto.description()))
        .andExpect(jsonPath("$.published").value(tutorialResponseDto.published()))
        .andExpect(jsonPath("$.level").value(tutorialResponseDto.level()));
  }

  @Test
  void shouldResourceNotFoundUpdateTutorial() throws Exception {
    var id = "123456";
    var tutorial = new TutorialRequestDto("title1", "desc1", true, 2);

    when(tutorialService.updateTutorial(id, tutorial))
        .thenThrow(new ResourceNotFoundException("Not found Tutorial with id = " + id));

    mockMvc
        .perform(
            put("/api/tutorials/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tutorial)))
        .andDo(print())
        .andExpect(status().isNotFound());
  }
}
