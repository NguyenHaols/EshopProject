package com.Eshopping.DTO;

import com.Eshopping.model.DetailOrder;
import com.Eshopping.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Date createDate;
    private Double totalPrice;
    private UserDTO user;
    private List<DetailOrderDTO> detailOrderList;
}
