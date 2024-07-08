package com.example.firstcrud.mapper;

import com.example.firstcrud.domain.Tutorial;
import com.example.firstcrud.dtos.TutorialRequestDto;
import com.example.firstcrud.utils.function.DataMapper;

public class TutorialMapper implements DataMapper<TutorialRequestDto, Tutorial>{

    @Override
    public Tutorial map(TutorialRequestDto source) {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle(source.title());
        tutorial.setDescription(source.description());
        tutorial.setPublished(source.published());
        tutorial.setLevel(source.level());
        return tutorial;
    }
    
}
