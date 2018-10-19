package com.asiainfo.dao;

import com.asiainfo.domain.FaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaceInfoRepository extends JpaRepository<FaceInfo,String> {
}
