package com.example.batchproject.entity.statistics;

import com.example.batchproject.entity.BookingEntity;
import com.example.batchproject.status.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "statistics")
public class StatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statisticsSeq;

    private LocalDateTime statisticsAt;

    private int allCount;
    private int attendedCount;
    private int cancelledCount;

    public static StatisticsEntity create(final BookingEntity bookingEntity) {
        StatisticsEntity statisticsEntity = new StatisticsEntity();
        statisticsEntity.setStatisticsAt(bookingEntity.getStatisticsAt());
        statisticsEntity.setAllCount(1);
        if (bookingEntity.isAttended()) {
            statisticsEntity.setAttendedCount(1);
        }
        if (BookingStatus.CANCELLED.equals(bookingEntity.getStatus())) {
            statisticsEntity.setCancelledCount(1);
        }
        return statisticsEntity;
    }

    public void add(final BookingEntity bookingEntity) {
        this.allCount++;
        if (bookingEntity.isAttended()) {
            this.attendedCount++;
        }
        if (BookingStatus.CANCELLED.equals(bookingEntity.getStatus())) {
            this.cancelledCount++;
        }
    }


}
