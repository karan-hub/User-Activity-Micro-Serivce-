package com.fitness.activityervice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document( collection = "activities")
public class Activity {

    @Id
    private  String  id;
    private  ActivityType activityType;
    private  Integer duration;
    private  Integer caloriesBurn;
    private LocalDateTime startTime;
    @Field("matrics")
    private Map<String , Object> additionalMatrics;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
