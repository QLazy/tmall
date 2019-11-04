package com.qlazy.tmall.dto;

import com.qlazy.tmall.entity.category;

import lombok.Data;

@Data
public class PropertyDTO {
    private Integer id;
    private String name;
    private category category;
}
