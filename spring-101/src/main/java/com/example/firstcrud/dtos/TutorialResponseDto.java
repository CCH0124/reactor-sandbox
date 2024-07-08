package com.example.firstcrud.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

@Schema(description = "Tutorial HTTP Response Model Information")
public record TutorialResponseDto(
    @Schema(
            accessMode = Schema.AccessMode.READ_ONLY,
            description = "Tutorial UUID",
            example = "123-123-123")
        String id,
    @Schema(description = "Tutorial title", example = "Taiwan") String title,
    @Schema(description = "Tutorial description", example = "Taiwan NO1.") String description,
    @Schema(description = "Tutorial status.", example = "true", defaultValue = "false")
        boolean published,
    @Schema(description = "Tutorial level.", example = "1") Integer level,
    @Schema(description = "Tutorial create time.", example = "2023-01-01") Date createdAt) {}
