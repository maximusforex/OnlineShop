package com.maximmalikov.onlineshop.repository;

import com.maximmalikov.onlineshop.domain.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicsRepository extends JpaRepository<Characteristics,Long>  {

    Characteristics getByCharacteristicId(long characteristicsId);

    Characteristics getByProductProductId(long productId);

}
