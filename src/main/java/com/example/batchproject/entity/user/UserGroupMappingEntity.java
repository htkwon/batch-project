package com.example.batchproject.entity.user;

import com.example.batchproject.entity.BaseEntity;
import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
@Entity
@Getter
@Table(name="user_group_mapping")
@IdClass(UserGroupMappingId.class)
public class UserGroupMappingEntity extends BaseEntity {

    @Id
    private String userGroupId;
    @Id
    private String userId;

    private String userGroupName;
    private String description;



}
