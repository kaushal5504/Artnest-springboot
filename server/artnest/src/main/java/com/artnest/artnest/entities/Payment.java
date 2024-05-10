package com.artnest.artnest.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
   
   private String cardHolderName;

   private String cardNumber;

   private LocalDate expirationDate;

   private String cvv;
    
}
