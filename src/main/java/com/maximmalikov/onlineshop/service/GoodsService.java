package com.maximmalikov.onlineshop.service;

import com.maximmalikov.onlineshop.domain.Characteristics;
import com.maximmalikov.onlineshop.domain.Goods;
import com.maximmalikov.onlineshop.domain.Orders;
import com.maximmalikov.onlineshop.dto.GoodsDTO;
import com.maximmalikov.onlineshop.repository.CharacteristicsRepository;
import com.maximmalikov.onlineshop.repository.GoodsRepository;
import com.maximmalikov.onlineshop.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsService {

    private final GoodsRepository goodsRepository;

    private final CharacteristicsRepository characteristicsRepository;

    private final OrdersRepository ordersRepository;

    private GoodsDTO fromGoods(Goods product) {
        if (product != null) {
            return GoodsDTO.builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .categories(product.getCategories())
                    .price(product.getPrice())
                    .availability(product.getAvailability())
                    .characteristics(product.getCharacteristics() != null
                            ? product.getCharacteristics().stream()
                            .map(Characteristics::getCharacteristicId)
                            .collect(Collectors.toList())
                            : null)
                    .orders(product.getOrders() != null
                            ? product.getOrders().stream()
                            .map(Orders::getOrderNumber)
                            .collect(Collectors.toList())
                            : null)
                    .build();
        }
        return null;
    }

    private Goods fromDTO(GoodsDTO goodsDTO) {
        if (goodsDTO != null) {
            return Goods.builder()
                    .productId(goodsDTO.getProductId())
                    .productName(goodsDTO.getProductName())
                    .categories(goodsDTO.getCategories())
                    .price(goodsDTO.getPrice())
                    .availability(goodsDTO.getAvailability())
                    .characteristics(goodsDTO.getCharacteristics() != null
                            ? characteristicsRepository.findAllById(goodsDTO.getCharacteristics())
                            : null)
                    .orders(goodsDTO.getOrders() != null
                            ? ordersRepository.findAllById(goodsDTO.getOrders())
                            : null)
                    .build();
        }
        return null;
    }

    public List<GoodsDTO> getAllGoods() {
        return goodsRepository.findAll().stream()
                .map(this::fromGoods)
                .collect(Collectors.toList());
    }

    @Transactional
    public GoodsDTO addProduct(GoodsDTO goodsDTO) {
        if (!goodsRepository.existsById(goodsDTO.getProductId())) {
            return fromGoods(goodsRepository.saveAndFlush(fromDTO(goodsDTO)));
        }
        return null;
    }

    @Transactional
    public void deleteByProductId(long productId) {
        if (goodsRepository.existsById(productId)) {
            goodsRepository.deleteById(productId);
        }
    }

    @Transactional
    public GoodsDTO updateGoods(GoodsDTO goodsDTO) {
        if (goodsRepository.existsById(goodsDTO.getProductId())) {
            Goods goodsTerm = goodsRepository.getOne(goodsDTO.getProductId());
            goodsTerm.setProductName(goodsDTO.getProductName());
            goodsTerm.setCategories(goodsDTO.getCategories());
            goodsTerm.setPrice(goodsDTO.getPrice());
            goodsTerm.setAvailability(goodsDTO.getAvailability());
            return fromGoods(goodsRepository.saveAndFlush(goodsTerm));
        }
        return null;
    }

    public GoodsDTO getByProductId(long productId) {
        if (goodsRepository.existsById(productId)) {
            return fromGoods(goodsRepository.getByProductId(productId));
        }
        return null;
    }

    public GoodsDTO getByProductName(String productName) {
        return fromGoods(goodsRepository.getByProductName(productName));
    }


}
