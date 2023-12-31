package com.example.batchproject.job.pass;

import com.example.batchproject.entity.pass.BulkPassEntity;
import com.example.batchproject.status.BulkPassStatus;
import com.example.batchproject.entity.pass.PassEntity;
import com.example.batchproject.entity.pass.PassModelMapper;
import com.example.batchproject.entity.user.UserGroupMappingEntity;
import com.example.batchproject.repository.BulkPassRepository;
import com.example.batchproject.repository.PassRepository;
import com.example.batchproject.repository.UserGroupMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddPassesTasklet implements Tasklet {

    /**
     * 이용권 일괄 지급 tasklet
     * Tasklet?
     * 1.단순 작업 실행
     * 2. 비동기 작업과 통합 (비동기 작업을 실행하고 완료 여부 확인 가능)
     */

    private final PassRepository passRepository;
    private final BulkPassRepository bulkPassRepository;
    private final UserGroupMappingRepository userGroupMappingRepository;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        //이용권 시작 일시 1일 전, user group 내 각 사용자에게 이용권 추가.
        final LocalDateTime startedAt = LocalDateTime.now().minusDays(1);
        final List<BulkPassEntity> bulkPassEntities = bulkPassRepository.findByStatusAndStartedAtGreaterThan(BulkPassStatus.READY, startedAt);


        int count = 0;

        for (BulkPassEntity bulkPassEntity : bulkPassEntities) {
            final List<String> userIds = userGroupMappingRepository.findByUserGroupId(bulkPassEntity.getUserGroupId())
                    .stream().map(UserGroupMappingEntity::getUserId).toList();

            count += addPasses(bulkPassEntity, userIds);
            bulkPassEntity.setStatus(BulkPassStatus.COMPLETED);


        }

        log.info("AddPassesTasklet - execute : 이용권 {}건 추가 완료, startedAt={}", count, startedAt);
        return RepeatStatus.FINISHED;
    }

    //bulkPass의 정보로 pass 데이터 생성.
    private int addPasses(BulkPassEntity bulkPassEntity, List<String> userIds) {
        List<PassEntity> passEntities = new ArrayList<>();
        for (String userId : userIds) {
            PassEntity passEntity = PassModelMapper.INSTANCE.toPassEntity(bulkPassEntity, userId);
            passEntities.add(passEntity);
        }
        return passRepository.saveAll(passEntities).size();
    }
}