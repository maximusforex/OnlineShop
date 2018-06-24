package com.maximmalikov.onlineshop.domain;

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
@Table(name = "points_of_issue")
public class PointsOfIssue implements Serializable {

    private static final long serialVersionUID = -4867857422600089211L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "points_id")
    private long pointsId;

    @Column(name = "address")
    private String address;

    @Column(name = "work_time")
    private String workTime;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @Singular
    private List<Delivery> deliveries;
}
