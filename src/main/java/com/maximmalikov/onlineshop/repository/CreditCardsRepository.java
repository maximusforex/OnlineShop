package com.maximmalikov.onlineshop.repository;

import com.maximmalikov.onlineshop.domain.CreditCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardsRepository extends JpaRepository<CreditCards, Long> {

    CreditCards getByCardNumber(long cardNumber);

}
