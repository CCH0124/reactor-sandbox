package com.example.firstcrud.mapper;

import com.example.firstcrud.domain.Tutorial;
import com.example.firstcrud.dtos.TutorialResponseDto;
import com.example.firstcrud.utils.function.DataMapper;

public class TutorialResponseDtoMapper implements DataMapper<Tutorial, TutorialResponseDto>{

    @Override
    public TutorialResponseDto map(Tutorial source) {
        return new TutorialResponseDto(
            source.getId().toString(),
            source.getTitle(),
            source.getDescription(),
            source.isPublished(),
            source.getLevel(),
            source.getCreatedAt());
    }
    
}
