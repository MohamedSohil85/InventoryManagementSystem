package com.mohamed.inventorymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseId;
    private Date purchase_date;
    @ManyToOne
    private Supplier supplier;
    private Company company;
    private int quantity;
    private float purchase_discount;
    private float purchase_rate;
    private List<Product>products;

}
