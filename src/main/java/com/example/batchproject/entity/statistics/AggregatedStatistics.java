package com.example.batchproject.entity.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AggregatedStatistics {
    private LocalDateTime statisticsAt;
    private Long allcount;
    private Long attendedCount;
    private Long cancelledCount;

    public void merge(final AggregatedStatistics statistics){
        this.allcount += statistics.getAllcount();
        this.attendedCount += statistics.getAttendedCount();
        this.cancelledCount += statistics.getCancelledCount();
    }
}
