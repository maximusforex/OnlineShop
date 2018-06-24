package com.maximmalikov.onlineshop.repository;

import com.maximmalikov.onlineshop.domain.Users;
import com.maximmalikov.onlineshop.domain.Goods;
import com.maximmalikov.onlineshop.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    Orders getByOrderNumber(long orderNumber);

    Orders getByOrderDate(Date orderDate);

    Orders getByGoodsProductId(Goods productId);

    Orders getByGoodsProductName(Goods productName);

    Orders getByUserId(Users userId);

}
