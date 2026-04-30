package ru.nartemt.tone_engine_ver2.model.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CartDto(Long id, List<CartItemDto> cartItems, UserShortDto user, BigDecimal totalPrice, Integer itemsAmount) {
}