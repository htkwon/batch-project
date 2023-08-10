package com.example.batchproject.job.pass;


import com.example.batchproject.entity.pass.PassEntity;
import com.example.batchproject.status.PassStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ExpirePassesJobConfig {

    /**
     * 이용권 만료 작업
     */

    private final int CHUNCK_SIZE = 5;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;


    @Bean
    public Job expirePassJob(){
        return this.jobBuilderFactory.get("expirePassJob")
                .start(expirePassStep())
                .build();
    }
    @Bean
    public Step expirePassStep(){
        return this.stepBuilderFactory.get("expirePassStep")
                .<PassEntity,PassEntity>chunk(CHUNCK_SIZE)
                .reader(expirePassesItemReader())
                .processor(expirePassesItemProcessor())
                .writer(expirePassesItemWriter())
                .build();
    }

    /**
     * JpaCursorItemReader란?
     * 1. ItemReader의 구현체 중 하나.
     * 2. 대량의 데이터를 처리할 때 유용하며, 특히 큰 데이터베이스 테이블에서 데이터를 페이징 처리하고 읽어오는데 적합.
     * 3. 커서 방식으로 데이터를 읽어오므로, 메모리 사용량을 최소화하고 대량의 데이터를 효율적으로 처리할 수 있음.
     * 4. 병렬 처리. (CHUNK 단위)
     */

    @Bean
    @StepScope
    public JpaCursorItemReader<PassEntity> expirePassesItemReader(){
        return new JpaCursorItemReaderBuilder<PassEntity>()
                .name("expirePassesItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select p from PassEntity p where p.status =:status and p.endedAt <= :endedAt")
                .parameterValues(Map.of("status", PassStatus.PROGRESSED,"endedAt", LocalDateTime.now()))
                .build();
    }

    @Bean
    public ItemProcessor<PassEntity,PassEntity> expirePassesItemProcessor(){
        return passEntity ->{
            passEntity.setStatus(PassStatus.EXPIRED);
            passEntity.setExpiredAt(LocalDateTime.now());
            return passEntity;
        };
    }

    @Bean
    public JpaItemWriter<PassEntity> expirePassesItemWriter(){
        return new JpaItemWriterBuilder<PassEntity>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }



















}
