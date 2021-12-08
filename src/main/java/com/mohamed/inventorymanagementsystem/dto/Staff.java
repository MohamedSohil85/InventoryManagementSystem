package com.mohamed.inventorymanagementsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotEmpty(message = "please enter a firstName")
    private String firstName;
    @NotEmpty(message = "please enter a lastName")
    private String lastName;
    @NotEmpty(message = "please enter a address")
    private String address;
    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Mobile number is invalid")
    private String phone;
    @Email
    @NotEmpty(message = "please enter a E-mail")
    private String email;
    private String token;
    @OneToMany
    private List<Sales> salesList;
}
