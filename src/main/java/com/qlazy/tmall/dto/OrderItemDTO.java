package com.qlazy.tmall.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Integer id;
    private Integer pid;
    private Integer oid;
    private Integer uid;
    private Integer number;
    
    private ProductDTO productDTO;
    private OrderDTO orderDTO;
    private UserDTO userDTO;
}
