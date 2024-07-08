package com.example.firstcrud.service;

import com.example.firstcrud.dtos.TutorialRequestDto;
import com.example.firstcrud.dtos.TutorialResponseDto;
import com.example.firstcrud.dtos.TutorialResponsePagingDto;

public interface TutorialService {

  TutorialResponsePagingDto getAllTutorials(String title, int page, int size);

  TutorialResponseDto getTutorialById(String id);

  TutorialResponseDto createTutorial(TutorialRequestDto tutorialRequestDto);

  TutorialResponseDto updateTutorial(String id, TutorialRequestDto tutorialRequestDto);

  void deleteTutorial(String id);

  void deleteAllTutorials();

  TutorialResponsePagingDto findByPublished(int page, int size);
}
