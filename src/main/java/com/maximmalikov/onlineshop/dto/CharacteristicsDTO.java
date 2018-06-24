package com.maximmalikov.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CharacteristicsDTO implements Serializable {

    private static final long serialVersionUID = 1392386035124357075L;

    private long characteristicId;
    private String characteristicName;
    private String description;
    private long product;

}
