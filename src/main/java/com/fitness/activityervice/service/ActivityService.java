package com.fitness.activityervice.service;

import com.fitness.activityervice.dto.ActivityRequest;
import com.fitness.activityervice.dto.ActivityResponse;
import com.fitness.activityervice.model.Activity;
import com.fitness.activityervice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private  final ActivityRepository activityRepository;
    public ActivityResponse trackActivity(ActivityRequest request) {
        Activity activity = Activity.builder()
                .activityType(request.getActivityType())
                .additionalMatrics(request.getAdditionalMatrics())
                .duration(request.getDuration())
                .caloriesBurn(request.getCaloriesBurn())
                .startTime(request.getStartTime())
                .build();

        activity =  activityRepository.save(activity);

        return  buildResponse(activity);
    }

    private ActivityResponse buildResponse(Activity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
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
