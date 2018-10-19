package com.asiainfo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="t_yx_faceinfo")
public class FaceInfo {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="faceimage")
    private String faceimage;
    @Column(name="age")
    private String age;
    @Column(name="sex")
    private String sex;
}
