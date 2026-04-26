package ru.nartemt.tone_engine_ver2.model.request;

import jakarta.validation.constraints.Min;

public record AddToCartRequest(@Min(1) Long id, @Min(1) Integer quantity ) {
}
