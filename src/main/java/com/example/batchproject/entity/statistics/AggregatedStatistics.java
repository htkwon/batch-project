package com.example.batchproject.entity.statistics;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AggregatedStatistics {
    private LocalDateTime statisticsAt;
    private Long allCount;
    private Long attendedCount;
    private Long cancelledCount;


    public void merge(final AggregatedStatistics statistics){
        this.allCount += statistics.getAllCount();
        this.attendedCount += statistics.getAttendedCount();
        this.cancelledCount += statistics.getCancelledCount();
    }
}
