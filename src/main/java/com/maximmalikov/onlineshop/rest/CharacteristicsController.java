package com.maximmalikov.onlineshop.rest;

import com.maximmalikov.onlineshop.dto.CharacteristicsDTO;
import com.maximmalikov.onlineshop.service.CharacteristicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characteristics")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CharacteristicsController {

    private final CharacteristicsService characteristicsService;

    @GetMapping
    public List<CharacteristicsDTO> getAll() {
        return characteristicsService.getAllCharacteristics();
    }

    @PostMapping
    public ResponseEntity<CharacteristicsDTO> addCharacteristic(@RequestBody CharacteristicsDTO characteristicsDTO) {
        CharacteristicsDTO characteristicsDTO1 = characteristicsService.addCharacteristic(characteristicsDTO);
        return ResponseEntity.ok(characteristicsDTO1);
    }

    @DeleteMapping("/{characteristic_id}")
    public ResponseEntity<Void> deleteCharacteristic(@PathVariable(value = "characteristic_id") long characteristicId) {
        try {
            characteristicsService.deleteByCharacteristicId(characteristicId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<CharacteristicsDTO> updateCharacteristic(@RequestBody CharacteristicsDTO characteristicsDTO) {
        CharacteristicsDTO characteristicsDTO1 = characteristicsService.updateCharacteristics(characteristicsDTO);
        return ResponseEntity.ok(characteristicsDTO1);
    }

    @GetMapping("/{characteristic_id}")
    public ResponseEntity<CharacteristicsDTO> getCharacteristicById(@PathVariable(value = "characteristic_id") long characteristicId) {
        return ResponseEntity.ok(characteristicsService.getByCharacteristicId(characteristicId));
    }

    @GetMapping("/goods/{product_id}")
    public ResponseEntity<CharacteristicsDTO> getCharacteristicByProductId(@PathVariable(value = "product_id") long productId) {
        return ResponseEntity.ok(characteristicsService.getByProductId(productId));
    }

}
