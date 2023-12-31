package com.example.batchproject.entity.pass;


import com.example.batchproject.status.BulkPassStatus;
import lombok.Getter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "bulk_pass")
public class BulkPassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bulkPassSeq;
    private Integer packageSeq;

    private String userGroupId;

    @Enumerated(EnumType.STRING)
    private BulkPassStatus status;
    private Integer count;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public void setStatus(BulkPassStatus status){
        this.status = status;
    }

}
