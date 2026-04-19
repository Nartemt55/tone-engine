package ru.nartemt.tone_engine_ver2.model.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record CartItemDto(long id, String brand, String model,
                          String imageUrl, BigDecimal price, int quantity) {
}
