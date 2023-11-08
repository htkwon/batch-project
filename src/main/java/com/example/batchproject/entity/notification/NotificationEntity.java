package com.example.batchproject.entity.notification;

import com.example.batchproject.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notification")
public class NotificationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationSeq;

    private String uuid;

    @Enumerated(EnumType.STRING)
    private NotificationEvent event;
    private String text;
    private boolean sent;
    private LocalDateTime sentAt;

    public void setSent(boolean sent){
        this.sent = sent;
    }
    public void setSentAt(LocalDateTime sentAt){
        this.sentAt = sentAt;
    }

}
