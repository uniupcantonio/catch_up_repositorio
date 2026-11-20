package com.icecube.platform.u202416147.detection.domain.model.valueobjects;

import java.util.UUID;

public record DetectorId(UUID value) {
    private static final String REQUIRED_MESSAGE_KEY = "neutrino-alert.detector-id.required";

    public DetectorId {
        if (value == null) {
            throw new IllegalArgumentException(REQUIRED_MESSAGE_KEY);
        }
    }
}
