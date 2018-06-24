package com.maximmalikov.onlineshop.dto;

import com.maximmalikov.onlineshop.domain.enums.OrderStatus;
import com.maximmalikov.onlineshop.domain.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO implements Serializable {

    private static final long serialVersionUID = -1918998667052710286L;

    private long orderNumber;
    private Date orderDate;
    private LocalTime orderTime;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private List<Long> goods;
    private List<Long> users;
    private long delivery;

}
