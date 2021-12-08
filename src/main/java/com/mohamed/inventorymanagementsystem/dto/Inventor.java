package com.mohamed.inventorymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Inventor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventorId;
    private String firstName;
    private String lastName;
    @ManyToOne
    private Company company;
}
