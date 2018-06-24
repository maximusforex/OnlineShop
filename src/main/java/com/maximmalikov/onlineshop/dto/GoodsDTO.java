package com.maximmalikov.onlineshop.dto;

import com.maximmalikov.onlineshop.domain.enums.Availability;
import com.maximmalikov.onlineshop.domain.enums.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDTO implements Serializable {

    private static final long serialVersionUID = 8955300838255278763L;

    private long productId;
    private String productName;
    private Categories categories;
    private double price;
    private Availability availability;
    private List<Long> characteristics;
    private List<Long> orders;
    private long user;
}
