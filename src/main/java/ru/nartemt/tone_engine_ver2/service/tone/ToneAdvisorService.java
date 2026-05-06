package ru.nartemt.tone_engine_ver2.service.tone;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.config.ScoringConfig;
import ru.nartemt.tone_engine_ver2.model.dto.AdvisorResponseDto;
import ru.nartemt.tone_engine_ver2.model.entity.EquipmentType;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;
import ru.nartemt.tone_engine_ver2.repository.EquipmentRepository;
import ru.nartemt.tone_engine_ver2.service.album.AlbumService;
import ru.nartemt.tone_engine_ver2.service.equipment.AbstractEquipmentService;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ToneAdvisorService {

    private final AlbumService albumService;
    private final List<AbstractEquipmentService<? extends MusicalEquipment, ? extends EquipmentRepository<? extends MusicalEquipment>>> equipmentService;
    private final ScoringConfig.Scoring config;

    public AdvisorResponseDto getAdvisorResponseDto(Long albumId, BigDecimal budget) {
        Album album = albumService.findWithPresetsById(albumId)
                .orElseThrow(() -> new EntityNotFoundException("Album not found"));
        List<MusicalEquipment> recommendedEquipment = getRecommendedEquipment(album, budget);

        return new AdvisorResponseDto(album,
                recommendedEquipment,
                getFrequencyBars(album.getPreset()),
                getFinalPrice(recommendedEquipment));
    }

    private List<MusicalEquipment> getRecommendedEquipment(Album album, BigDecimal budget) {
        Map<EquipmentType, BigDecimal> budgetMap = Map.of(
                EquipmentType.GUITAR, budget.multiply(BigDecimal.valueOf(config.getBalance().getGuitar())),
                EquipmentType.AMPLIFIER, budget.multiply(BigDecimal.valueOf(config.getBalance().getAmplifier())),
                EquipmentType.PEDAL, budget.multiply(BigDecimal.valueOf(config.getBalance().getPedal()))
        );
        List<MusicalEquipment> equipmentList = new ArrayList<>();

        for (EquipmentType type : budgetMap.keySet()) {
            MusicalEquipment equipment = findTopMatch(album, budgetMap.get(type), type);
            if (equipment != null)
                equipmentList.add(equipment);
            else {
                log.warn("Equipment {} not found due to small budget", type);
                return Collections.emptyList();
            }
        }
        return equipmentList;
    }

    private MusicalEquipment findTopMatch(Album album, BigDecimal budget,
                                          EquipmentType type) {

        return equipmentService
                .stream()
                .filter(service -> service.isSupport(type))
                .map(p -> p
                        .findEquipmentByAlbumBudgetType(album, budget, type))
                .filter(matches -> !matches.isEmpty())
                .map(List::getFirst)
                .findFirst()
                .orElse(null);
    }

    private BigDecimal getFinalPrice(List<MusicalEquipment> equipmentList) {
        return equipmentList.stream()
                .map(MusicalEquipment::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Integer> getFrequencyBars(Preset preset) {
        int low = preset.getFreqLow();
        int mid = preset.getFreqMid();
        int high = preset.getFreqHigh();
        return List.of(
                low,
                (low + mid) / 2,
                mid,
                (mid + high) / 2,
                high,
                Math.max(0, high - 10));
    }
}
