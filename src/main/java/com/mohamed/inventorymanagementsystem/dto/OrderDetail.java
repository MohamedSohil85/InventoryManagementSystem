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
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderDetailsId;
    private float unit_price;
    private int Quantity;
    private float total;
    private int size;
    private int discount;
    @JsonFormat(pattern = "dd.MM.YYYY")
    private Date date;
    @OneToOne
    private Product product;
    @ManyToOne
    private Orders orders;
}
