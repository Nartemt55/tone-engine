package ru.nartemt.tone_engine_ver2.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "musical_equipment")
public abstract class MusicalEquipment {

    public static final Comparator<MusicalEquipment> BY_PRICE_ASC = Comparator.comparing(MusicalEquipment::getPrice);
    public static final Comparator<MusicalEquipment> BY_PRICE_DESC = BY_PRICE_ASC.reversed();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "brand", length = 50, nullable = false)
    private String brand;

    @Column(name = "model", length = 50)
    private String model;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "stock")
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "equipment_type", nullable = false)
    private EquipmentType equipmentType;

    public MusicalEquipment() { }

    public String getEquipmentFullName() {
        return brand + " " + model;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getStock() {
        return stock;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
