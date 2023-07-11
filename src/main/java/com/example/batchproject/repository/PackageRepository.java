package com.example.batchproject.repository;

import com.example.batchproject.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<PackageEntity,Integer> {
}
