package com.icecube.platform.u202416147.detection.domain.model.valueobjects;

import java.math.BigDecimal;

public record EnergyRange(BigDecimal minimumEnergy, BigDecimal maximumEnergy) {
    private static final String MINIMUM_ENERGY_POSITIVE_MESSAGE_KEY = "neutrino-alert.energy-range.minimum-energy.positive";
    private static final String MAXIMUM_ENERGY_POSITIVE_MESSAGE_KEY = "neutrino-alert.energy-range.maximum-energy.positive";
    private static final String MAXIMUM_GREATER_THAN_MINIMUM_MESSAGE_KEY = "neutrino-alert.energy-range.maximum-energy.greater-than-minimum";
    private static final String ENERGY_REQUIRED_MESSAGE_KEY = "neutrino-alert.energy-range.energy.required";

    public EnergyRange {
        if (minimumEnergy == null || minimumEnergy.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(MINIMUM_ENERGY_POSITIVE_MESSAGE_KEY);
        }
        if (maximumEnergy == null || maximumEnergy.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(MAXIMUM_ENERGY_POSITIVE_MESSAGE_KEY);
        }
        if (maximumEnergy.compareTo(minimumEnergy) <= 0) {
            throw new IllegalArgumentException(MAXIMUM_GREATER_THAN_MINIMUM_MESSAGE_KEY);
        }
    }

    public boolean containsEnergy(BigDecimal energy) {
        if (energy == null) {
            throw new IllegalArgumentException(ENERGY_REQUIRED_MESSAGE_KEY);
        }
        return energy.compareTo(minimumEnergy) >= 0 && energy.compareTo(maximumEnergy) <= 0;
    }
}
