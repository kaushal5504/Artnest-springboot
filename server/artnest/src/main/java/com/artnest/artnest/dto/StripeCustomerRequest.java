package com.artnest.artnest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StripeCustomerRequest {
    private String name;
    private String email;
     
    
}
