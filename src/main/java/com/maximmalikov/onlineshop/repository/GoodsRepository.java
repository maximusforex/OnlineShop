package com.maximmalikov.onlineshop.repository;

import com.maximmalikov.onlineshop.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface GoodsRepository extends JpaRepository<Goods,Long> {

    Goods getByProductId(long productId);

    Goods getByProductName(String productName);

}
