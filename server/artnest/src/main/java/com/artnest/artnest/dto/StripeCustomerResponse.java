package com.artnest.artnest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StripeCustomerResponse {
    private String name;
    private String email;
    private String customer_id;
    
}
