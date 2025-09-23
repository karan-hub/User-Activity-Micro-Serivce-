package com.fitness.activityervice.service;

import com.fitness.activityervice.dto.ActivityRequest;
import com.fitness.activityervice.dto.ActivityResponse;
import com.fitness.activityervice.model.Activity;
import com.fitness.activityervice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private  final ActivityRepository activityRepository;
    private  final UserValidationService validationService;

    private  final KafkaTemplate<String , Activity> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private  String topicName;

    public ActivityResponse trackActivity(ActivityRequest request) {
        boolean isValid = validationService.validateUser(request.getUserId());
        if (!isValid)  throw  new RuntimeException("USER NOT FOUND BRO");
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .activityType(request.getActivityType())
                .additionalMatrics(request.getAdditionalMatrics())
                .duration(request.getDuration())
                .caloriesBurn(request.getCaloriesBurn())
                .startTime(request.getStartTime())
                .build();

        activity =  activityRepository.save(activity);

        try {
            kafkaTemplate.send(topicName,activity.getId(),activity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  buildResponse(activity);
    }

    public List<ActivityResponse> getAllActivities() {
        return activityRepository.findAll()
                .stream()
                .map(this::buildResponse)
                .toList();
    }

    private ActivityResponse buildResponse(Activity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
                .userId(activity.getUserId())
                .activityType(activity.getActivityType() != null ? activity.getActivityType().name() : null)
                .duration(activity.getDuration())
                .caloriesBurn(activity.getCaloriesBurn())
                .startTime(activity.getStartTime())
                .additionalMatrics(activity.getAdditionalMatrics())
                .createdAt(activity.getCreatedAt())
                .updatedAt(activity.getUpdatedAt())
                .build();
    }

}
