package com.artnest.artnest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLikesRequest {

    private Long product_id;
    private String email;
    
}
