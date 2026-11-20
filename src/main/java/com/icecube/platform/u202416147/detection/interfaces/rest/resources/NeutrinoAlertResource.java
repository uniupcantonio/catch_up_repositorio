package com.icecube.platform.u202416147.detection.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Response resource representing a neutrino alert")
public record NeutrinoAlertResource(
        @Schema(description = "Persistent identifier", example = "1")
        Long id,
        @Schema(description = "Unique code of the neutrino alert", example = "IC-261121A")
        String alertCode,
        @Schema(description = "Identifier of the detector that raised the alert", example = "3f2b8c1e-7d4a-4e9b-9c2f-1a5d6e7f8a9b")
        String detectorId,
        @Schema(description = "Start date of the follow-up window", example = "2026-11-21")
        String followUpStartDate,
        @Schema(description = "End date of the follow-up window", example = "2026-11-28")
        String followUpEndDate,
        @Schema(description = "Minimum energy in teraelectronvolts (TeV)", example = "120.5")
        BigDecimal minimumEnergy,
        @Schema(description = "Maximum energy in teraelectronvolts (TeV)", example = "290.0")
        BigDecimal maximumEnergy,
        @Schema(description = "Confidence level of the alert, from 0 to 1", example = "0.85")
        BigDecimal confidenceScore,
        @Schema(description = "Alert status", example = "CANDIDATE")
        String status,
        @Schema(description = "Scientific notes about the alert", example = "Track-like event compatible with a blazar flare")
        String scientificNotes) {
}
