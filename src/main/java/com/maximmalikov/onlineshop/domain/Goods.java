package com.maximmalikov.onlineshop.domain;

import com.maximmalikov.onlineshop.domain.enums.Availability;
import com.maximmalikov.onlineshop.domain.enums.Categories;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder(toBuilder = true)
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = -63033342808989195L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "categories")
    private Categories categories;

    @Column(name = "price")
    private double price;

    @Column(name = "availability")
    private Availability availability;

    @OneToMany(cascade = CascadeType.ALL)
    @Singular
    private List<Characteristics> characteristics;

    @ManyToMany(mappedBy = "goods", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Singular
    private List<Orders> orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

}
