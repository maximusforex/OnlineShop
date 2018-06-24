package com.maximmalikov.onlineshop.domain;

import com.maximmalikov.onlineshop.domain.enums.DeliveryStatus;
import com.maximmalikov.onlineshop.domain.enums.DeliveryType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Builder(toBuilder = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {

    private static final long serialVersionUID = -6562991439804155681L;

    @Id
    @JoinColumn(name = "order_number", referencedColumnName = "order_number")
    private long orderNumber;

    @Column(name = "total_weight")
    private double totalWeight;

    @Column(name = "total_volume")
    private double totalVolume;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Column(name = "delivery_time")
    private LocalTime deliveryTime;

    @Column(name = "price")
    private double price;

    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;

    @Column(name = "delivery_type")
    private DeliveryType deliveryType;

    @OneToOne(mappedBy = "delivery")
    private Orders order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "points_id")
    private PointsOfIssue pointOfIssue;

}
