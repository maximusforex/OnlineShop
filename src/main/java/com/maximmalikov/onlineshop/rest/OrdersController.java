package com.maximmalikov.onlineshop.rest;

import com.maximmalikov.onlineshop.dto.OrdersDTO;
import com.maximmalikov.onlineshop.repository.UsersRepository;
import com.maximmalikov.onlineshop.repository.GoodsRepository;
import com.maximmalikov.onlineshop.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrdersController {

    private final OrdersService ordersService;
    private final GoodsRepository goodsRepository;
    private final UsersRepository usersRepository;

    @GetMapping
    public List<OrdersDTO> getAll() {
        return ordersService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<OrdersDTO> addOrder(@RequestBody OrdersDTO ordersDTO) {
        OrdersDTO ordersDTO1 = ordersService.addOrder(ordersDTO);
        return ResponseEntity.ok(ordersDTO1);
    }

    @DeleteMapping("/{order_number}")
    public ResponseEntity<Void> deleteOrder(@PathVariable(value = "order_number") long orderNumber) {
        try {
            ordersService.deleteByOrderNumber(orderNumber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<OrdersDTO> updateOrder(@RequestBody OrdersDTO ordersDTO) {
        OrdersDTO ordersDTO1 = ordersService.updateOrder(ordersDTO);
        return ResponseEntity.ok(ordersDTO1);
    }

    @GetMapping("/{order_number}")
    public ResponseEntity<OrdersDTO> getOrderByNumber(@PathVariable(value = "order_number") long orderNumber) {
        return ResponseEntity.ok(ordersService.getByOrderNumber(orderNumber));
    }

    @GetMapping("/{order_date}")
    public ResponseEntity<OrdersDTO> getOrderByDate(@PathVariable(value = "order_date") Date orderDate) {
        return ResponseEntity.ok(ordersService.getByOrderDate(orderDate));
    }

    @GetMapping("/goods/{product_id}")
    public ResponseEntity<OrdersDTO> getOrderByProductId(@PathVariable(value = "product_id") long productId) {
        return ResponseEntity.ok(ordersService.getByProductId(goodsRepository.getByProductId(productId)));
    }

    @GetMapping("/goods/{product_name}")
    public ResponseEntity<OrdersDTO> getOrderByProductName(@PathVariable(value = "product_name") String productName) {
        return ResponseEntity.ok(ordersService.getByProductName(goodsRepository.getByProductName(productName)));
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<OrdersDTO> getOrderByCustomerLogin(@PathVariable(value = "user_id") long userId) {
        return ResponseEntity.ok(ordersService.getByUserId(usersRepository.getByUserId(userId)));
    }
}
