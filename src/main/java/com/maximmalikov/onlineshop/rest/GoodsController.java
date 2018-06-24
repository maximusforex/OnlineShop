package com.maximmalikov.onlineshop.rest;

import com.maximmalikov.onlineshop.dto.GoodsDTO;
import com.maximmalikov.onlineshop.repository.OrdersRepository;
import com.maximmalikov.onlineshop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsController {

    private final GoodsService goodsService;
    private final OrdersRepository ordersRepository;

    @GetMapping
    public List<GoodsDTO> getAll() {
        return goodsService.getAllGoods();
    }

    @PostMapping
    public ResponseEntity<GoodsDTO> addProduct(@RequestBody GoodsDTO goodsDTO) {
        GoodsDTO goodsDTO1 = goodsService.addProduct(goodsDTO);
        return ResponseEntity.ok(goodsDTO1);
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "product_id") long productId) {
        try {
            goodsService.deleteByProductId(productId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<GoodsDTO> updateGoods(@RequestBody GoodsDTO goodsDTO) {
        GoodsDTO goodsDTO1 = goodsService.updateGoods(goodsDTO);
        return ResponseEntity.ok(goodsDTO1);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<GoodsDTO> getProductById(@PathVariable(value = "product_id") long productId) {
        return ResponseEntity.ok(goodsService.getByProductId(productId));
    }

    @GetMapping("/{product_name}")
    public ResponseEntity<GoodsDTO> getProductByName(@PathVariable(value = "product_name") String productName) {
        return ResponseEntity.ok(goodsService.getByProductName(productName));
    }


}
