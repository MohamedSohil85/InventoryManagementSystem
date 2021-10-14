package com.mohamed.inventorymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String productDesc;
    private String productUnit;
    private int quantity;
    private float price;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @JsonFormat(pattern = "dd.MM.YYYY")
    private Date dateOfExpiry;
    @JsonFormat(pattern = "dd.MM.YYYY")
    private Date addedOn;
    private String productURL;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Supplier supplier;
}
