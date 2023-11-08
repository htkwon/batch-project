package com.example.batchproject.entity.user;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
public class UserGroupMappingId implements Serializable {

    private String userGroupId;
    private String userId;


}
