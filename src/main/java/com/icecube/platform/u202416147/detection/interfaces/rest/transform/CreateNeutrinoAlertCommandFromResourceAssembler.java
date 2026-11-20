package com.icecube.platform.u202416147.detection.interfaces.rest.transform;

import com.icecube.platform.u202416147.detection.domain.model.commands.CreateNeutrinoAlertCommand;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertCode;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertStatus;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.DetectorId;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.EnergyRange;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.FollowUpWindow;
import com.icecube.platform.u202416147.detection.interfaces.rest.resources.CreateNeutrinoAlertResource;

import java.util.UUID;

public class CreateNeutrinoAlertCommandFromResourceAssembler {
    public static CreateNeutrinoAlertCommand toCommandFromResource(CreateNeutrinoAlertResource resource) {
        return new CreateNeutrinoAlertCommand(
                new AlertCode(resource.alertCode()),
                new DetectorId(UUID.fromString(resource.detectorId())),
                new FollowUpWindow(resource.followUpStartDate(), resource.followUpEndDate()),
                new EnergyRange(resource.minimumEnergy(), resource.maximumEnergy()),
                resource.confidenceScore(),
                resource.status() != null ? AlertStatus.valueOf(resource.status()) : null,
                resource.scientificNotes());
    }
}
