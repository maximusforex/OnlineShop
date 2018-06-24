package com.maximmalikov.onlineshop.service;

import com.maximmalikov.onlineshop.domain.Users;
import com.maximmalikov.onlineshop.domain.Goods;
import com.maximmalikov.onlineshop.domain.Orders;
import com.maximmalikov.onlineshop.dto.OrdersDTO;
import com.maximmalikov.onlineshop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrdersService {

    private final OrdersRepository ordersRepository;

    private final GoodsRepository goodsRepository;

    private final UsersRepository usersRepository;

    private final DeliveryRepository deliveryRepository;

    private OrdersDTO fromOrders(Orders order) {
        if (order != null) {
            return OrdersDTO.builder()
                    .orderNumber(order.getOrderNumber())
                    .orderDate(order.getOrderDate())
                    .orderTime(order.getOrderTime())
                    .orderStatus(order.getOrderStatus())
                    .paymentMethod(order.getPaymentMethod())
                    .goods(order.getGoods() != null
                            ? order.getGoods().stream()
                            .map(Goods::getProductId)
                            .collect(Collectors.toList())
                            : null)
                    .users(order.getUsers() != null
                            ? order.getUsers().stream()
                            .map(Users::getUserId)
                            .collect(Collectors.toList())
                            : null)
                    .delivery(order.getDelivery().getOrderNumber())
                    .build();
        }
        return null;
    }

    private Orders fromDTO(OrdersDTO ordersDTO) {
        if (ordersDTO != null) {
            return Orders.builder()
                    .orderNumber(ordersDTO.getOrderNumber())
                    .orderDate(ordersDTO.getOrderDate())
                    .orderTime(ordersDTO.getOrderTime())
                    .orderStatus(ordersDTO.getOrderStatus())
                    .paymentMethod(ordersDTO.getPaymentMethod())
                    .goods(ordersDTO.getGoods() != null
                            ? goodsRepository.findAllById(ordersDTO.getGoods())
                            : null)
                    .users(ordersDTO.getUsers() != null
                            ? usersRepository.findAllById(ordersDTO.getUsers())
                            : null)
                    .delivery(ordersDTO.getDelivery() > 0L
                            ? deliveryRepository.getOne(ordersDTO.getDelivery())
                            : null)
                    .build();
        }
        return null;
    }

    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.findAll().stream()
                .map(this::fromOrders)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrdersDTO addOrder(OrdersDTO ordersDTO) {
        if (!ordersRepository.existsById(ordersDTO.getOrderNumber())) {
            return fromOrders(ordersRepository.saveAndFlush(fromDTO(ordersDTO)));
        }
        return null;
    }

    @Transactional
    public void deleteByOrderNumber(long orderNumber) {
        if (ordersRepository.existsById(orderNumber)) {
            ordersRepository.deleteById(orderNumber);
        }
    }

    @Transactional
    public OrdersDTO updateOrder(OrdersDTO ordersDTO) {
        if (ordersRepository.existsById(ordersDTO.getOrderNumber())) {
            Orders ordersTemp = ordersRepository.getOne(ordersDTO.getOrderNumber());
            ordersTemp.setOrderDate(ordersDTO.getOrderDate());
            ordersTemp.setOrderTime(ordersDTO.getOrderTime());
            ordersTemp.setOrderStatus(ordersDTO.getOrderStatus());
            ordersTemp.setPaymentMethod(ordersDTO.getPaymentMethod());
            return fromOrders(ordersRepository.saveAndFlush(ordersTemp));
        }
        return null;
    }

    public OrdersDTO getByOrderNumber(long orderNumber) {
        if (ordersRepository.existsById(orderNumber)) {
            return fromOrders(ordersRepository.getByOrderNumber(orderNumber));
        }
        return null;
    }

    public OrdersDTO getByOrderDate(Date orderDate) {
        return fromOrders(ordersRepository.getByOrderDate(orderDate));
    }

    public OrdersDTO getByProductId(Goods product) {
        return fromOrders(ordersRepository.getByGoodsProductId(goodsRepository.getByProductId(product.getProductId())));
    }

    public OrdersDTO getByProductName(Goods product) {
        return fromOrders(ordersRepository.getByGoodsProductName(goodsRepository.getByProductName(product.getProductName())));
    }

    public OrdersDTO getByUserId(Users user) {
        return fromOrders(ordersRepository.getByUserId(usersRepository.getByLogin(user.getLogin())));
    }

}
