package com.maximmalikov.onlineshop.rest;

import com.maximmalikov.onlineshop.dto.CreditCardsDTO;
import com.maximmalikov.onlineshop.service.CreditCardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit_cards")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardsController {

    private final CreditCardsService creditCardsService;

    @GetMapping
    public List<CreditCardsDTO> getAll() {
        return creditCardsService.getAllCreditCards();
    }

    @PostMapping
    public ResponseEntity<CreditCardsDTO> addCreditCard(@RequestBody CreditCardsDTO creditCardsDTO) {
        CreditCardsDTO creditCardsDTO1 = creditCardsService.addCreditCard(creditCardsDTO);
        return ResponseEntity.ok(creditCardsDTO1);
    }

    @DeleteMapping("/{card_number}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable(value = "card_number") long cardNumber) {
        try {
            creditCardsService.deleteCreditCard(cardNumber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<CreditCardsDTO> updateCreditCard(@RequestBody CreditCardsDTO creditCardsDTO) {
        CreditCardsDTO creditCardsDTO1 = creditCardsService.updateCreditCard(creditCardsDTO);
        return ResponseEntity.ok(creditCardsDTO1);
    }

    @GetMapping("/{card_number}")
    public ResponseEntity<CreditCardsDTO> getCardByNumber(@PathVariable(value = "card_number") long cardNumber) {
        return ResponseEntity.ok(creditCardsService.getByCardNumber(cardNumber));
    }

}
