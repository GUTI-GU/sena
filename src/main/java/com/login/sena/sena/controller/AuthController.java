package com.login.sena.sena.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.sena.sena.model.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Simulación de base de datos en memoria
    private Map<String, String> users = new HashMap<>();

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (users.containsKey(user.getUsername())) {
            return new ResponseEntity<>("Usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        users.put(user.getUsername(), user.getPassword());
        return new ResponseEntity<>("Usuario registrado", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (users.containsKey(user.getUsername()) && 
            users.get(user.getUsername()).equals(user.getPassword())) {
            return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
        }
        return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
    }
}