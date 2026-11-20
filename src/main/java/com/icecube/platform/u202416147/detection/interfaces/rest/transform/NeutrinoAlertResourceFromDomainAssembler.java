package com.icecube.platform.u202416147.detection.interfaces.rest.transform;

import com.icecube.platform.u202416147.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202416147.detection.interfaces.rest.resources.NeutrinoAlertResource;

public class NeutrinoAlertResourceFromDomainAssembler {
    public static NeutrinoAlertResource toResourceFromDomain(NeutrinoAlert neutrinoAlert) {
        return new NeutrinoAlertResource(
                neutrinoAlert.getId(),
                neutrinoAlert.getAlertCode().code(),
                neutrinoAlert.getDetectorId().value().toString(),
                neutrinoAlert.getFollowUpWindow().startDate().toString(),
                neutrinoAlert.getFollowUpWindow().endDate().toString(),
                neutrinoAlert.getEnergyRange().minimumEnergy(),
                neutrinoAlert.getEnergyRange().maximumEnergy(),
                neutrinoAlert.getConfidenceScore(),
                neutrinoAlert.getStatus().name(),
                neutrinoAlert.getScientificNotes());
    }
}
