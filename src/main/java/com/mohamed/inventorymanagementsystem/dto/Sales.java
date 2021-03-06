package com.mohamed.inventorymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Sales implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long salesId;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd.MM.YYYY")
    private Date SalesDate;
    private String invoice_No;
    private int total_product;
    private int discount;
    private int vat_charge_rate;
    private float vat_charge;
    private float gross_amount;
    private float net_amount;
    @ManyToOne
    private Staff staff;
    @ManyToOne
    private Customer customer;
    @OneToMany
    List<SalesItem> SalesItemList;
    private double total;
}
