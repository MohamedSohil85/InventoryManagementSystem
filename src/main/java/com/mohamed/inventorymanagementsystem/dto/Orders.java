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
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd.MM.YYYY")
    private Date OrderDate;
    @ManyToOne
    private User user;
    @OneToMany
    List<OrderDetail> orderDetailList;
    private double sum;
}
