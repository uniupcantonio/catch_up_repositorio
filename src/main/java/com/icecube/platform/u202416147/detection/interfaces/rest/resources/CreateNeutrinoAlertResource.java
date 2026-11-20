package com.icecube.platform.u202416147.detection.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Request resource used to create a neutrino alert")
public record CreateNeutrinoAlertResource(
        @Schema(description = "Unique code of the neutrino alert", example = "IC-261121A", maxLength = 50,
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "{validation.alert-code.required}")
        @Size(max = 50, message = "{validation.alert-code.size}")
        String alertCode,

        @Schema(description = "Identifier of the detector that raised the alert", example = "3f2b8c1e-7d4a-4e9b-9c2f-1a5d6e7f8a9b",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "{validation.detector-id.required}")
        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                message = "{validation.detector-id.format}")
        String detectorId,

        @Schema(description = "Start date of the follow-up window", example = "2026-11-21",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "{validation.follow-up-start-date.required}")
        LocalDate followUpStartDate,

        @Schema(description = "End date of the follow-up window, after the start date", example = "2026-11-28",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "{validation.follow-up-end-date.required}")
        LocalDate followUpEndDate,

        @Schema(description = "Minimum energy in teraelectronvolts (TeV)", example = "120.5",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "{validation.minimum-energy.required}")
        @Positive(message = "{validation.minimum-energy.positive}")
        BigDecimal minimumEnergy,

        @Schema(description = "Maximum energy in teraelectronvolts (TeV), greater than the minimum energy", example = "290.0",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "{validation.maximum-energy.required}")
        @Positive(message = "{validation.maximum-energy.positive}")
        BigDecimal maximumEnergy,

        @Schema(description = "Confidence level of the alert, from 0 to 1", example = "0.85",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "{validation.confidence-score.required}")
        @DecimalMin(value = "0.0", message = "{validation.confidence-score.range}")
        @DecimalMax(value = "1.0", message = "{validation.confidence-score.range}")
        BigDecimal confidenceScore,

        @Schema(description = "Alert status, CANDIDATE when not specified", example = "CANDIDATE",
                allowableValues = {"CANDIDATE", "UNDER_REVIEW", "VERIFIED", "DISMISSED"})
        @Pattern(regexp = "^(CANDIDATE|UNDER_REVIEW|VERIFIED|DISMISSED)$", message = "{validation.status.allowed}")
        String status,

        @Schema(description = "Optional scientific notes about the alert", example = "Track-like event compatible with a blazar flare",
                maxLength = 500)
        @Size(max = 500, message = "{validation.scientific-notes.size}")
        String scientificNotes) {
}
