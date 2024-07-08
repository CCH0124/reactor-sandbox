package com.example.firstcrud.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Tutorial HTTP Response Model Information")
public record TutorialResponsePagingDto(
    @Schema(description = "Tutorial list.") @JsonProperty("data")
        List<TutorialResponseDto> tutorialResponseDto,
    @Schema(description = "current page.", example = "1") int currentPage,
    @Schema(description = "total items.", example = "2") long totalItems,
    @Schema(description = "total page.", example = "3") int totalPages) {}
