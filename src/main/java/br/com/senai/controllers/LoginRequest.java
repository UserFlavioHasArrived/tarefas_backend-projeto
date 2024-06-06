package br.com.senai.controllers;

import lombok.Data;

@Data
public class LoginRequest {
    private String name;
    private String password;
}
