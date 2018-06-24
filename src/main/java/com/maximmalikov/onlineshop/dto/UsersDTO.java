package com.maximmalikov.onlineshop.dto;

import com.maximmalikov.onlineshop.domain.enums.Gender;
import com.maximmalikov.onlineshop.domain.enums.Role;
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
public class UsersDTO implements Serializable {

    private static final long serialVersionUID = -8256752526545849501L;

    private long userId;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String address;
    private Gender gender;
    private String phoneNumber;
    private Role role;
    private List<Long> orders;
    private List<Long> creditCards;
    private List<Long> pointsOfIssue;
    private List<Long> goods;

}
