package com.mohamed.inventorymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty(message = "Please enter a Name")
    private String productName;
    @NotEmpty(message = "Please enter a Description")
    private String productDesc;
    @NotEmpty(message = "Please enter a Product Unit")
    private String productUnit;
    @NotNull(message = "Please enter a Quantity")
    @DecimalMin("1")
    private int quantity;
    @NotNull(message = "Please enter a price")
    @DecimalMin("1.00")
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
