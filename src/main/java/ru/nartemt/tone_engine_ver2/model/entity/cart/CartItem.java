package ru.nartemt.tone_engine_ver2.model.entity.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private MusicalEquipment product;

    public CartItem(int quantity, Cart cart, MusicalEquipment product) {
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }
}
