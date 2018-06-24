package com.maximmalikov.onlineshop.dto;

import com.maximmalikov.onlineshop.domain.enums.DeliveryStatus;
import com.maximmalikov.onlineshop.domain.enums.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO implements Serializable {

    private static final long serialVersionUID = -7152081768790589398L;

    private long orderNumber;
    private double totalWeight;
    private double totalVolume;
    private Date deliveryDate;
    private LocalTime deliveryTime;
    private double price;
    private DeliveryStatus deliveryStatus;
    private DeliveryType deliveryType;
    private long orders;
    private long pointOfIssue;

}
