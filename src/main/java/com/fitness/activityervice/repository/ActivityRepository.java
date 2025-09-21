package com.fitness.activityervice.repository;

import com.fitness.activityervice.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository  extends MongoRepository<Activity , String> {

}
