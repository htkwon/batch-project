package com.example.batchproject.entity.user;


import com.example.batchproject.entity.BaseEntity;
import com.example.batchproject.status.UserStatus;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class UserEntity extends BaseEntity {

    @Id
    private String userId;
    private String userName;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String uuid;

}
