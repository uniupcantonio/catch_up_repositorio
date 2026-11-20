package com.icecube.platform.u202416147.detection.infrastructure.persistence.jpa.entities;

import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertStatus;
import com.icecube.platform.u202416147.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "neutrino_alert", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"alert_code"}, name = NeutrinoAlertPersistenceEntity.ALERT_CODE_UNIQUE_CONSTRAINT)
})
public class NeutrinoAlertPersistenceEntity extends AuditableAbstractPersistenceEntity {

    public static final String ALERT_CODE_UNIQUE_CONSTRAINT = "uk_neutrino_alert_alert_code";

    @Column(nullable = false, length = 50)
    private String alertCode;

    @Column(nullable = false, length = 36)
    private String detectorId;

    @Column(nullable = false)
    private LocalDate followUpStartDate;

    @Column(nullable = false)
    private LocalDate followUpEndDate;

    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal minimumEnergy;

    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal maximumEnergy;

    @Column(nullable = false, precision = 5, scale = 4)
    private BigDecimal confidenceScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AlertStatus status;

    @Column(length = 500)
    private String scientificNotes;
}
