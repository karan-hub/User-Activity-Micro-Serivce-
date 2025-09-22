package com.fitness.activityervice.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
@RequiredArgsConstructor
public class UserValidationService {


    private   final  WebClient  userServiceWebClient;

    public  boolean  validateUser(String usrId){
       try {
           return  userServiceWebClient.get()
                   .uri("/api/user/{usrId}/validate" , usrId)
                   .retrieve()
                   .bodyToMono(Boolean.class)
                   .block();
       } catch (WebClientException  e) {
           System.err.println("User Service call failed: " + e.getMessage());
       }
       return  false;
    }

}
