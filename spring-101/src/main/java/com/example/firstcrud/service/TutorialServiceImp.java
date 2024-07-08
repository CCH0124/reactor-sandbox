package com.example.firstcrud.service;

import com.example.firstcrud.domain.Tutorial;
import com.example.firstcrud.dtos.TutorialRequestDto;
import com.example.firstcrud.dtos.TutorialResponseDto;
import com.example.firstcrud.dtos.TutorialResponsePagingDto;
import com.example.firstcrud.exception.ResourceNotFoundException;
import com.example.firstcrud.repository.TutorialRepository;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TutorialServiceImp implements TutorialService {

  @Autowired TutorialRepository tutorialRepository;

  @Override
  public TutorialResponsePagingDto getAllTutorials(String title, int page, int size) {
    Page<Tutorial> pageData;
    Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "title"));
    if (Objects.isNull(title)) {
      pageData = tutorialRepository.findAll(paging);
    } else {
      pageData = tutorialRepository.findByTitleContaining(title, paging);
    }
    var tutorials = pageData.getContent().stream().map(this::toTutorialResponseDto).toList();
    return new TutorialResponsePagingDto(
        tutorials, pageData.getNumber(), pageData.getTotalElements(), pageData.getTotalPages());
  }

  @Override
  public TutorialResponseDto getTutorialById(String id) {
    return tutorialRepository
        .findById(UUID.fromString(id))
        .map(this::toTutorialResponseDto)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
  }

  @Override
  public TutorialResponseDto createTutorial(TutorialRequestDto tutorialRequestDto) {
    var tutorial = tutorialRepository.save(toTutorial(tutorialRequestDto));

    return toTutorialResponseDto(tutorial);
  }

  @Override
  public TutorialResponseDto updateTutorial(String id, TutorialRequestDto tutorialRequestDto) {
    var tutorial =
        tutorialRepository
            .findById(UUID.fromString(id))
            .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

    tutorial.setTitle(tutorialRequestDto.title());
    tutorial.setPublished(tutorialRequestDto.published());
    tutorial.setDescription(tutorialRequestDto.description());
    var saveData = tutorialRepository.save(tutorial);
    ;
    return toTutorialResponseDto(saveData);
  }

  @Override
  public void deleteTutorial(String id) {
    tutorialRepository.deleteById(UUID.fromString(id));
  }

  @Override
  public void deleteAllTutorials() {
    tutorialRepository.deleteAll();
  }

  @Override
  public TutorialResponsePagingDto findByPublished(int page, int size) {
    Pageable paging = PageRequest.of(page, size);
    var pageData = tutorialRepository.findByPublished(true, paging);
    var tutorials = pageData.getContent().stream().map(this::toTutorialResponseDto).toList();
    return new TutorialResponsePagingDto(
        tutorials, pageData.getNumber(), pageData.getTotalElements(), pageData.getTotalPages());
  }

  TutorialResponseDto toTutorialResponseDto(Tutorial tutorial) {
    return new TutorialResponseDto(
        tutorial.getId().toString(),
        tutorial.getTitle(),
        tutorial.getDescription(),
        tutorial.isPublished(),
        tutorial.getLevel(),
        tutorial.getCreatedAt());
  }

  Tutorial toTutorial(TutorialRequestDto dto) {
    Tutorial tutorial = new Tutorial();
    tutorial.setTitle(dto.title());
    tutorial.setDescription(dto.description());
    tutorial.setPublished(dto.published());
    tutorial.setLevel(dto.level());

    return tutorial;
  }
}
