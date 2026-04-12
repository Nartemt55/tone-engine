package ru.nartemt.tone_engine_ver2.model.dto;

import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

import java.math.BigDecimal;
import java.util.List;

public class AdvisorResponseDto {

    private Album album;
    private List<MusicalEquipment> equipmentList;
    private List<Integer> frequencies;
    private BigDecimal totalPrice;

    public AdvisorResponseDto(Album album, List<MusicalEquipment> equipmentList, List<Integer> frequencies, BigDecimal totalPrice) {
        this.album = album;
        this.equipmentList = equipmentList;
        this.frequencies = frequencies;
        this.totalPrice = totalPrice;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<MusicalEquipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<MusicalEquipment> equipmentList) {
        this.equipmentList = equipmentList;
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
