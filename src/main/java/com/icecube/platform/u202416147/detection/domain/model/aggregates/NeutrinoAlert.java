package com.icecube.platform.u202416147.detection.domain.model.aggregates;

import com.icecube.platform.u202416147.detection.domain.model.commands.CreateNeutrinoAlertCommand;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertCode;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertStatus;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.DetectorId;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.EnergyRange;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.FollowUpWindow;
import com.icecube.platform.u202416147.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class NeutrinoAlert extends AbstractDomainAggregateRoot<NeutrinoAlert> {
    private static final String CONFIDENCE_SCORE_RANGE_MESSAGE_KEY = "neutrino-alert.confidence-score.range";

    private final Long id;
    private final AlertCode alertCode;
    private final DetectorId detectorId;
    private final FollowUpWindow followUpWindow;
    private final EnergyRange energyRange;
    private final BigDecimal confidenceScore;
    private final AlertStatus status;
    private final String scientificNotes;

    public NeutrinoAlert(CreateNeutrinoAlertCommand command) {
        this(null,
                command.alertCode(),
                command.detectorId(),
                command.followUpWindow(),
                command.energyRange(),
                command.confidenceScore(),
                command.status() != null ? command.status() : AlertStatus.CANDIDATE,
                command.scientificNotes());
    }

    public NeutrinoAlert(Long id,
                         AlertCode alertCode,
                         DetectorId detectorId,
                         FollowUpWindow followUpWindow,
                         EnergyRange energyRange,
                         BigDecimal confidenceScore,
                         AlertStatus status,
                         String scientificNotes) {
        validateConfidenceScore(confidenceScore);
        this.id = id;
        this.alertCode = alertCode;
        this.detectorId = detectorId;
        this.followUpWindow = followUpWindow;
        this.energyRange = energyRange;
        this.confidenceScore = confidenceScore;
        this.status = status;
        this.scientificNotes = scientificNotes;
    }

    private static void validateConfidenceScore(BigDecimal confidenceScore) {
        if (confidenceScore == null
                || confidenceScore.compareTo(BigDecimal.ZERO) < 0
                || confidenceScore.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException(CONFIDENCE_SCORE_RANGE_MESSAGE_KEY);
        }
    }
}
