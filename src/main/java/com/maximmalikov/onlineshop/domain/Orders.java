package com.maximmalikov.onlineshop.domain;

import com.maximmalikov.onlineshop.domain.enums.OrderStatus;
import com.maximmalikov.onlineshop.domain.enums.PaymentMethod;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Builder(toBuilder = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = -930798143078000085L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_number")
    private long orderNumber;

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "order_time")
    private LocalTime orderTime;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "goods_orders",
            joinColumns = {@JoinColumn(name = "fk_orders", referencedColumnName = "order_number")},
            inverseJoinColumns = {@JoinColumn(name = "fk_goods", referencedColumnName = "product_id")}

    )
    @Singular
    private List<Goods> goods;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_orders",
            joinColumns = {@JoinColumn(name = "fk_orders", referencedColumnName = "order_number")},
            inverseJoinColumns = {@JoinColumn(name = "fk_users", referencedColumnName = "user_id")}
    )
    @Singular
    private List<Users> users;

    @OneToOne
    @JoinColumn(name = "order_number")
    private Delivery delivery;

}
