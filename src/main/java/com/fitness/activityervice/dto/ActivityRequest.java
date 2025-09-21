package com.fitness.activityervice.dto;

import com.fitness.activityervice.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {
    private ActivityType activityType;
    private String userId;
    private Integer duration;
    private Integer caloriesBurn;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMatrics;
}

