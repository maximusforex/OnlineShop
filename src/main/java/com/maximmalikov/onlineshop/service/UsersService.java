package com.maximmalikov.onlineshop.service;

import com.maximmalikov.onlineshop.domain.*;
import com.maximmalikov.onlineshop.dto.UsersDTO;
import com.maximmalikov.onlineshop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersService {

    private final UsersRepository usersRepository;

    private final OrdersRepository ordersRepository;

    private final CreditCardsRepository creditCardsRepository;

    private final PointsOfIssueRepository pointsOfIssueRepository;

    private final GoodsRepository goodsRepository;

    private UsersDTO fromUsers(Users user) {
        if (user != null) {
            return UsersDTO.builder()
                    .userId(user.getUserId())
                    .login(user.getLogin())
                    .password(user.getPassword())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .gender(user.getGender())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole())
                    .orders(user.getOrders() != null
                            ? user.getOrders().stream()
                            .map(Orders::getOrderNumber)
                            .collect(Collectors.toList())
                            : null)
                    .creditCards(user.getCreditCards() != null
                            ? user.getCreditCards().stream()
                            .map(CreditCards::getCardNumber)
                            .collect(Collectors.toList())
                            : null)
                    .pointsOfIssue(user.getPointsOfIssues() != null
                            ? user.getPointsOfIssues().stream()
                            .map(PointsOfIssue::getPointsId)
                            .collect(Collectors.toList())
                            : null)
                    .goods(user.getGoods() != null
                            ? user.getGoods().stream()
                            .map(Goods::getProductId)
                            .collect(Collectors.toList())
                            : null)
                    .build();
        }
        return null;
    }

    private Users fromDTO(UsersDTO usersDTO) {
        if (usersDTO != null) {
            return Users.builder()
                    .login(usersDTO.getLogin())
                    .name(usersDTO.getName())
                    .surname(usersDTO.getSurname())
                    .email(usersDTO.getEmail())
                    .address(usersDTO.getAddress())
                    .gender(usersDTO.getGender())
                    .phoneNumber(usersDTO.getPhoneNumber())
                    .role(usersDTO.getRole())
                    .orders(usersDTO.getOrders() != null
                            ? ordersRepository.findAllById(usersDTO.getOrders())
                            : null)
                    .creditCards(usersDTO.getCreditCards() != null
                            ? creditCardsRepository.findAllById(usersDTO.getCreditCards())
                            : null)
                    .pointsOfIssues(usersDTO.getPointsOfIssue() != null
                            ? pointsOfIssueRepository.findAllById(usersDTO.getPointsOfIssue())
                            : null)
                    .goods(usersDTO.getGoods() != null
                            ? goodsRepository.findAllById(usersDTO.getGoods())
                            : null)
                    .build();
        }
        return null;
    }

    public List<UsersDTO> getAllCustomers() {
        return usersRepository.findAll().stream()
                .map(this::fromUsers)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsersDTO addUser(UsersDTO usersDTO) {
        if (!usersRepository.existsById(usersDTO.getUserId())) {
            return fromUsers(usersRepository.saveAndFlush(fromDTO(usersDTO)));
        }
        return null;
    }

    @Transactional
    public void deleteByUserId(long userId) {
        if (usersRepository.existsById(userId)) {
            usersRepository.deleteById(userId);
        }
    }

    @Transactional
    public UsersDTO updateUser(UsersDTO usersDTO) {
        if (usersRepository.existsById(usersDTO.getUserId())) {
            Users usersTerm = usersRepository.getOne(usersDTO.getUserId());
            usersTerm.setName(usersDTO.getName());
            usersTerm.setSurname(usersDTO.getSurname());
            usersTerm.setEmail(usersDTO.getEmail());
            usersTerm.setAddress(usersDTO.getAddress());
            usersTerm.setGender(usersDTO.getGender());
            usersTerm.setPhoneNumber(usersDTO.getPhoneNumber());
            usersTerm.setRole(usersDTO.getRole());
            return fromUsers(usersRepository.saveAndFlush(usersTerm));
        }
        return null;
    }

    public UsersDTO getByUserId(long userId) {
            return fromUsers(usersRepository.getByUserId(userId));
    }

    public UsersDTO GetByPhoneNumber(String phoneNumber) {
        return fromUsers(usersRepository.getByPhoneNumber(phoneNumber));
    }

    public UsersDTO getByOrderNumber(Orders order) {
        return fromUsers(usersRepository.getByOrdersOrderNumber(ordersRepository.getByOrderNumber(order.getOrderNumber())));
    }

    public UsersDTO getByCreditCard(CreditCards creditCard) {
        return fromUsers(usersRepository.getByCreditCardsCardNumber(creditCardsRepository.getByCardNumber(creditCard.getCardNumber())));
    }
}
