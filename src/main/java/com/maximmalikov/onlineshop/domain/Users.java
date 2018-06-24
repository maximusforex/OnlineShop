package com.maximmalikov.onlineshop.domain;

import com.maximmalikov.onlineshop.domain.enums.Gender;
import com.maximmalikov.onlineshop.domain.enums.Role;
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
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 8135107027416749714L;

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "role")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL)
    @Singular
    private List<Goods> goods;

    @ManyToMany(mappedBy = "users")
    @Singular
    private List<Orders> orders;

    @OneToMany(cascade = CascadeType.ALL)
    @Singular
    private List<CreditCards> creditCards;

    @OneToMany(cascade = CascadeType.ALL)
    @Singular
    private List<PointsOfIssue> pointsOfIssues;
}
