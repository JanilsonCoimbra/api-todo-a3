package com.todo.task;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TaskApplication.class, args);
		
        if (FirebaseApp.getApps().isEmpty()) {
        
		FileInputStream serviceAccount;
		serviceAccount = new FileInputStream("src/main/resources/firebase/serviceAccountKey.json");
		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();
		FirebaseApp.initializeApp(options);
        }
	
	}
}
