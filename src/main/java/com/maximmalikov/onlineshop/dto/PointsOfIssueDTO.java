package com.maximmalikov.onlineshop.dto;

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
public class PointsOfIssueDTO implements Serializable {

    private static final long serialVersionUID = -1469606932419438814L;

    private long pointsId;
    private String address;
    private String workTime;
    private String phoneNumber;
    private List<Long> deliveries;
}
