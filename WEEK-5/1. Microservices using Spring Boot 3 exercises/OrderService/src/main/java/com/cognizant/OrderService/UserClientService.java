package com.cognizant.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserClientService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public UserDTO getUserById(int userId) {
        return webClientBuilder.build()
            .get()
            .uri("http://localhost:8081/users/" + userId) 
            .retrieve()
            .bodyToMono(UserDTO.class)
            .block();
    }
}


