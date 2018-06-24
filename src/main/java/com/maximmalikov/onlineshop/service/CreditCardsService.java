package com.maximmalikov.onlineshop.service;

import com.maximmalikov.onlineshop.domain.CreditCards;
import com.maximmalikov.onlineshop.dto.CreditCardsDTO;
import com.maximmalikov.onlineshop.repository.CreditCardsRepository;
import com.maximmalikov.onlineshop.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardsService {

    private final CreditCardsRepository creditCardsRepository;

    private final UsersRepository usersRepository;

    private CreditCardsDTO fromCreditCards(CreditCards creditCards) {
        if (creditCards != null) {
            return CreditCardsDTO.builder()
                    .cardNumber(creditCards.getCardNumber())
                    .verificationCode(creditCards.getVerificationCode())
                    .expireDate(creditCards.getExpireDate())
                    .user(creditCards.getUser().getUserId())
                    .build();
        }
        return null;
    }

    private CreditCards fromDTO(CreditCardsDTO creditCardsDTO) {
        if (creditCardsDTO != null) {
            return CreditCards.builder()
                    .cardNumber(creditCardsDTO.getCardNumber())
                    .verificationCode(creditCardsDTO.getVerificationCode())
                    .expireDate(creditCardsDTO.getExpireDate())
                    .user(creditCardsDTO.getUser() > 0L
                            ? usersRepository.getByUserId(creditCardsDTO.getUser())
                            : null)
                    .build();
        }
        return null;
    }

    public List<CreditCardsDTO> getAllCreditCards() {
        return creditCardsRepository.findAll().stream()
                .map(this::fromCreditCards)
                .collect(Collectors.toList());
    }

    @Transactional
    public CreditCardsDTO addCreditCard(CreditCardsDTO creditCardsDTO) {
        if (!creditCardsRepository.existsById(creditCardsDTO.getCardNumber())) {
            return fromCreditCards(creditCardsRepository.saveAndFlush(fromDTO(creditCardsDTO)));
        }
        return null;
    }

    @Transactional
    public void deleteCreditCard(long cardNumber) {
        if (creditCardsRepository.existsById(cardNumber)) {
            creditCardsRepository.deleteById(cardNumber);
        }
    }

    @Transactional
    public CreditCardsDTO updateCreditCard(CreditCardsDTO creditCardsDTO) {
        if (creditCardsRepository.existsById(creditCardsDTO.getCardNumber())) {
            CreditCards creditCardsTemp = creditCardsRepository.getOne(creditCardsDTO.getCardNumber());
            creditCardsTemp.setVerificationCode(creditCardsDTO.getVerificationCode());
            creditCardsTemp.setExpireDate(creditCardsDTO.getExpireDate());
            return fromCreditCards(creditCardsRepository.saveAndFlush(fromDTO(creditCardsDTO)));
        }
        return null;
    }

    public CreditCardsDTO getByCardNumber(long cardNumber) {
        if(creditCardsRepository.existsById(cardNumber)) {
            return fromCreditCards(creditCardsRepository.getByCardNumber(cardNumber));
        }
        return null;
    }

}
