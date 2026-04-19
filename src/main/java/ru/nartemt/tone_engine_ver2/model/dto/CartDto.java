package ru.nartemt.tone_engine_ver2.model.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartDto(List<CartItemDto> cartItems, BigDecimal totalPrice, int itemsAmount) {
}