package com.fitness.activityervice.controller;

import com.fitness.activityervice.dto.ActivityRequest;
import com.fitness.activityervice.dto.ActivityResponse;
import com.fitness.activityervice.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {

    private  final ActivityService activityService ;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity (@RequestBody ActivityRequest request ){
        return  ResponseEntity.ok(activityService.trackActivity(request));
    }

}
