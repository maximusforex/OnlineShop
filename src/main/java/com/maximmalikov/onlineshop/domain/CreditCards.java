package com.maximmalikov.onlineshop.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder(toBuilder = true)
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_cards")
public class CreditCards implements Serializable {

    private static final long serialVersionUID = -1432740147554190787L;

    @Id
    @Column(name = "card_number")
    private long cardNumber;

    @Column(name = "verification_code")
    private int verificationCode;

    @Column(name = "expire_date")
    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

}
