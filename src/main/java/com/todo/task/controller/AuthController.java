package com.todo.task.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.firebase.auth.FirebaseAuthException;
import com.todo.task.models.UserModel;
import com.todo.task.services.FirebaseAuthService;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private final FirebaseAuthService firebaseAuthService;
    
    @Value("${firebase.apiKey}")
    private String apiKey;

    public AuthController(FirebaseAuthService firebaseAuthService) {
        this.firebaseAuthService = firebaseAuthService;
    }

    @PostMapping("/token")
    public String generateIdToken(@RequestBody UserModel user) {
        return firebaseAuthService.authenticateUserAndGetIdToken(user.getEmail(), user.getPassword(), apiKey);
    }

    @PostMapping("/custom-token")
    public String generateCustomToken(@RequestBody String uid) {
        try {
            return firebaseAuthService.generateCustomToken(uid);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Erro ao gerar token personalizado: " + e.getMessage());
        }
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel user) {
    	System.out.println("Apikey ---------"+apiKey);

        try {
            String uid = firebaseAuthService.registerUser(user.getEmail(), user.getPassword(), apiKey);
            return "Usuário registrado com sucesso! UID: " + uid;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    
    
}
