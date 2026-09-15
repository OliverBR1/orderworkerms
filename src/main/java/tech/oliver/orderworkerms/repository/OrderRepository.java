package tech.oliver.orderworkerms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.oliver.orderworkerms.entity.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);
}
