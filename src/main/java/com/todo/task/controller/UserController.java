package com.todo.task.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.todo.task.models.UserModel;
import com.todo.task.services.FirebaseAuthService;



@RestController
@RequestMapping("/login")
public class UserController {
	
	
    @Value("${firebase.apiKey}")
    private String apiKey;
	    
    private FirebaseAuthService firebaseAuthService;
    

	@PostMapping
	public String generateCustomToken(@RequestBody UserModel user) {
		firebaseAuthService = new FirebaseAuthService();
	    String uid = firebaseAuthService.authenticateUserAndGetUid(user.getEmail(), user.getPassword(), apiKey);

	    try {
	        return FirebaseAuth.getInstance().createCustomToken(uid);
	    } catch (FirebaseAuthException e) {
	        throw new RuntimeException("Erro ao gerar token personalizado: " + e.getMessage());
	    }
	}


}
