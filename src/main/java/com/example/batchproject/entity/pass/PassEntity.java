package com.example.batchproject.entity.pass;

import com.example.batchproject.entity.BaseEntity;
import com.example.batchproject.status.PassStatus;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "pass")
public class PassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passSeq;
    private Integer packageSeq;
    private String userId;

    @Enumerated(EnumType.STRING)
    private PassStatus status;
    private Integer remainingCount;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime expiredAt;

    public void setStatus(PassStatus status){
        this.status = status;
    }
    public void setExpiredAt(LocalDateTime expiredAt){
        this.expiredAt = expiredAt;
    }

    public void setRemainingCount(Integer remainingCount){
        this.remainingCount = remainingCount;
    }
}
