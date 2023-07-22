package com.example.batchproject.repository;

import com.example.batchproject.entity.pass.BulkPassEntity;
import com.example.batchproject.status.BulkPassStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BulkPassRepository extends JpaRepository<BulkPassEntity,Integer> {
    List<BulkPassEntity> findByStatusAndStartedAtGreaterThan(BulkPassStatus status, LocalDateTime startedAt);
}
