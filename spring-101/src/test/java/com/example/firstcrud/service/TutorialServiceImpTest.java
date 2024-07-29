package com.example.firstcrud.service;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.example.firstcrud.domain.Tutorial;
import com.example.firstcrud.dtos.TutorialRequestDto;
import com.example.firstcrud.dtos.TutorialResponseDto;
import com.example.firstcrud.dtos.TutorialResponsePagingDto;
import com.example.firstcrud.repository.TutorialRepository;

@ExtendWith(MockitoExtension.class)
class TutorialServiceImpTest {

    @InjectMocks
    private TutorialServiceImp tutorialService;

    @Mock
    private TutorialRepository tutorialRepository;

    private Tutorial mockTutorial;
    private PageRequest pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockTutorial = new Tutorial();
        mockTutorial.setId(UUID.randomUUID());
        mockTutorial.setTitle("Title");
        mockTutorial.setDescription("Description");
        mockTutorial.setPublished(true);
        mockTutorial.setLevel(2);

        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "title"));
    }

    @Test
    void testGetAllTutorials_WhenTitleIsNull() {
        Page<Tutorial> page = new PageImpl<>(List.of(mockTutorial));
        when(tutorialRepository.findAll(any(Pageable.class))).thenReturn(page);

        TutorialResponsePagingDto result = tutorialService.getAllTutorials(null, 0, 10);

        assertEquals(1L, result.totalItems());
        assertEquals(1L, result.totalPages());
        verify(tutorialRepository).findAll(any(Pageable.class));
    }

    @Test
    void testGetAllTutorials_WhenTitleIsNotNull() {
        Page<Tutorial> page = new PageImpl<>(List.of(mockTutorial));
        when(tutorialRepository.findByTitleContaining(anyString(), any(Pageable.class))).thenReturn(page);

        TutorialResponsePagingDto result = tutorialService.getAllTutorials("Title", 0, 10);

        assertEquals(1, result.totalItems());
        assertEquals(1, result.totalItems());
        verify(tutorialRepository).findByTitleContaining(anyString(), any(Pageable.class));
    }

    @Test
    void testGetTutorialById() {
        when(tutorialRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockTutorial));

        TutorialResponseDto result = tutorialService.getTutorialById(mockTutorial.getId().toString());

        assertEquals(mockTutorial.getTitle(), result.title());
        verify(tutorialRepository).findById(any(UUID.class));
    }

    @Test
    void testCreateTutorial() {
        when(tutorialRepository.save(any(Tutorial.class))).thenReturn(mockTutorial);

        TutorialRequestDto requestDto = new TutorialRequestDto("Title", "Description", true, 1);
        TutorialResponseDto result = tutorialService.createTutorial(requestDto);

        assertEquals(requestDto.title(), result.title());
        verify(tutorialRepository).save(any(Tutorial.class));
    }

    @Test
    void testUpdateTutorial() {
        when(tutorialRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockTutorial));
        when(tutorialRepository.save(any(Tutorial.class))).thenReturn(mockTutorial);

        TutorialRequestDto requestDto = new TutorialRequestDto("Updated Title", "Updated Description", false, 2);
        TutorialResponseDto result = tutorialService.updateTutorial(mockTutorial.getId().toString(), requestDto);

        assertEquals(requestDto.title(), result.title());
        verify(tutorialRepository).findById(any(UUID.class));
        verify(tutorialRepository).save(any(Tutorial.class));
    }

    @Test
    void testDeleteTutorial() {
        doNothing().when(tutorialRepository).deleteById(any(UUID.class));

        tutorialService.deleteTutorial(mockTutorial.getId().toString());

        verify(tutorialRepository).deleteById(any(UUID.class));
    }

    @Test
    void testDeleteAllTutorials() {
        doNothing().when(tutorialRepository).deleteAll();

        tutorialService.deleteAllTutorials();

        verify(tutorialRepository).deleteAll();
    }

    @Test
    void testFindByPublished() {
        Page<Tutorial> page = new PageImpl<>(List.of(mockTutorial));
        when(tutorialRepository.findByPublished(true, pageable)).thenReturn(page);

        TutorialResponsePagingDto result = tutorialService.findByPublished(0, 10);

        assertEquals(1, result.totalItems());
        assertEquals(1, result.totalPages());
        verify(tutorialRepository).findByPublished(true, pageable);
    }
}