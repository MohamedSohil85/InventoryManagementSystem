package com.mohamed.inventorymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalesItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long SalesItemId;
    @DecimalMin("1")
    private int Quantity;
    private float total;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;
    private int rate;
    @OneToOne
    private Product product;
    @ManyToOne
    private Sales sales;
}
