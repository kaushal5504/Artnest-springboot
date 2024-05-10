package com.artnest.artnest.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.artnest.artnest.entities.Role;

import lombok.Data;
import java.util.Objects;

public class SignUpRequest {

    @NotBlank(message = "first name cant be empty")
    private String name;
    @NotBlank(message = "phonenumber cannot be blank")
    @Size(max=10)
    private Long phoneNumber;
    @NotBlank(message = "email name cant be empty")
    @Email(message = "email is not valid")
    private String email;
    @Size(min = 3, max = 10, message = "password:password must be between 3 to 10 characters")
    @NotBlank(message = "password cant be empty")
    private String password;
    @NotBlank
    Role role;



    public SignUpRequest() {
    }

    public SignUpRequest(String name, Long phoneNumber, String email, String password, Role role) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public SignUpRequest name(String name) {
        setName(name);
        return this;
    }

    public SignUpRequest phoneNumber(Long phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public SignUpRequest email(String email) {
        setEmail(email);
        return this;
    }

    public SignUpRequest password(String password) {
        setPassword(password);
        return this;
    }

    public SignUpRequest role(Role role) {
        setRole(role);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SignUpRequest)) {
            return false;
        }
        SignUpRequest signUpRequest = (SignUpRequest) o;
        return Objects.equals(name, signUpRequest.name) && Objects.equals(phoneNumber, signUpRequest.phoneNumber) && Objects.equals(email, signUpRequest.email) && Objects.equals(password, signUpRequest.password) && Objects.equals(role, signUpRequest.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, email, password, role);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
    
    


}
