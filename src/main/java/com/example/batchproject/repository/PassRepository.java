package com.example.batchproject.repository;

import com.example.batchproject.entity.pass.PassEntity;
import com.example.batchproject.status.PassStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface PassRepository extends JpaRepository<PassEntity,Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE PassEntity p" +
             "         SET p.remainingCount =:remainingCount," +
             "             p.modifiedAt = CURRENT_TIMESTAMP " +
             "         WHERE p.passSeq =:passSeq")
    int updateRemainingCount(Integer passSeq, Integer remainingCount);


    List<PassEntity> findByStatusAndStartedAtLessThanEqual(PassStatus ready, LocalDateTime nowAt);
}
