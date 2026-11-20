package com.icecube.platform.u202416147.detection.domain.model.valueobjects;

public record AlertCode(String code) {
    private static final String REQUIRED_MESSAGE_KEY = "neutrino-alert.alert-code.required";

    public AlertCode {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException(REQUIRED_MESSAGE_KEY);
        }
    }
}
