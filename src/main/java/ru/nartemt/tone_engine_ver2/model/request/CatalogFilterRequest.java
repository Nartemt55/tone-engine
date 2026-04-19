package ru.nartemt.tone_engine_ver2.model.request;

import ru.nartemt.tone_engine_ver2.model.entity.EquipmentType;

public record CatalogFilterRequest(EquipmentType type, String sort) {
}
