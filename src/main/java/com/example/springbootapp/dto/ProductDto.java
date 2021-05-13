package com.example.springbootapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Integer id;
    private String title;
    private Integer cost;
}
