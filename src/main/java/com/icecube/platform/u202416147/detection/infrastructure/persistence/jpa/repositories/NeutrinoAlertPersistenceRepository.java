package com.icecube.platform.u202416147.detection.infrastructure.persistence.jpa.repositories;

import com.icecube.platform.u202416147.detection.infrastructure.persistence.jpa.entities.NeutrinoAlertPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeutrinoAlertPersistenceRepository extends JpaRepository<NeutrinoAlertPersistenceEntity, Long> {
    boolean existsByAlertCode(String alertCode);
}
