package ru.nartemt.tone_engine_ver2.model.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductShortDto(Long id, String brand, String model,
                              String imageUrl, BigDecimal price) {
}