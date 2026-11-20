package com.icecube.platform.u202416147.detection.domain.model.valueobjects;

import java.time.LocalDate;

public record FollowUpWindow(LocalDate startDate, LocalDate endDate) {
    private static final String START_DATE_REQUIRED_MESSAGE_KEY = "neutrino-alert.follow-up-window.start-date.required";
    private static final String END_DATE_REQUIRED_MESSAGE_KEY = "neutrino-alert.follow-up-window.end-date.required";
    private static final String END_DATE_AFTER_START_DATE_MESSAGE_KEY = "neutrino-alert.follow-up-window.end-date.after-start-date";
    private static final String CHECK_DATE_REQUIRED_MESSAGE_KEY = "neutrino-alert.follow-up-window.check-date.required";

    public FollowUpWindow {
        if (startDate == null) {
            throw new IllegalArgumentException(START_DATE_REQUIRED_MESSAGE_KEY);
        }
        if (endDate == null) {
            throw new IllegalArgumentException(END_DATE_REQUIRED_MESSAGE_KEY);
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException(END_DATE_AFTER_START_DATE_MESSAGE_KEY);
        }
    }

    public boolean isWithinWindow(LocalDate checkDate) {
        if (checkDate == null) {
            throw new IllegalArgumentException(CHECK_DATE_REQUIRED_MESSAGE_KEY);
        }
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }
}
