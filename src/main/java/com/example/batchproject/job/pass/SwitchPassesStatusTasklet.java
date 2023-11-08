package com.example.batchproject.job.pass;

import com.example.batchproject.entity.pass.PassEntity;
import com.example.batchproject.repository.PassRepository;
import com.example.batchproject.status.PassStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SwitchPassesStatusTasklet implements Tasklet {

    private final PassRepository passRepository;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        //이용권의 시작일이 현재시간이 된 경우, READY 상태를 PROGRESS로 변경
        final LocalDateTime nowAt = LocalDateTime.now();
        final List<PassEntity> passEntityList = passRepository.findByStatusAndStartedAtLessThanEqual(PassStatus.READY, nowAt);

        int count = 0;

        for (PassEntity passEntity : passEntityList) {
            passEntity.setStatus(PassStatus.PROGRESSED);
            count += 1;
        }
        log.info("SwitchPassesStatusTasklet - execute : 이용권 상태 변경 {} 건 추가 완료", count);

        return RepeatStatus.FINISHED;
    }


}
