package com.artnest.artnest.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="like_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    @JsonManagedReference
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="product_id")
    @JsonManagedReference
    private Product product;

    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            // ", user='" + getUser() + "'" +
            // ", product='" + getProduct() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }

}
