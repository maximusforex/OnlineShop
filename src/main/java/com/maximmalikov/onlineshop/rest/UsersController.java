package com.maximmalikov.onlineshop.rest;

import com.maximmalikov.onlineshop.dto.UsersDTO;
import com.maximmalikov.onlineshop.repository.CreditCardsRepository;
import com.maximmalikov.onlineshop.repository.OrdersRepository;
import com.maximmalikov.onlineshop.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {

    private final UsersService usersService;

    private final OrdersRepository ordersRepository;

    private final CreditCardsRepository creditCardsRepository;

    @GetMapping
    public List<UsersDTO> getAll() {
        return usersService.getAllCustomers();
    }

    @PostMapping
    public ResponseEntity<UsersDTO> addCustomer(@RequestBody UsersDTO usersDTO) {
        UsersDTO usersDTO1 = usersService.addUser(usersDTO);
        return ResponseEntity.ok(usersDTO1);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "user_id") long userId) {
        try {
            usersService.deleteByUserId(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<UsersDTO> updateCustomer(@RequestBody UsersDTO usersDTO) {
        UsersDTO usersDTO1 = usersService.updateUser(usersDTO);
        return ResponseEntity.ok(usersDTO1);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UsersDTO> getCustomerByLogin(@PathVariable(value = "user_id") long userId) {
        return ResponseEntity.ok(usersService.getByUserId(userId));
    }


    @GetMapping("/{phone_number}")
    public ResponseEntity<UsersDTO> getCustomerByPhoneNumber(@PathVariable(value = "phone_number") String phoneNumber) {
        return ResponseEntity.ok(usersService.GetByPhoneNumber(phoneNumber));
    }

    @GetMapping("/orders/{order_number}")
    public ResponseEntity<UsersDTO> getCustomerByOrderNumber(@PathVariable(value = "order_number") long orderNumber) {
        return ResponseEntity.ok(usersService.getByOrderNumber(ordersRepository.getByOrderNumber(orderNumber)));
    }

    @GetMapping("/credit_cards/{credit_number}")
    public ResponseEntity<UsersDTO> getCustomerByCreditCard(@PathVariable(value = "credit_number") long creditNumber) {
        return ResponseEntity.ok(usersService.getByCreditCard(creditCardsRepository.getByCardNumber(creditNumber)));
    }
}
