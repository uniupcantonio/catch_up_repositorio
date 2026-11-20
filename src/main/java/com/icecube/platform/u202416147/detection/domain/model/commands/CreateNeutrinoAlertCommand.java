package com.icecube.platform.u202416147.detection.domain.model.commands;

import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertCode;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertStatus;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.DetectorId;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.EnergyRange;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.FollowUpWindow;

import java.math.BigDecimal;

public record CreateNeutrinoAlertCommand(AlertCode alertCode,
                                         DetectorId detectorId,
                                         FollowUpWindow followUpWindow,
                                         EnergyRange energyRange,
                                         BigDecimal confidenceScore,
                                         AlertStatus status,
                                         String scientificNotes) {
    public CreateNeutrinoAlertCommand {
        if (alertCode == null)
            throw new IllegalArgumentException("alertCode cannot be null");
        if (detectorId == null)
            throw new IllegalArgumentException("detectorId cannot be null");
        if (followUpWindow == null)
            throw new IllegalArgumentException("followUpWindow cannot be null");
        if (energyRange == null)
            throw new IllegalArgumentException("energyRange cannot be null");
    }
}
