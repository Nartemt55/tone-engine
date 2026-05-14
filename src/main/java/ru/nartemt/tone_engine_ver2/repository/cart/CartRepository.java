package ru.nartemt.tone_engine_ver2.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nartemt.tone_engine_ver2.model.entity.cart.Cart;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long id);
    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.items WHERE c.user.id=:id")
    Optional<Cart> findByUserIdWithItems(Long id);
}
