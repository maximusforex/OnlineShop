package com.maximmalikov.onlineshop.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder(toBuilder = true)
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "characteristics")
public class Characteristics implements Serializable {

    private static final long serialVersionUID = 3039490220618939276L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "characteristic_id")
    private long characteristicId;

    @Column(name = "characteristic_name")
    private String characteristicName;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Goods product;

}
