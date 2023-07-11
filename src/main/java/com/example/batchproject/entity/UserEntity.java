package com.example.batchproject.entity;


import com.example.batchproject.status.UserStatus;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@ToString
@Table(name = "user")
@Entity
@TypeDef(name = "json",typeClass = JsonStringType.class)
public class UserEntity extends BaseEntity {

    @Id
    private String userId;

    private String userName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    //json으로 저장되어 있는 문자열 데이터
    @Type(type="json")
    private Map<String,Object> meta;

    public String getUuid(){
        String uuid = null;
        if(meta.containsKey(("uuid"))){
            uuid = String.valueOf(meta.get("uuid"));
        }
        return uuid;
    }




}
