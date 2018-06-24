package com.maximmalikov.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardsDTO implements Serializable {

    private static final long serialVersionUID = 2637692125862463250L;

    private long cardNumber;
    private int verificationCode;
    private Date expireDate;
    private long user;
}
