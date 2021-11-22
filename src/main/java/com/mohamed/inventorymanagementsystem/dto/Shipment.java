package com.mohamed.inventorymanagementsystem.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Shipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shipmentId;
    @NotEmpty(message = "please enter a firstName")
    private String firstName;
    @NotEmpty(message = "please enter a lastName")
    private String lastName;
    @NotEmpty(message = "please enter a address")
    private String address;
    @Email
    @NotEmpty(message = "please enter a E-mail")
    private String email;
    @OneToOne
    private Orders orders;

}
