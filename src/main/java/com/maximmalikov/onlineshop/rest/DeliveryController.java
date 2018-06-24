package com.maximmalikov.onlineshop.rest;

import com.maximmalikov.onlineshop.dto.DeliveryDTO;
import com.maximmalikov.onlineshop.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryDTO> getAll() {
        return deliveryService.getAllDelivery();
    }

    @PostMapping
    public ResponseEntity<DeliveryDTO> addDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        DeliveryDTO deliveryDTO1 = deliveryService.addDelivery(deliveryDTO);
        return ResponseEntity.ok(deliveryDTO1);
    }

    @DeleteMapping("/{order_number}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable(value = "order_number") long orderNumber) {
        try {
            deliveryService.deleteDelivery(orderNumber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<DeliveryDTO> updateDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        DeliveryDTO deliveryDTO1 = deliveryService.updateDelivery(deliveryDTO);
        return ResponseEntity.ok(deliveryDTO1);
    }

    @GetMapping("/{order_number}")
    public ResponseEntity<DeliveryDTO> getDeliveryByOrderNumber(@PathVariable(value = "order_number") long orderNumber) {
        return ResponseEntity.ok(deliveryService.getByOrderNumber(orderNumber));
    }

    @GetMapping("/{delivery_date}")
    public ResponseEntity<DeliveryDTO> getDeliveryByDate(@PathVariable(value = "delivery_date") Date deliveryDate) {
        return ResponseEntity.ok(deliveryService.getByDeliveryDate(deliveryDate));
    }

}
