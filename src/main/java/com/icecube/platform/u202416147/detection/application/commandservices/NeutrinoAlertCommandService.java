package com.icecube.platform.u202416147.detection.application.commandservices;

import com.icecube.platform.u202416147.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202416147.detection.domain.model.commands.CreateNeutrinoAlertCommand;
import com.icecube.platform.u202416147.shared.application.result.ApplicationError;
import com.icecube.platform.u202416147.shared.application.result.Result;

public interface NeutrinoAlertCommandService {
    Result<NeutrinoAlert, ApplicationError> handle(CreateNeutrinoAlertCommand command);
}
