package com.example.batchproject.repository;

import com.example.batchproject.entity.UserGroupMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupMappingRepository extends JpaRepository<UserGroupMappingEntity,Integer> {
    List<UserGroupMappingEntity> findByUserGroupId(String userGroupId);

}
