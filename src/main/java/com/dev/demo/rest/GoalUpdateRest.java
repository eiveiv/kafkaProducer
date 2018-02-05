package com.dev.demo.rest;

import com.dev.demo.endpoint.KafkaEndpoint;
import com.dev.demo.model.GoalUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class GoalUpdateRest {

    @Autowired
    private KafkaEndpoint kafkaEndpoint;

    @PostMapping("/goal-update")
    public void goalUpdate(@Valid @RequestBody GoalUpdate goalUpdate) {
        kafkaEndpoint.sendGoalMessage(goalUpdate);
    }
}
