package ru.nartemt.tone_engine_ver2.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.model.dto.AdvisorResponceDto;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;
import ru.nartemt.tone_engine_ver2.service.equipment.ProductProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToneAdvisorService {

    private final AlbumService albumService;
    private final List<ProductProvider<? extends MusicalEquipment>> equipmentProviders;

    @Autowired
    public ToneAdvisorService(AlbumService albumService,
                              List<ProductProvider<? extends MusicalEquipment>> equipmentProviders) {
        this.albumService = albumService;
        this.equipmentProviders = equipmentProviders;
    }

    public AdvisorResponceDto getRecommendations(long albumId, BigDecimal budget) {
        Album album = albumService.findById(albumId)
                .orElseThrow(() -> new EntityNotFoundException("Album not found"));
        Preset preset = albumService.getPresetByAlbumId(albumId)
                .orElseThrow(() -> new EntityNotFoundException("Album not found"));

        // budget management
        BigDecimal gBudget = budget.multiply(new BigDecimal("0.5"));
        BigDecimal aBudget = budget.multiply(new BigDecimal("0.35"));
        BigDecimal pBudget = budget.multiply(new BigDecimal("0.15"));

        List<MusicalEquipment> gears = new ArrayList<>();
        findTopMatch(preset, gBudget, "Guitar").ifPresent(gears::add);
        findTopMatch(preset, aBudget, "Amplifier").ifPresent(gears::add);
        findTopMatch(preset, pBudget, "Pedal").ifPresent(gears::add);

        BigDecimal finalPrice = gears.stream()
                .map(MusicalEquipment::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AdvisorResponceDto(album, gears, getFrequencyBars(preset), finalPrice);
    }

    private Optional<? extends MusicalEquipment> findTopMatch(Preset preset, BigDecimal budget,
                                                              String type) {

        return equipmentProviders.stream()
                .filter(p -> p.isSupport(type))
                .map(p -> p.findMatches(preset, budget))
                .filter(matches -> !matches.isEmpty())
                .map(matches -> matches.get(0))
                .findFirst();
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
