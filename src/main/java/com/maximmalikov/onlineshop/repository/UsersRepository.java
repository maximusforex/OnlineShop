package com.maximmalikov.onlineshop.repository;

import com.maximmalikov.onlineshop.domain.CreditCards;
import com.maximmalikov.onlineshop.domain.Users;
import com.maximmalikov.onlineshop.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users getByUserId(long userId);

    Users getByLogin(String login);

    Users getByPhoneNumber(String phoneNumber);

    Users getByOrdersOrderNumber(Orders orderNumber);

    Users getByCreditCardsCardNumber(CreditCards creditCard);
}
