package com.flagcamp.ehub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonDeserialize(builder = User.Builder.class)
public class User {

    @Id
    @JsonProperty("Username")
    private String username;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("First_name")
    private String firstname;

    @JsonProperty("Last_name")
    private String lastname;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("Address")
    private String address;

    public User() {}

    public User(Builder builder){
        this.username = builder.username;
        this.password = builder.password;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public static class Builder{

        @JsonProperty("Username")
        private String username;

        @JsonProperty("Password")
        private String password;

        @JsonProperty("First_name")
        private String firstname;

        @JsonProperty("Last_name")
        private String lastname;

        @JsonProperty("Phone")
        private String phone;

        @JsonProperty("Address")
        private String address;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public User build(){return new User(this);}
    }
}
