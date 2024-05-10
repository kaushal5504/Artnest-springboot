package com.artnest.artnest.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Entity
@Table(name="user_info")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Size(max = 10)
    private Long phoneNumber;

    @NotBlank(message = "email cannnot be empty")
    private String email;

    @NotBlank(message = "password cant be empty")
    //@JsonIgnore
    private String password;

    Role role;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "payment_info", joinColumns = {@JoinColumn(name = "userid")})
    private List<Payment> payment = new ArrayList<>();
    // the name of the column will be user_id which contain id of user
    // the name of the table will be payment_info
    // here we donot need to create entity separately , to give more column names
    // @CollectionTable(name = "order_items", joinColumns = {
    // @JoinColumn(name = "order_id"),
    // @JoinColumn(name = "item_code")
    // })

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    //we will say that the user in like table will contain the join data 
    private List<Like> like = new ArrayList<>();
    

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    //we will say that the user in like table will contain the join data 
    private List<Wishlist> wishlist = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    //we will say that the user in like table will contain the join data 
    private List<Order> order = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Comment> comment = new ArrayList<>();

    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));

    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public User() {
    }

    public User(Long id, String name, Long phoneNumber, String email, String password, Role role, List<Payment> payment, List<Like> like, List<Comment> comment, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.payment = payment;
        this.like = like;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Payment> getPayment() {
        return this.payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public List<Like> getLike() {
        return this.like;
    }

    public void setLike(List<Like> like) {
        this.like = like;
    }

    public List<Comment> getComment() {
        return this.comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User phoneNumber(Long phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User role(Role role) {
        setRole(role);
        return this;
    }

    public User payment(List<Payment> payment) {
        setPayment(payment);
        return this;
    }

    public User like(List<Like> like) {
        setLike(like);
        return this;
    }

    public User comment(List<Comment> comment) {
        setComment(comment);
        return this;
    }

    public User createdAt(LocalDateTime createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(payment, user.payment) && Objects.equals(like, user.like) && Objects.equals(comment, user.comment) && Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, password, role, payment, like, comment, createdAt);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getRole() + "'" +
            ", payment='" + getPayment() + "'" +
            // ", like='" + getLike() + "'" +
            // ", comment='" + getComment() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}
