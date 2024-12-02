package com.todo.task.services;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
public class FirebaseAuthService {
	
	public String authenticateUserAndGetUid(String email, String password, String apiKey) {
		 	System.out.println(apiKey);
		    String apiUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="+apiKey;

		    RestTemplate restTemplate = new RestTemplate();
		    Map<String, Object> requestBody = new HashMap<>();
		    requestBody.put("email", email);
		    requestBody.put("password", password);
		    requestBody.put("returnSecureToken", true);

		    try {
		        ObjectMapper objectMapper = new ObjectMapper();
		        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

		        HttpHeaders headers = new HttpHeaders();
		        headers.set("Content-Type", "application/json");

		        HttpEntity<String> request = new HttpEntity<>(jsonRequestBody, headers);

		        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

		        if (response.getStatusCode().is2xxSuccessful()) {
		            Map<String, Object> jsonResponse = objectMapper.readValue(response.getBody(), Map.class);
		            return (String) jsonResponse.get("localId");
		        } else {
		            throw new RuntimeException("Falha na autenticação do usuário.");
		        }
		    } catch (Exception e) {
		        throw new RuntimeException("Erro ao autenticar o usuário: " + e.getMessage());
		    }
		}
	
		public String authenticateUserAndGetIdToken(String email, String password, String apiKey) {
		    System.out.println(apiKey);
		    String apiUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + apiKey;
	
		    RestTemplate restTemplate = new RestTemplate();
		    Map<String, Object> requestBody = new HashMap<>();
		    requestBody.put("email", email);
		    requestBody.put("password", password);
		    requestBody.put("returnSecureToken", true);
	
		    try {
		        ObjectMapper objectMapper = new ObjectMapper();
		        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
	
		        HttpHeaders headers = new HttpHeaders();
		        headers.set("Content-Type", "application/json");
	
		        HttpEntity<String> request = new HttpEntity<>(jsonRequestBody, headers);
	
		        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
	
		        if (response.getStatusCode().is2xxSuccessful()) {
		            Map<String, Object> jsonResponse = objectMapper.readValue(response.getBody(), Map.class);
		            return (String) jsonResponse.get("idToken"); // Retorna o ID Token
		        } else {
		            throw new RuntimeException("Falha na autenticação do usuário.");
		        }
		    } catch (Exception e) {
		        throw new RuntimeException("Erro ao autenticar o usuário: " + e.getMessage());
		    }
		}


	    public String validateToken(String idToken) throws FirebaseAuthException {
	        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
	        String uid = decodedToken.getUid();
	        return uid;
	    }

	    public String generateCustomToken(String uid) throws FirebaseAuthException {
	        return FirebaseAuth.getInstance().createCustomToken(uid);
	    }
	    
}
