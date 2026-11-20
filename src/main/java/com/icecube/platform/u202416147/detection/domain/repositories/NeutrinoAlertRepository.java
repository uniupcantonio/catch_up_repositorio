package com.icecube.platform.u202416147.detection.domain.repositories;

import com.icecube.platform.u202416147.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertCode;

public interface NeutrinoAlertRepository {
    boolean existsByAlertCode(AlertCode alertCode);

    NeutrinoAlert save(NeutrinoAlert neutrinoAlert);
}
