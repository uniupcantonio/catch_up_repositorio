package com.icecube.platform.u202416147.detection.infrastructure.persistence.jpa.assemblers;

import com.icecube.platform.u202416147.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertCode;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.DetectorId;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.EnergyRange;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.FollowUpWindow;
import com.icecube.platform.u202416147.detection.infrastructure.persistence.jpa.entities.NeutrinoAlertPersistenceEntity;

import java.util.UUID;

public class NeutrinoAlertPersistenceEntityAssembler {
    public static NeutrinoAlertPersistenceEntity toPersistenceEntityFromDomain(NeutrinoAlert neutrinoAlert) {
        var persistenceEntity = new NeutrinoAlertPersistenceEntity();
        persistenceEntity.setId(neutrinoAlert.getId());
        persistenceEntity.setAlertCode(neutrinoAlert.getAlertCode().code());
        persistenceEntity.setDetectorId(neutrinoAlert.getDetectorId().value().toString());
        persistenceEntity.setFollowUpStartDate(neutrinoAlert.getFollowUpWindow().startDate());
        persistenceEntity.setFollowUpEndDate(neutrinoAlert.getFollowUpWindow().endDate());
        persistenceEntity.setMinimumEnergy(neutrinoAlert.getEnergyRange().minimumEnergy());
        persistenceEntity.setMaximumEnergy(neutrinoAlert.getEnergyRange().maximumEnergy());
        persistenceEntity.setConfidenceScore(neutrinoAlert.getConfidenceScore());
        persistenceEntity.setStatus(neutrinoAlert.getStatus());
        persistenceEntity.setScientificNotes(neutrinoAlert.getScientificNotes());
        return persistenceEntity;
    }

    public static NeutrinoAlert toDomainFromPersistenceEntity(NeutrinoAlertPersistenceEntity persistenceEntity) {
        return new NeutrinoAlert(
                persistenceEntity.getId(),
                new AlertCode(persistenceEntity.getAlertCode()),
                new DetectorId(UUID.fromString(persistenceEntity.getDetectorId())),
                new FollowUpWindow(persistenceEntity.getFollowUpStartDate(), persistenceEntity.getFollowUpEndDate()),
                new EnergyRange(persistenceEntity.getMinimumEnergy(), persistenceEntity.getMaximumEnergy()),
                persistenceEntity.getConfidenceScore(),
                persistenceEntity.getStatus(),
                persistenceEntity.getScientificNotes());
    }
}
