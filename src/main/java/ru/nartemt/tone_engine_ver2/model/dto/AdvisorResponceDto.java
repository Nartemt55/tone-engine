package ru.nartemt.tone_engine_ver2.model.dto;

import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

import java.math.BigDecimal;
import java.util.List;

public class AdvisorResponceDto {

    private Album album;
    private List<MusicalEquipment> gear;
    private List<Integer> frequencies;
    private BigDecimal totalPrice;

    public AdvisorResponceDto(Album album, List<MusicalEquipment> gear,
                              List<Integer> frequencies, BigDecimal totalPrice) {

        this.album = album;
        this.gear = gear;
        this.frequencies = frequencies;
        this.totalPrice = totalPrice;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<MusicalEquipment> getGear() {
        return gear;
    }

    public void setGear(List<MusicalEquipment> gear) {
        this.gear = gear;
    }

    public List<Integer> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(List<Integer> frequencies) {
        this.frequencies = frequencies;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
