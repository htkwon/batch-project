package com.example.batchproject.entity;


import com.example.batchproject.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "package")
public class PackageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageSeq;
    private String packageName;
    private Integer count;
    private Integer period;


}
