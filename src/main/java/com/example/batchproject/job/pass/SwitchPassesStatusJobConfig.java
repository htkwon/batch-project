package com.example.batchproject.job.pass;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SwitchPassesStatusJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SwitchPassesStatusTasklet switchPassesStatusTasklet;

    @Bean
    public Job switchPassesJob() {
        return this.jobBuilderFactory.get("switchPassJob")
                .start(switchPassesStep())
                .build();
    }

    @Bean
    public Step switchPassesStep() {
        return this.stepBuilderFactory.get("switchPassStep")
                .tasklet(switchPassesStatusTasklet)
                .build();
    }

}
