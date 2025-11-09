package com.myorganisation.technova.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Job {
    @Scheduled(cron = "*/5 * * * * ?")
    public void everyFiveSeconds() {
        System.out.println("Hi at every 5 seconds");
    }
}




















