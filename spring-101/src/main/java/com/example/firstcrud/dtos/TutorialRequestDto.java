package com.example.firstcrud.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tutorial HTTP Request Model Information")
public record TutorialRequestDto(
    @Schema(description = "Tutorial title", example = "Taiwan") String title,
    @Schema(description = "Tutorial description", example = "Taiwan NO1.") String description,
    @Schema(description = "Tutorial status.", example = "true", defaultValue = "false")
        boolean published,
    @Schema(description = "Tutorial level.", example = "1") Integer level) {}
