package com.maximmalikov.onlineshop.repository;

import com.maximmalikov.onlineshop.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    Delivery getByOrderNumber(long orderNumber);

    Delivery getByDeliveryDate(Date deliveryDate);

}
